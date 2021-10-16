package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.HttpApiRequestModel;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.*;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

public class HttpService extends HttpApiRequestModel {

    private static final Logger LOGGER = LogManager.getLogger(HttpApiRequestModel.class);
    protected static final String HIGHLIGHT = "=============";

    ObjectMapper objectMapper = new ObjectMapper();
    String requestParamJson = null;
    String requestHeaderJson = null;
    String result = null;
    JSONObject jsonResponse = null;
    StringEntity entity = null;
    HttpPost post = null;
    Object json = null;

    /**
     *
     * @return httpClient with trusted SSL Certificate
     */
//    public static CloseableHttpClient getCloseableHttpClient() {
//        CloseableHttpClient httpClient = null;
//        try {
//            httpClient = HttpClients.custom().
//                    setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).
//                    setSSLContext(new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy()
//                    {
//                        public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException
//                        {
//                            return true;
//                        }
//                    }).build()).build();
//        } catch (KeyManagementException e) {
//            LOGGER.error("KeyManagementException in creating http client instance", e);
//        } catch (NoSuchAlgorithmException e) {
//            LOGGER.error("NoSuchAlgorithmException in creating http client instance", e);
//        } catch (KeyStoreException e) {
//            LOGGER.error("KeyStoreException in creating http client instance", e);
//        }
//        return httpClient;
//    }

    /**
     * send post request
     */

    public void sendPostRequest(String url, Map<String, String> requestHeaders, Map<String, String> requestParams) {

        post = new HttpPost(url);
        LOGGER.info(HIGHLIGHT + "POST URL" + HIGHLIGHT );
        LOGGER.info(url);
        ObjectMapper objectMapper = new ObjectMapper();

        for(Map.Entry<String, String> entry : requestHeaders.entrySet()){
            post.setHeader(entry.getKey(),entry.getValue());
        }

        try {
            requestParamJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestParams); //pretty
            requestHeaderJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestHeaders); //pretty

        } catch ( JsonProcessingException e) {
            e.printStackTrace();
        }

        LOGGER.info(HIGHLIGHT + "REQUEST HEADER" + HIGHLIGHT);
        LOGGER.info(requestHeaderJson);
        LOGGER.info(HIGHLIGHT + "REQUEST BODY" + HIGHLIGHT);
        LOGGER.info(requestParamJson);

        entity = new StringEntity(requestParamJson,"UTF-8");
        post.setEntity(entity);

        try (//        CloseableHttpClient httpClient = getCloseableHttpClient();
             CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)){

            result = EntityUtils.toString(response.getEntity()); //display all
            json = objectMapper.readValue(result, Object.class);

            jsonResponse = new JSONObject(result); // return as json
            setLatestResponse(jsonResponse);

            LOGGER.info(HIGHLIGHT + "RESPONSE BODY" + HIGHLIGHT);
            LOGGER.info(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json)); //pretty
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendGetRequest(String url, Map<String, String> requestHeaders ){
        LOGGER.info(HIGHLIGHT + "GET URL" + HIGHLIGHT );
        LOGGER.info(url);

        HttpGet httpGet = new HttpGet(url);

        if(!requestHeaders.isEmpty()){
            for(Map.Entry<String, String> entry : requestHeaders.entrySet()){
                httpGet.addHeader(entry.getKey(),entry.getValue());
            }
        }

//        CloseableHttpClient httpClient = getCloseableHttpClient();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {

            result = EntityUtils.toString(response.getEntity()); //display all
            json = objectMapper.readValue(result, Object.class);

            jsonResponse = new JSONObject(result); // return as json
            setLatestResponse(jsonResponse);

            LOGGER.info(HIGHLIGHT + "RESPONSE BODY" + HIGHLIGHT);
            LOGGER.info(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json)); //pretty
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONObject sendGetRequest(String url){
        LOGGER.info(HIGHLIGHT + "GET URL" + HIGHLIGHT );
        LOGGER.info(url);

        HttpGet httpGet = new HttpGet(url);

//        CloseableHttpClient httpClient = getCloseableHttpClient();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {

            result = EntityUtils.toString(response.getEntity()); //display all
            json = objectMapper.readValue(result, Object.class);

            jsonResponse = new JSONObject(result); // return as json
            setLatestResponse(jsonResponse);

            LOGGER.info(HIGHLIGHT + "RESPONSE BODY" + HIGHLIGHT);
            LOGGER.info(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json)); //pretty

        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonResponse;
    }
}
