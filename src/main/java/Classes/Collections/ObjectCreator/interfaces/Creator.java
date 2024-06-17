package Classes.Collections.ObjectCreator.interfaces;

import Classes.Vehicle;
import Classes.VehicleType;

import java.util.List;
import java.util.Objects;

public interface Creator<T> {
    static final String REGEX_PATTERN = ",(?![0-9]+\")";
    public T createObject(String line);

}
