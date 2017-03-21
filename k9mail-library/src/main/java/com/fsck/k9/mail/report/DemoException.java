package com.fsck.k9.mail.report;

public class DemoException extends ReportingThread {
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        throw new NullPointerException();
    }
}
