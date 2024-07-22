package Project.Classes;

import Project.Classes.Infrastructure.core.annotations.Autowired;
import Project.Classes.Infrastructure.dto.annotations.Column;
import Project.Classes.Infrastructure.dto.entity.OrderDTO;
import Project.Classes.Infrastructure.dto.service.OrdersService;
import Project.Classes.interfaces.Fixer;
import Project.Classes.UtilFiles.CSVReadWrite;
import Project.Classes.UtilFiles.LineProcessor;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class MechanicService implements Fixer {
    public static final String[] DETAILS = {"фильтр", "втулка", "вал", "ось", "свеча", "масло", "ГРМ", "шрус"};
    final Path FILE_PATH = (Paths.get("D:/JavaProjects/AutoparkProject/src/CSV/orders.csv"));
    static final int AMOUNT_OF_BROKEN_DETAILS = 2;
    final Charset utf8 = StandardCharsets.UTF_8;
    @Autowired
    private OrdersService ordersService;

    public MechanicService(){}
    public List<Vehicle> findNotBrokenVehicles(List<Vehicle> vehicles) {
        return vehicles.stream().filter(vehicle -> !isBroken(vehicle)).collect(Collectors.toList());
    }
@SneakyThrows
    public void detectBreaking(Vehicle vehicle) {
        Map<String, Integer> detailsToRepair = new HashMap<>();

        OrderDTO orders = new OrderDTO();

        Arrays.stream(DETAILS)
                .forEach(detail -> detailsToRepair.put(detail, ((int) (Math.random() * AMOUNT_OF_BROKEN_DETAILS))));

        Field []fields = Arrays.stream(orders.getClass().getDeclaredFields()).toArray(Field[]::new);



        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)){
                field.setAccessible(true);

                if (field.getAnnotation(Column.class).name().equals("vehicleId"))
                    field.set(orders, vehicle.getVehicleId());
                else
                    field.set(orders, detailsToRepair.get(field.getAnnotation(Column.class).name()));
            }
        }
        if (Math.random() > 0.5) {
            ordersService.save(orders);
            CSVReadWrite.makeRecord(LineProcessor.transformToCSVLine(vehicle, detailsToRepair), FILE_PATH);
        }

    }

    @Override
    public void repair(Vehicle vehicle) {
        ordersService.deleteData();

        if (isBroken(vehicle)) {
            System.out.println(vehicle.getModel() + " Was fixed");
            CSVReadWrite.clearFile(FILE_PATH);
        }
    }
    public void repair(long id) {
        ordersService.delete(id);

//        if (isBroken()) {
//            System.out.println(vehicle.getModel() + " Was fixed");
//            CSVReadWrite.clearFile(FILE_PATH);
//        }
    }

    @Override
    public boolean isBroken(Vehicle vehicle) {
        List<String> vehiclesID = CSVReadWrite.readCSV(FILE_PATH);
        for (String vehicleID : vehiclesID) {
            if (vehicleID.charAt(0) - '0' == vehicle.getVehicleId()) {
                return true;
            }
        }
        return false;
    }
}
