package com.afrikpay.security.utils;

import com.afrikpay.security.entity.AppConfig;
import com.afrikpay.security.entity.Parameters;
import com.afrikpay.security.entity.Terminal;
import com.afrikpay.security.entity.WhiteList;

import java.util.Arrays;
import java.util.List;

public class DataBaseUtil {

    private static final String DATABASE_NAME = "security";

    private DataBaseUtil(){}

    public static List<Class<?>> getDataBaseClasses(){
        return Arrays.asList(
                AppConfig.class,
                Parameters.class,
                Terminal.class,
                WhiteList.class
        );
    }

    public static String getDatabaseName(){
        return DATABASE_NAME;
    }
}
