package com.example.flc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.flc.domain.Card;
import com.example.flc.domain.Deck;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    public Page<Card> findByDeck(Deck deck, Pageable pageable);

    public List<Card> findByDeck(Deck deck);
}
