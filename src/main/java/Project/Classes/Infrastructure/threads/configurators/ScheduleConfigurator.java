package Project.Classes.Infrastructure.threads.configurators;

import Project.Classes.Infrastructure.configurators.ProxyConfigurator;
import Project.Classes.Infrastructure.core.Context;
import Project.Classes.Infrastructure.threads.annotations.Schedule;
import lombok.SneakyThrows;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ScheduleConfigurator implements ProxyConfigurator {
    @Override
    public <T> T makeProxy(T obj, Class<T> implementation, Context context) {
        List<Method> methods = Arrays.stream(implementation.getMethods())
                .filter(method -> method.isAnnotationPresent(Schedule.class))
                .toList();

        for (Method method : methods) {
            if (method.getReturnType().equals(Void.TYPE) && method.getModifiers() != Modifier.PUBLIC){
                throw new RuntimeException(String.format("Method %s is not public or its return type is not void", method.getName()));
            }
        }

        if (Arrays.stream(implementation.getMethods()).anyMatch(method -> method.isAnnotationPresent(Schedule.class)))
            return (T) Enhancer.create(implementation,(MethodInterceptor) this::invoke);
        return obj;
    }

    @SneakyThrows
    private Object invoke (Object object, Method method, Object[] args, MethodProxy methodProxy) {
    Schedule schedulesync = method.getAnnotation(Schedule.class);

    if (schedulesync != null) {
        System.out.println(method);
        Thread thread = new Thread(() -> this.invoker(object, methodProxy, args, schedulesync.timeout(), schedulesync.delta()));
        thread.setDaemon(true);
        thread.start();
    }
    return methodProxy.invokeSuper(object, args);
    }

    private void invoker(Object object, MethodProxy method, Object[] args, int milliseconds, int delta){
        Thread thread = new Thread(() -> {
            while (true) {
                try{
                    Thread invokeThread = new Thread(() -> {
                        try (ExecutorService executorService = Executors.newSingleThreadExecutor(new ThreadFactory() {
                            @Override
                            public Thread newThread(Runnable r) {
                                Thread fTread = Executors.defaultThreadFactory().newThread(r);
                                fTread.setDaemon(true);
                                return fTread;
                            }
                        })) {
                            try {
                                executorService.submit(() -> {
                                    try {
                                        return method.invokeSuper(object, args);
                                    } catch (Throwable throwable) {

                                    }
                                    return null;
                                }).get(milliseconds, TimeUnit.MILLISECONDS);
                            } catch (Exception e) {
                                executorService.shutdownNow();
                            }
                            executorService.shutdownNow();
                        }

                    });
                    invokeThread.setDaemon(true);
                    invokeThread.start();
                    Thread.currentThread().sleep(delta);
                } catch (InterruptedException e){
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}
