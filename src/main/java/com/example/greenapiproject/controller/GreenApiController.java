package com.example.greenapiproject.controller;

import com.example.greenapiproject.service.GreenApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class GreenApiController {

    private final GreenApiService greenApiService;

    @Autowired
    public GreenApiController(GreenApiService greenApiService) {
        this.greenApiService = greenApiService;
    }
    @GetMapping("/getSettings")
    public String getSettings(@RequestParam String idInstance, @RequestParam String apiTokenInstance, Model model) {
        String result = greenApiService.getSettings(idInstance, apiTokenInstance);
        model.addAttribute("result", result);
        return "index";
    }

    @GetMapping("/getStateInstance")
    public String getStateInstance(@RequestParam String idInstance, @RequestParam String apiTokenInstance, Model model) {
        String result = greenApiService.getStateInstance(idInstance, apiTokenInstance);
        model.addAttribute("result", result);
        return "index";
    }

    @PostMapping("/sendMessage")
    public String sendMessage(@RequestParam String idInstance, @RequestParam String apiTokenInstance, @RequestParam String chatId, @RequestParam String message, Model model) {
        String result = greenApiService.sendMessage(idInstance, apiTokenInstance, chatId, message);
        model.addAttribute("result", result);
        return "index";
    }

    @PostMapping("/sendFileByUrl")
    public String sendFileByUrl(@RequestParam String idInstance, @RequestParam String apiTokenInstance, @RequestParam String chatId, @RequestParam String urlFile,@RequestParam String fileName, Model model) {
        String result = greenApiService.sendFileByUrl(idInstance, apiTokenInstance, chatId, urlFile, fileName);
        model.addAttribute("result", result);
        return "index";
    }
}
