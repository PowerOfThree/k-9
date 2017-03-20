package com.fsck.k9.report;

import android.app.Activity;
import android.util.JsonWriter;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

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
        try {
            StringWriter json = new StringWriter();
            JsonWriter jsonWriter = new JsonWriter(json);
            report.json(jsonWriter);
            jsonWriter.flush();
            Log.d(BugReportSinkTAG, "JSON to be sent: " + json.toString());

            Log.d(BugReportSinkTAG, "Opening connecction to Bug Reports server");
            URLConnection client = sinkUrl.openConnection();
            //client.setRequestProperty("Content-Length", "" + json.toString().length());
            client.setRequestProperty("Content-Type", "application/json");
            client.setDoOutput(true);

            Log.d(BugReportSinkTAG, "Writing bugreport to TCP stream");
            OutputStreamWriter httpWriter = new OutputStreamWriter(client.getOutputStream());
            httpWriter.write(json.toString());
            httpWriter.flush();
            BufferedReader serverAnswer = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String line;
            while ((line = serverAnswer.readLine()) != null) {
                Log.i(BugReportSinkTAG, line);
            }

            httpWriter.close();
            serverAnswer.close();
        } catch(IOException e) {
            Log.e(BugReportSinkTAG, e.getMessage());
        }
    }
}
