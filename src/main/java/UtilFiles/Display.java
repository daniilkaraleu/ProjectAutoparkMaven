package UtilFiles;

import Classes.interfaces.Displayable;

import java.util.List;

public class Display {
    public static void display(List <?> collection) throws IllegalArgumentException{
        if (collection.getFirst() instanceof Displayable)
            collection.forEach(System.out::println);
        else
            throw new IllegalArgumentException(String.format("The collection of %s can't be displayed",
                    collection
                            .getFirst()
                            .getClass()
                            .getSimpleName()));
    }
}
