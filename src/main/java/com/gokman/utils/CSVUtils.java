package com.gokman.utils;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class CSVUtils {


    //--------------------------------------------------------------------------------------------------------------

    private static final String DEFAULT_SEPARATOR = ",";



    //--------------------------------------------------------------------------------------------------------------

    private static String followCVSformat(String value) {

        String result = value;
        if (result.contains("\"")) {
            result = result.replace("\"", "\"\"");
        }
        return result;

    }



    //--------------------------------------------------------------------------------------------------------------

    public static void writeLine(Writer w, List<String> values, String separators) throws IOException {

        boolean first = true;

        if (separators.equals("")) {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuilder sb = new StringBuilder();
        for (String value : values) {
            if (!first) {
                sb.append(separators);
            }

            sb.append(followCVSformat(value));

            first = false;
        }
        sb.append("\n");
        w.append(sb.toString());


    }



    //--------------------------------------------------------------------------------------------------------------

}