package com.example.demo2.service.Impl;

import com.example.demo2.common.Token;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpPostPutDelete
{
    /**
     * 华为请求接口
     * @param urlPath 华为请求后缀
     * @param method  请求办法（POST/PUT/DELETE）
     * @param Json
     * @return
     */
    public static String doPostToJson(String urlPath,String method, String Json) {
        
        String result = "";
        BufferedReader reader = null;
        HttpURLConnection conn = null;
        try {
            trustAllHosts();
            URL url = new URL(urlPath);
            if (url.getProtocol().toLowerCase().equals("https")) {
                HttpsURLConnection httpsConn = (HttpsURLConnection) url.openConnection();
                httpsConn.setHostnameVerifier(DO_NOT_VERIFY);
                conn = httpsConn;
            }
            else {
                conn = (HttpURLConnection) url.openConnection();
            }
            
            conn.setRequestMethod(method);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("X-ACCESS-TOKEN", Token.getToken());
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            // conn.setRequestProperty("accept","*/*");
            conn.setRequestProperty("accept", "application/json");
            if (Json != null) {
                byte[] writebytes = Json.getBytes();
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(Json.getBytes());
                outwritestream.flush();
                outwritestream.close();
            }
            
            if (conn.getResponseCode() == 200) {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
                result = reader.readLine();
            }
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    
        return result;
    }
    
    final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier()
    {
        public boolean verify(String arg0, SSLSession arg1) {
            return true;
        }
    };
    
    public static void trustAllHosts() {
        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager()
        {
            
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[] {};
            }
            
            public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
            
            }
            
            public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
            
            }
        }
        
        };
        
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
