package com.map;

public class wall extends element{
    @Override
    public String toString() {
        return "#";
    }
    @Override
    public boolean equals(element opp) {
        return opp.toString() == toString();
    }
}
