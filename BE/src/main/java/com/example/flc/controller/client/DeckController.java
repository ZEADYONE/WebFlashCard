package com.example.flc.controller.client;

import com.example.flc.repository.DeckRepository;
import com.example.flc.repository.UserRepository;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.flc.domain.Deck;
import com.example.flc.domain.User;
import com.example.flc.service.CardService;
import com.example.flc.service.DeckService;
import com.example.flc.service.UploadService;

@Controller
@RequestMapping("/client")
public class DeckController {

    private final DeckRepository deckRepository;
    private final DeckService deckService;
    private final UploadService uploadService;
    private final UserRepository userRepository;
    private final CardService cardService;

    public DeckController(UploadService uploadService, DeckService deckService, UserRepository userRepository,
            CardService cardService, DeckRepository deckRepository) {
        this.uploadService = uploadService;
        this.deckService = deckService;
        this.userRepository = userRepository;
        this.cardService = cardService;
        this.deckRepository = deckRepository;
    }

    // CREATE
    @PostMapping("/deck/create")
    public String createDeck(
            @ModelAttribute("deck") Deck deck,
            @RequestParam("images") MultipartFile file,
            Principal principal) {

        String pathImg = this.uploadService.handleSaveUploadImg(file, "client");
        deck.setImage(pathImg);

        String username = principal.getName();
        User user = userRepository.findByEmail(username);

        deck.setUser(user);
        deck.setStatus(true);
        this.deckService.handelSaveDeck(deck);

        return "redirect:/client/library";
    }

    // VIEW
    @GetMapping("/deck/{id}")
    public String getHomeDeck(@PathVariable("id") long id, Model model, Principal principal) {
        Deck deck = this.deckService.getDeckById(id);
        String username = principal.getName();
        User user = userRepository.findByEmail(username);
        Long currentUserId = user.getId();

        model.addAttribute("currentUser", currentUserId);
        model.addAttribute("deck", deck);
        model.addAttribute("listCard", this.cardService.getAllCardByDeck(deck));
        return "client/deck/homepage";
    }

    // UPDATE
    @PostMapping("/deck/update")
    public String updateDeck(@ModelAttribute("deck") Deck deck, @RequestParam("images") MultipartFile file,
            Principal principal) {

        String pathImg = this.uploadService.handleSaveUploadImg(file, "client");

        if (pathImg.isEmpty()) {
            deck.setImage(this.deckService.getDeckById(deck.getId()).getImage());
        } else {

            deck.setImage(pathImg);
        }

        String username = principal.getName();
        User user = userRepository.findByEmail(username);

        deck.setUser(user);
        deck.setStatus(true);

        this.deckService.handelSaveDeck(deck);

        return "redirect:/client/library";
    }

    // DELETE
    @PostMapping("/deck/delete/{id}")
    public String deleteDeck(@PathVariable("id") long id) {
        Deck deck = this.deckService.getDeckById(id);
        deck.setStatus(false);
        return "redirect:/client/library";
    }
}
