 package siteLocal;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpUtility {
 
    private static HttpURLConnection httpConn;
 
    public static HttpURLConnection sendGetRequest(String requestURL)
            throws IOException {
        URL url = new URL(requestURL);
        httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setUseCaches(false);
 
        httpConn.setDoInput(true); 
        httpConn.setDoOutput(false);
 
        return httpConn;
    }
 
    public static HttpURLConnection sendPostRequest(String requestURL,Map<String, String> params) throws IOException {
        URL url = new URL(requestURL);
        httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setUseCaches(false);
 
        httpConn.setDoInput(true); 
 
        StringBuilder requestParams = new StringBuilder();

        httpConn.setRequestMethod("POST");
        httpConn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded" );

        if (params != null && params.size() > 0) {
 
            httpConn.setDoOutput(true);
 
            Iterator<String> paramIterator = params.keySet().iterator();
            while (paramIterator.hasNext()) {
                String key = paramIterator.next();
                String value = params.get(key);
                requestParams.append(URLEncoder.encode(key, "UTF-8"));
                requestParams.append("=").append(URLEncoder.encode(value, "UTF-8"));
                requestParams.append("&");
            }
            httpConn.setRequestProperty( "Content-Length", String.valueOf(requestParams.length()));
            try (OutputStream os = httpConn.getOutputStream()) {
                os.write(requestParams.toString().getBytes());
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }

        return httpConn;
    }
 
    public static String readSingleLineResponse() throws IOException {
        InputStream inputStream = null;
        if (httpConn != null) {
            inputStream = httpConn.getInputStream();
        } else {
            throw new IOException("Connection is not established.");
        }
        String response;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                inputStream))) {
            response = reader.readLine();
        }
 
        return response;
    }
 
    public static String[] readMultipleLinesRespone() throws IOException {
        InputStream inputStream = null;
        if (httpConn != null) {
            inputStream = httpConn.getInputStream();
        } else {
            throw new IOException("Connection is not established.");
        }
 
        List<String> response;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                inputStream))) {
            response = new ArrayList<>();
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.add(line);
            }
        }
 
        return response.toArray(new String[0]);
    }
     
    public static void disconnect() {
        if (httpConn != null) {
            httpConn.disconnect();
        }
    }
}