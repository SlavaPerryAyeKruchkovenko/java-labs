package com.extensions;

import java.util.List;

public class ListExtensions {
    public static <T> void concat(List<T> list, T... elements) {
        for(T object : elements) {
            list.add(object);
        }
    }
}
