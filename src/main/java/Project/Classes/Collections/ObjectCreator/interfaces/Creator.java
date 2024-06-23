package Project.Classes.Collections.ObjectCreator.interfaces;

public interface Creator<T> {
    static final String REGEX_PATTERN = ",(?![0-9]+\")";
    public T createObject(String line);

}
