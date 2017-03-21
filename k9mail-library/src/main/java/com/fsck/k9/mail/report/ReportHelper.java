package com.fsck.k9.mail.report;

public class ReportHelper {
    static StringBuilder getStackTrace(Throwable t) {
        StringBuilder stackTrace = new StringBuilder();
        for(StackTraceElement traceElement : t.getStackTrace()) {
            stackTrace.append(traceElement.toString());
            stackTrace.append("\n");
        }
        return stackTrace;
    }
}
