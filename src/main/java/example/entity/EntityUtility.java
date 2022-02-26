package example.entity;

import java.util.Arrays;
import java.util.List;

public class EntityUtility {

    public static List<Class<?>> getClasses() {
        return Arrays.asList(
                User.class,
                Role.class
        );
    }

    public static String getDataBaseName(){
        return "exemple_db";
    }
}
