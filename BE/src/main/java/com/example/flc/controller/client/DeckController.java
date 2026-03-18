package com.example.flc.controller.client;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.flc.domain.Deck;
import com.example.flc.service.UploadService;

import jakarta.servlet.ServletContext;

@Controller
@RequestMapping("/client")
public class DeckController {

    private final UploadService uploadService;

    public DeckController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping("/deck/create")
    public String saveDeck(@ModelAttribute Deck deck, @RequestParam("images") MultipartFile file) {

        this.uploadService.handelSaveUploadFile(file, "client");
        return "redirect:/client/library";
    }

}
