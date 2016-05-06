package com.com.Json.Parser;

import org.codehaus.jettison.json.JSONObject;
import org.mortbay.util.ajax.JSON;

/**
 * Created by elf on 04.05.2016.
 */
public class ConvertJSON {

    public static JSONObject stringToJson(String txtJson){
        try {
            JSONObject obj = new JSONObject(txtJson);
            return obj;
        }catch (Exception e)
        {
        return null;

        }
    }

}
