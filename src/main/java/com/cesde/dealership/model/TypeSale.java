package com.cesde.dealership.model;

public enum TypeSale {
    NEW("new"),
    RESALE("resale");

    private final String value;

    TypeSale(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


    // Metodo para obtener el valor del enum desde la cadena
    public static TypeSale fromValue(String value) {
        for (TypeSale type : TypeSale.values()) {
            if (type.getValue().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Valor no válido: " + value);
    }
}
