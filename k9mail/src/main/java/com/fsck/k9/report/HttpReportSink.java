package com.fsck.k9.report;

import android.util.JsonWriter;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class HttpReportSink implements ReportSink {
    private URL sinkUrl;
    private static final String BugReportSinkTAG = "K9_HttpReportSink";

    public HttpReportSink(String sinkUrlStr) {
        try {
            this.sinkUrl = new URL(sinkUrlStr);
        } catch (MalformedURLException e){
            Log.e(BugReportSinkTAG, e.getMessage());
        }
    }

    @Override
    public void handle(Report report) {
        BufferedWriter httpWriter = null;
        try {
            httpWriter = openConnection();
            JsonWriter jsonWriter = new JsonWriter(httpWriter);
            report.json(jsonWriter);
        } catch(IOException e) {
            Log.e(BugReportSinkTAG, e.getMessage());
        } finally {
            try {
                if (httpWriter != null) {
                    httpWriter.close();
                }
            } catch (IOException e) {
                Log.e(BugReportSinkTAG, e.getMessage());
            }
        }
    }

    private BufferedWriter openConnection() throws IOException {
        HttpURLConnection conn = (HttpURLConnection) sinkUrl.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        return writer;
    }
}
