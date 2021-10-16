package biz;

import model.HttpApiRequestModel;
import org.json.JSONObject;
import service.HttpService;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

public class GetWeather extends HttpService {
    public JSONObject testGetWeatherApi(String url){
        HttpApiRequestModel httpRequest = new HttpApiRequestModel();
        HttpService sendRequest = new HttpService();
        return sendRequest.sendGetRequest(url);
    }

}
