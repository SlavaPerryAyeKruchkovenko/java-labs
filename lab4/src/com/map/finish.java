package com.map;

public class finish extends element{
    @Override
    public String toString() {
        return "f";
    }

    @Override
    public boolean equals(element opp) {
        return opp.toString() == toString();
    }
}
