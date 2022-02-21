package com.map;

public abstract class element {
    public abstract boolean equals(element opp);
    public static element getElement(String sign) throws Exception {
        switch (sign) {
            case  ("s"):
                return new start();
            case ("f"):
                return new finish();
            case ("."):
                return new pass();
            case ("*"):
                return new way();
            case ("#"):
                return new wall();
            default:
                throw new Exception("incorrect sign");
        }
    }
}
