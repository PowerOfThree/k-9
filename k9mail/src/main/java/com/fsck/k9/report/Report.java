package com.fsck.k9.report;

import java.util.Date;

public class Report {
    private Date date;
    private Thread thread;
    private Throwable throwable;

    public Report(Date date, Thread thread, Throwable throwable) {
        this.date = date;
        this.thread = thread;
        this.throwable = throwable;
    }

    public Date getDate() {
        return date;
    }

    public Thread getThread() {
        return thread;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
