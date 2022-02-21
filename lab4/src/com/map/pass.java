package com.map;

public class pass extends element{
    @Override
    public String toString() {
        return ".";
    }
    @Override
    public boolean equals(element opp) {
        return opp.toString() == toString();
    }
}
