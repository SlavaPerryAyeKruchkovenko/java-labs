package com.map;

public class start extends element{
    @Override
    public String toString() {
        return "s";
    }
    @Override
    public boolean equals(element opp) {
        return opp.toString() == toString();
    }
}
