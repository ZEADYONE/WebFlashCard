package com.example.flc.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.flc.domain.Card;
import com.example.flc.domain.Deck;
import com.example.flc.service.CardService;
import com.example.flc.service.DeckService;
import com.example.flc.service.UploadService;

@Controller
@RequestMapping("/client")
public class CardController {

    private final CardService cardService;
    private final DeckService deckService;
    private final UploadService uploadService;

    public CardController(CardService cardService, DeckService deckService, UploadService uploadService) {
        this.cardService = cardService;
        this.deckService = deckService;
        this.uploadService = uploadService;
    }

    // VIEW

    @GetMapping("/card/view/{id}")
    public String getViewCardPage(@PathVariable("id") long id, Model model) {

        Card card = this.cardService.getCard(id);
        model.addAttribute("card", card);
        return "client/card/view";
    }
    // CREATE

    @GetMapping("/card/create/{deckId}")
    public String getCreateCardPage(@PathVariable("deckId") long deckId, Model model) {
        model.addAttribute("deckId", deckId);
        model.addAttribute("card", new Card());
        return "client/card/create";
    }

    @PostMapping("card/create/{deckId}") // Đổi ở đây
    public String createCard(
            @PathVariable("deckId") long deckId,
            @ModelAttribute("card") Card card,
            @RequestParam("images") MultipartFile img,
            @RequestParam("sounds") MultipartFile sound) {

        Deck deck = this.deckService.getDeckById(deckId);
        card.setDeck(deck);

        // Bây giờ card.getId() sẽ in ra 0 hoặc null vì không bị binding nhầm nữa
        System.out.println(card.getId() + " ID CARD");

        String pathImg = this.uploadService.handleSaveUploadImg(img, "client");
        card.setImage(pathImg);
        System.out.println(pathImg);

        String pathSound = this.uploadService.handleSaveUploadSound(sound, "client");
        card.setSound(pathSound);
        System.out.println(pathSound);

        this.cardService.handelSaveCard(card);
        return "redirect:/client/deck/" + deckId;
    }

    // UPDATE
    @GetMapping("/card/update/{id}")
    public String getUpdateCardPage(@PathVariable("id") long id, Model model) {
        Card card = this.cardService.getCard(id);
        model.addAttribute("card", card);
        return "client/card/update";
    }

    @PostMapping("card/update/{id}")
    public String updateCard(
            @ModelAttribute("card") Card card,
            @RequestParam("images") MultipartFile img,
            @RequestParam("sounds") MultipartFile sound) {

        Card updateCard = this.cardService.getCard(card.getId());

        String pathImg = this.uploadService.handleSaveUploadImg(img, "client");
        if (!pathImg.isEmpty()) {
            card.setImage(pathImg);
        } else {
            card.setImage(updateCard.getImage());
        }

        String pathSound = this.uploadService.handleSaveUploadSound(sound, "client");
        if (!pathSound.isEmpty()) {
            card.setSound(pathSound);
        } else {
            card.setSound(updateCard.getSound());
        }

        card.setDeck(updateCard.getDeck());

        this.cardService.handelSaveCard(card);
        return "redirect:/client/deck/" + updateCard.getDeck().getId();
    }

    // DELETE

    @PostMapping("/card/delete/{id}")
    public String deleteCard(@PathVariable("id") long id) {
        Card card = this.cardService.getCard(id);
        long deckId = card.getDeck().getId();
        this.cardService.deleteCard(card.getId());
        return "redirect:/client/deck/" + deckId;
    }
}
