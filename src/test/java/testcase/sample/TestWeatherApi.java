package testcase.sample;

import biz.GetWeather;
import model.HttpApiRequestModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.annotations.Test;
import service.HttpService;
import utility.DataProviderClass;

import java.io.IOException;

public class TestWeatherApi{

    private static final Logger LOGGER = LogManager.getLogger(TestWeatherApi.class);
    protected static final String HIGHLIGHT = "=============";

    @Test(dataProvider = "getDataFromCsv", dataProviderClass = DataProviderClass.class)
    public void testGetWeatherApi(String text) throws IOException {
        GetWeather businessApi = new GetWeather();
        String key = "2f51dd88ca4fe0322687c849095511e8";
        String location = "manila";
        JSONObject response = businessApi.testGetWeatherApi("https://api.openweathermap.org/data/2.5/weather?q=" + text + "&appid=" + key);

        HttpService appResponse = new HttpService();
        LOGGER.info(HIGHLIGHT);
        LOGGER.info(appResponse.getJsonData(response,"name"));

    }
}
