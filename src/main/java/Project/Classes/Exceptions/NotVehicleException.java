package Project.Classes.Exceptions;

public class NotVehicleException extends Exception {
    NotVehicleException (){}
    public NotVehicleException(String message){
        super(message);
    }
}