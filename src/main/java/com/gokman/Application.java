package com.gokman;


import com.gokman.model.Suggest;
import com.gokman.service.SuggestApiImpl;
import com.gokman.utils.CSVUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Console;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;


@SpringBootApplication
public class Application implements CommandLineRunner {


    //------------------------------------------------------------------------------------------------------------------

    private static final Logger log         = LoggerFactory.getLogger(Application.class);



    //------------------------------------------------------------------------------------------------------------------

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }



    //------------------------------------------------------------------------------------------------------------------

    @Override
    public void run(String... args) {

        SuggestApiImpl suggestApiImpl = new SuggestApiImpl();

        if(!suggestApiImpl.isParameterTrue(args)){
            return;
        }

        ResponseEntity<Suggest[]> suggestEntity =  suggestApiImpl.getSuggestEntity(args[0]);

        if (suggestEntity.getStatusCode() != HttpStatus.OK){
            log.error("API error. Check your api  !!!");
            return;
        }

        // API returns only OK status code. So we need to check result instead of http NO_CONTENT status
        Suggest[] suggests = suggestEntity.getBody();

        if (suggests.length == 0){
            log.error("No result !!!");
            return;
        }

        //map suggest to string output
        String[] outputAsString = suggestApiImpl.mapSuggestToString(suggests);

        Console console   = System.console();
        String csvPath    = console.readLine("Please write full path of csv file: ");
        FileWriter writer = null;

        try {
            writer = new FileWriter(csvPath);
            CSVUtils.writeLine(writer, Arrays.asList(outputAsString), "\n");
        } catch (IOException e) {
            log.error("Error while writing to file !!!");
        } finally {
            if(writer != null) {
                try {
                    writer.flush();
                    writer.close();
                } catch (IOException ioException) {
                    log.error("Error while flushing/closing fileWriter !!!");
                }
            }
        }

    }



    //------------------------------------------------------------------------------------------------------------------

}