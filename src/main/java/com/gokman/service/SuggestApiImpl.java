package com.gokman.service;

import com.gokman.Application;
import com.gokman.model.Output;
import com.gokman.model.Suggest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class SuggestApiImpl implements SuggestApi{


    //------------------------------------------------------------------------------------------------------------------

    private static final Logger log         = LoggerFactory.getLogger(Application.class);
    private static final String SUGGEST_API = "http://api.goeuro.com/api/v2/position/suggest/en/";



    //------------------------------------------------------------------------------------------------------------------

    public String[] mapSuggestToString(Suggest[] suggests) {
        String[]  outputsAsString = new String[suggests.length];

        for(int i = 0; i < suggests.length; i++){
            Output output = new Output(suggests[i].get_id(),
                    suggests[i].getName(),
                    suggests[i].getType(),
                    suggests[i].getGeo_position().getLatitude(),
                    suggests[i].getGeo_position().getLongitude());
            outputsAsString[i] = output.toString();
        }
        return outputsAsString;
    }



    //------------------------------------------------------------------------------------------------------------------

    public ResponseEntity<Suggest[]> getSuggestEntity(String cityName) {

        RestTemplate restTemplate      = new RestTemplate();
        ResponseEntity<Suggest[]> responseEntity    = restTemplate.getForEntity(SUGGEST_API + cityName, Suggest[].class);

        return responseEntity;
    }



    //------------------------------------------------------------------------------------------------------------------

    public boolean isParameterTrue(String... parameters){

        if (parameters.length > 1) {
            log.error("only 1 parameter allowed");
            return false;
        } else if (parameters.length == 0) {
            log.error("1 parameter required");
            return false;
        }else{
            return true;
        }

    }



    //------------------------------------------------------------------------------------------------------------------

}
