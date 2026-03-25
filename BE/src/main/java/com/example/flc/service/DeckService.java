package com.example.flc.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Deck> getDeckCommunity(Pageable pageable) {
        return this.deckRepository.findPublicActiveDeck(pageable);
    }

    public Page<Deck> getDeckCourse(Pageable pageable) {
        return this.deckRepository.findDeckCourse(pageable);
    }

    public void handelSaveDeck(Deck deck) {
        this.deckRepository.save(deck);
        return;
    }

    public Deck getDeckById(long id) {
        return this.deckRepository.findById(id).orElseThrow(() -> new RuntimeException(" Khong tim thay deck"));
    }

    public Page<DeckProgress> getDecksWithProgress(Long userId, Pageable pageable) {
        Page<Object[]> results = deckRepository.findDeckProgressRaw(userId, pageable);

        return results.map(result -> new DeckProgress(
                ((Number) result[0]).longValue(),
                (String) result[1],
                (String) result[2],
                (String) result[3],
                (String) result[4],
                (String) result[5],
                ((Number) result[6]).longValue(),
                ((Number) result[7]).longValue(),
                ((Number) result[8]).longValue()));
    }

    public Page<Deck> getAllDeck(Pageable pageable) {
        return this.deckRepository.findAll(pageable);
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
