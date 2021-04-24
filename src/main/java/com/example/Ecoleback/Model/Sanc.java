package com.example.Ecoleback.Model;

public enum Sanc {
    Avertissement,
    Exclusion,
    Expulsion;


    public static <T extends Enum<T>> T valueOf(Class<T> enumType, String name) {
        return Enum.valueOf(enumType, name);
    }
}
