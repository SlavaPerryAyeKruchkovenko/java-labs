package com.util;

import java.util.ArrayList;
import java.util.List;

import static com.extensions.ListExtensions.concat;

public class ExceptionUtil {
    public static Throwable merge(Throwable ex, StackTraceElement[] stackTrace) {
        for (Throwable throwable : splitByHierarchy(ex))
            appendStackTrace(throwable, stackTrace);
        return ex;
    }
    private static void appendStackTrace(Throwable t, StackTraceElement[] elements) {
        List<StackTraceElement> traceElements = new ArrayList<>();
        concat(traceElements, t.getStackTrace());
        concat(traceElements, elements);
        t.setStackTrace(traceElements.toArray(new StackTraceElement[0]));
    }

    private static List<Throwable> splitByHierarchy(Throwable ex) {
        List<Throwable> exs = new ArrayList<>();
        while (ex != null) {
            exs.add(ex);
            System.out.println(ex.getMessage());
            ex = ex.getCause();
        }
        return exs;
    }
}
