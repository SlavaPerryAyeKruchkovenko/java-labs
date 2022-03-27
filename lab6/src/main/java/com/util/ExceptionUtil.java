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

    /**
     * Split exception by Hierarchy
     * for Example if Exception1 was thrown earlier, then Exception2.
     * Exception2 will be upper then Exception1
     * @param ex caught Exception
     * @return list of Exception by hierarchy order
     */
    private static List<Throwable> splitByHierarchy(Throwable ex) {
        List<Throwable> exs = new ArrayList<>();
        if(ex != null){
            for(Throwable throwable : ex.getSuppressed()){
                exs.add(throwable);
            }
            while (ex != null) {
                exs.add(ex);
                ex = ex.getCause();
            }
        }

        return exs;
    }
    private static void appendStackTrace(Throwable t, StackTraceElement[] elements) {
        List<StackTraceElement> traceElements = new ArrayList<>();
        if(t != null){
            concat(traceElements, t.getStackTrace());
            concat(traceElements, elements);
            t.setStackTrace(traceElements.toArray(new StackTraceElement[0]));
        }
    }

}
