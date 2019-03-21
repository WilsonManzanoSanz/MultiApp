package manzano.wilson.multiapp.Services;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import manzano.wilson.multiapp.Constants.Payload;

/**
 * Created by User on 17/11/2017.
 */

public class HttpConnection extends AsyncTask<String, String, String>
{

    private HttpURLConnection mConnection;
    @SuppressLint("StaticFieldLeak")
    private Context mContext;
    @SuppressLint("StaticFieldLeak")
    private Activity mActivity;
    private ProgressDialog pdLoading;
    private static final int CONNECTION_TIMEOUT=10000;
    private static final int READ_TIMEOUT=15000;
    private AsyncHttpResponse mAsyncHttpResponse = null;


    public HttpConnection(AsyncHttpResponse asyncHttpResponse) {
        this.mAsyncHttpResponse = asyncHttpResponse;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        URL URLServer = null;
        String mMethod;
        String data = "";
        Payload payload;
        try {

            // Enter URL address where your php file resides
            URLServer = new URL(params[0]);
            mMethod = params[1];
            data = params[2];
            payload = new Gson().fromJson(data, Payload.class);


        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "exception";
        }
        try {
            // Setup HttpURLConnection class to send and receive data from php and mysql
            mConnection = (HttpURLConnection) URLServer.openConnection();
            mConnection.setReadTimeout(READ_TIMEOUT);
            mConnection.setConnectTimeout(CONNECTION_TIMEOUT);
            mConnection.setRequestMethod(mMethod);

            // setDoInput and setDoOutput method depict handling of both send and receive
            mConnection.setDoInput(true);
            mConnection.setDoOutput(true);
            // Append parameters to URL
            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter(payload.getKey(), payload.getValue());
            String query = builder.build().getEncodedQuery();
            // Open connection for sending data
            OutputStream os = mConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();
            mConnection.connect();

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return "exception";
        }

        try {

            int response_code = mConnection.getResponseCode();

            // Check if successful connection made
            if (response_code == HttpURLConnection.HTTP_OK) {

                // Read data sent from server
                InputStream input = mConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                StringBuilder result = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                // Pass data to onPostExecute method
                Log.d("result", result.toString());
                return(result.toString());

            }else{

                return("unsuccessful");
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "exception";
        } finally {
            mConnection.disconnect();
        }


    }

    @Override
    protected void onPostExecute(String result) {

        mAsyncHttpResponse.httpResponse(result);

    }

}
