package jemboy.navitwo.Network;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.widget.Button;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import jemboy.navitwo.Main.MainActivity;
import jemboy.navitwo.Main.OnTaskCompleted;
import jemboy.navitwo.Utility.Constants;

public class DownloadIDTask extends AsyncTask<String, Void, String> {
    OnTaskCompleted taskCompleted;

    public DownloadIDTask(MainActivity mActivity) {
        this.taskCompleted = mActivity;
    }

    protected String doInBackground(String... params) {
        String result = "";
        try {
            URL url = new URL(Constants.SERVER);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(3000);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            String requestID = "request=downloadID&remoteID=" + params[0];
            connection.setFixedLengthStreamingMode(requestID.getBytes().length);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            writer.write(requestID);
            writer.flush();
            writer.close();

            InputStream inputStream = connection.getInputStream();
            StringBuffer stringBuffer = new StringBuffer();
            int character;
            while ((character = inputStream.read()) != -1) {
                stringBuffer.append((char)character);
            }
            inputStream.close();
            result = stringBuffer.toString();
        } catch (Exception e) {
            result = "Exception";
            e.printStackTrace();
        }
        return result;
    }

    protected void onPostExecute(String result) {
        taskCompleted.onDownloadIDCompleted(result);
    }
}