package com.fsck.k9.report;

import android.util.Log;

import java.util.Arrays;

public class LogReportSink implements ReportSink {
    private static final String BugReportSinkTAG = "K9_BUGREPORT";
    @Override
    public void handle(Report report) {
        Log.e(BugReportSinkTAG, "This is bug. Please report it.\n" +
                "date:       " + report.getDate() +
                "thread:     " + report.getThread().getName() +
                "message:    " + report.getThrowable().getMessage() +
                "stacktrace: " + ReportHelper.getStackTrace(report.getThrowable()));
    }
}
