package com.example.flc.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.flc.domain.Deck;
import com.example.flc.domain.User;
import com.example.flc.domain.DTO.DeckProgress;
import com.example.flc.repository.DeckRepository;

@Service
public class DeckService {

    private final DeckRepository deckRepository;

    public DeckService(DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
    }

    public List<Deck> getDeckLibrary(long id) {
        return this.deckRepository.findByUserId(id);
    }

    public List<Deck> getDeckCommunity() {
        return this.deckRepository.findPublicActiveDeck();
    }

    public void handelSaveDeck(Deck deck) {
        this.deckRepository.save(deck);
        return;
    }

    public Deck getDeckById(long id) {
        return this.deckRepository.findById(id).orElseThrow(() -> new RuntimeException(" Khong tim thay deck"));
    }

    public List<DeckProgress> getDecksWithProgress(Long userId) {
        List<Object[]> results = deckRepository.findDeckProgressRaw(userId);

        return results.stream().map(result -> new DeckProgress(
                ((Number) result[0]).longValue(), // id (vị trí 0)
                (String) result[1], // title (vị trí 1)
                (String) result[2], // image (vị trí 2)
                (String) result[3], // des (vị trí 3)
                (String) result[4], // scope (vị trí 4)
                (String) result[5], // userName (vị trí 5)
                ((Number) result[6]).longValue(), // totalCards (vị trí 6)
                ((Number) result[7]).longValue(),
                ((Number) result[8]).longValue()// correctCards (vị trí 7)
        )).collect(Collectors.toList());
    }

    public List<Deck> getAllDeck() {
        return this.deckRepository.findAll();
    }

    public void setDeckStatus(long id) {
        Deck deck = this.deckRepository.findById(id).orElseThrow(() -> new RuntimeException());
        if (deck.getStatus()) {
            deck.setStatus(false);
        } else {
            deck.setStatus(true);
        }
        handelSaveDeck(deck);
        return;
    }
}
