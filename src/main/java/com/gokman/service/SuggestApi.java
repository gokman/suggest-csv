package com.gokman.service;

import com.gokman.model.Suggest;
import org.springframework.http.ResponseEntity;


public interface SuggestApi {

    //------------------------------------------------------------------------------------------------------------------

    String[] mapSuggestToString(Suggest[] suggests);



    //------------------------------------------------------------------------------------------------------------------

    ResponseEntity<Suggest[]> getSuggestEntity(String cityName);



    //------------------------------------------------------------------------------------------------------------------

    boolean isParameterTrue(String... parameters);



    //------------------------------------------------------------------------------------------------------------------
}
