package com.example.flc.controller.client;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.flc.domain.User;
import com.example.flc.domain.DTO.DeckProgress;
import com.example.flc.repository.UserRepository;
import com.example.flc.service.DeckService;

@Controller
@RequestMapping("/client")
public class LCCController {

    private final DeckService deckService;
    private final UserRepository userRepository;

    // LIBRARY
    public LCCController(DeckService deckService, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.deckService = deckService;
    }

    @GetMapping("/library")
    public String getLibraryPage(Model model, Principal principal) {

        String username = principal.getName();
        User user = userRepository.findByEmail(username);
        Long currentUserId = user.getId();

        List<DeckProgress> listDeck = deckService.getDecksWithProgress(currentUserId);

        model.addAttribute("listDeck", listDeck);
        model.addAttribute("currentUser", currentUserId);
        return "client/library/library";
    }

    // COMMUNITY
    @GetMapping("/community")
    public String getCommunityPage(Model model) {
        model.addAttribute("listDeck", this.deckService.getDeckCommunity());
        return "client/community/community";
    }

    // COURSE
    @GetMapping("/course")
    public String getCoursePage() {
        return "client/course/course";
    }
}
