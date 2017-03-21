package com.fsck.k9.mail.report;

public interface ReportSink {
    void handle(Report report);
}
