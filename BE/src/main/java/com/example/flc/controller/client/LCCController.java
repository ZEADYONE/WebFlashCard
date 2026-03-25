package com.example.flc.controller.client;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.flc.domain.Deck;
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
    public String getLibraryPage(Model model, Principal principal,
            @RequestParam(value = "page", defaultValue = "1") int page) {
        // Nếu người dùng truyền page=1, Spring cần hiểu là offset 0
        Pageable pageable = PageRequest.of(page - 1, 6);

        String username = principal.getName();
        User user = userRepository.findByEmail(username);
        Long currentUserId = user.getId();

        // Gọi service với pageable
        Page<DeckProgress> pageDeck = deckService.getDecksWithProgress(currentUserId, pageable);
        model.addAttribute("listDeck", pageDeck.getContent()); // Danh sách deck cho trang hiện tại
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageDeck.getTotalPages());
        model.addAttribute("currentUser", currentUserId);

        return "client/library/library";
    }

    // COMMUNITY
    @GetMapping("/community")
    public String getCommunityPage(Model model,
            @RequestParam(value = "page", defaultValue = "1") int page) {

        Pageable pageable = PageRequest.of(page - 1, 6);

        Page<Deck> pageDeck = this.deckService.getDeckCommunity(pageable);

        model.addAttribute("listDeck", pageDeck.getContent()); // Dữ liệu của trang hiện tại
        model.addAttribute("currentPage", page); // Trang hiện tại
        model.addAttribute("totalPages", pageDeck.getTotalPages()); // Tổng số trang

        return "client/community/community";
    }

    // COURSE
    @GetMapping("/course")
    public String getCoursePage(Model model,
            @RequestParam(value = "page", defaultValue = "1") int page) {

        Pageable pageable = PageRequest.of(page - 1, 6);
        Page<Deck> pageDeck = this.deckService.getDeckCourse(pageable);

        model.addAttribute("decks", pageDeck.getContent()); // Dữ liệu của trang hiện tại
        model.addAttribute("currentPage", page); // Trang hiện tại
        model.addAttribute("totalPages", pageDeck.getTotalPages()); // Tổng số trang

        return "client/course/course";
    }
}
