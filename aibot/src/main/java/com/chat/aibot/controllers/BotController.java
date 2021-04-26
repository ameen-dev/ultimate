package com.chat.aibot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chat.aibot.constants.Constants;
import com.chat.aibot.services.BotService;

@Controller
@RequestMapping("/aibot")
public class BotController {
	
	@Autowired
	BotService service;
	
	@GetMapping("/chat")
	@ResponseBody
	public String getResponse(@RequestParam String botId, @RequestParam String message){
		if (botId == null || botId.equals("")) {
			return Constants.ID_NOT_AVAILABLE;
		}
		if (message == null || message.equals("")) {
			return Constants.MESSAGE_NOT_AVAILABLE;
		}
	    return service.getResponse(botId, message);
	}

}
