package com.fsck.k9.mail.report;

import android.util.Log;

public class LogReportSink implements ReportSink {
    private static final String BugReportSinkTAG = "K9_BUGREPORT";
    @Override
    public void handle(Report report) {
        Log.e(BugReportSinkTAG, "This is bug. Please report it.\n" +
                "date:       " + report.getDate() + "\n" +
                "thread:     " + report.getThread().getName() + "\n" +
                "message:    " + report.getThrowable().getMessage() + "\n" +
                "stacktrace: " + ReportHelper.getStackTrace(report.getThrowable()));
    }
}
