package com.morse.socketlibrary.request;

public enum Type {

    TCP("tcp"),
    UDP("udp");

    private String type;

    Type(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static Type fromType(String type) {
        for (Type t : Type.values()) {
            if (t.type == type) {
                return t;
            }
        }
        return null;
    }
}
