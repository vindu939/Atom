package com.atom.client;

import com.atom.telegrambot.model.Message;
import com.atom.telegrambot.model.Update;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

/**
 * Created by aravindp on 21/6/16.
 */
@Controller
@RequestMapping("/")
public class WebController {

    @RequestMapping(value = "webhook", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public int webhook(@RequestHeader HttpHeaders headers, @RequestBody Map data){

        if(data.keySet().contains("object") && data.keySet().contains("entry")){
            System.out.println("Got message from fb");
            return 200;
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonData = mapper.writeValueAsString(data);
            Update update = mapper.readValue(jsonData, Update.class);
            Message message = update.getMessage();

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return 200;
    }


    /**
     * For validating fb messenger webhook
     * @param headers
     * @param hubMode
     * @param hubVerifyToken
     * @param hubChallenge
     * @return
     */
    @RequestMapping(value = "webhook", method = RequestMethod.GET)
    @ResponseBody
    public String webhook(@RequestHeader HttpHeaders headers,
                          @RequestParam("hub.challenge") String hubMode,
                          @RequestParam("hub.challenge") String hubVerifyToken,
                          @RequestParam("hub.challenge") String hubChallenge){
        if (hubMode.equals("subscribe") && hubVerifyToken.equals("12345")) {
            return hubChallenge;
        }
        return null;
    }
}
