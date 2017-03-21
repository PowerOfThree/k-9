package com.fsck.k9.mail.report;

import android.util.JsonWriter;

import java.io.IOException;
import java.util.Date;

public class Report {
    private Date date;
    private Thread thread;
    private Throwable throwable;

    public Report(Thread thread, Throwable throwable) {
        this(new Date(), thread, throwable);
    }

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

    public void json(JsonWriter json) throws IOException {
        json.beginObject();
        json.name("date").value(date.getTime());
        json.name("thread").value(thread.getName());
        json.name("message").value(throwable.getMessage());
        json.name("stacktrace").value(ReportHelper.getStackTrace(throwable).toString());
        json.endObject();
    }
}
