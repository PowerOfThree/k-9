package com.fsck.k9.mail.report;


public class ReportingThread extends Thread {
    public static ReportSink logReportSink = new LogReportSink();
    public static ReportSink httpReportSink = new HttpReportSink("http://89.223.20.120:1080/create_issue/");

    public ReportingThread() {
    }

    public ReportingThread(Runnable target) {
        super(target);
    }

    public ReportingThread(ThreadGroup group, Runnable target) {
        super(group, target);
    }

    public ReportingThread(String name) {
        super(name);
    }

    public ReportingThread(ThreadGroup group, String name) {
        super(group, name);
    }

    public ReportingThread(Runnable target, String name) {
        super(target, name);
    }

    public ReportingThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
    }

    public ReportingThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
    }

    @Override
    public synchronized void start() {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable throwable) {
                Report report = new Report(thread, throwable);
                ReportingThread.logReportSink.handle(report);
                ReportingThread.httpReportSink.handle(report);
            }
        });

        super.start();
    }
}
