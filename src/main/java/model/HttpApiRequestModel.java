package model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLContextBuilder;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Random;

public class HttpApiRequestModel {


    private static final Logger LOGGER = LogManager.getLogger(HttpApiRequestModel.class);
    protected static final String HIGHLIGHT = "=============";

    private Map<String, String> requestHeader;
    private Map<String, String> requestParams;
    private JSONObject latestRequest;
    private JSONObject latestResponse;

    public Map<String, String> getRequestHeader() {
        return requestHeader;
    }
    public void setRequestHeader(Map<String, String> requestHeader) {
        this.requestHeader = requestHeader;
    }

    public Map<String, ?> getRequestParams() {
        return requestParams;
    }
    public void setRequestParams(Map requestParams) {
        this.requestParams = requestParams;
    }

    /**
     *
     * @param jsonResponse response as Json
     * @param fieldName target parameter
     * @return parameter and field name
     */
    public String getJsonData (JSONObject jsonResponse, String fieldName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = mapper.readValue(jsonResponse.toString(), Map.class);
        return map.get(fieldName);
    }

    public int getIntJsonData (JSONObject jsonResponse, String fieldName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Integer> map = mapper.readValue(jsonResponse.toString(), Map.class);
        return map.get(fieldName);
    }

    public JSONObject getLatestResponse() {
        return latestResponse;
    }
    public void setLatestResponse(JSONObject latestResponse) {
        this.latestResponse = latestResponse;
    }

}
