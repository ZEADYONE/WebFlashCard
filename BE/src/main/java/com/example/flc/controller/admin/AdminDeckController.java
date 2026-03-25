package com.example.flc.controller.admin;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.flc.domain.Deck;
import com.example.flc.domain.User;
import com.example.flc.service.CardService;
import com.example.flc.service.DeckService;
import com.example.flc.service.UploadService;
import com.example.flc.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminDeckController {

    private final DeckService deckService;
    private final CardService cardService;
    private final UploadService uploadService;
    private final UserService userService;

    public AdminDeckController(DeckService deckService, CardService cardService, UploadService uploadService,
            UserService userService) {
        this.deckService = deckService;
        this.cardService = cardService;
        this.uploadService = uploadService;
        this.userService = userService;
    }

    // HOME
    @GetMapping("/deck")
    public String getHomePage(Model model) {
        model.addAttribute("listDeck", this.deckService.getAllDeck());
        return "admin/deck/homepage";
    }

    // DELETE
    @PostMapping("/deck/status/{id}")
    public String deleteDeck(@PathVariable("id") long id) {
        this.deckService.setDeckStatus(id);
        return "redirect:/admin/deck";
    }

    // VIEW

    @GetMapping("/deck/view/{id}")
    public String getViewDeck(@PathVariable("id") long id, Model model) {

        Deck deck = this.deckService.getDeckById(id);
        model.addAttribute("deck", deck);
        model.addAttribute("listCard", this.cardService.getAllCardByDeck(deck));
        return "admin/deck/view";
    }

    // UPDATE

    @GetMapping("/deck/update/{id}")
    public String getUpdateDeck(@PathVariable("id") long id, Model model) {

        Deck deck = this.deckService.getDeckById(id);
        System.out.println(deck);
        model.addAttribute("deck", deck);
        model.addAttribute("listCard", this.cardService.getAllCardByDeck(deck));
        return "admin/deck/update";
    }

    @PostMapping("/deck/update/{id}")
    public String updateDeck(@PathVariable("id") long id, // Lấy ID từ URL cho chắc chắn
            @ModelAttribute("deck") Deck deck,
            @RequestParam("images") MultipartFile file,
            Principal principal) {

        Deck currentDeck = this.deckService.getDeckById(id);

        if (currentDeck != null) {
            currentDeck.setTitle(deck.getTitle());
            currentDeck.setDes(deck.getDes());
            currentDeck.setScope(deck.getScope());

            String pathImg = this.uploadService.handleSaveUploadImg(file, "client");
            if (!pathImg.isEmpty()) {
                currentDeck.setImage(pathImg);
            }

            this.deckService.handelSaveDeck(currentDeck);
        }

        return "redirect:/admin/deck/update/" + id;
    }
}
