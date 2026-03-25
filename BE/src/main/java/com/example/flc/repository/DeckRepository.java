package com.example.flc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.flc.domain.Deck;
import java.util.List;

@Repository
public interface DeckRepository extends JpaRepository<Deck, Long> {
    @Query("SELECT d FROM Deck d WHERE d.user.id = :userId AND d.status = true")
    List<Deck> findByUserId(@Param("userId") Long userId);

    List<Deck> findByScope(String scope);

    @Query("SELECT d FROM Deck d WHERE d.status = true AND d.scope = 'Public'")
    List<Deck> findPublicActiveDeck();

    @Query(value = "SELECT d.id, d.title, d.image, d.des, d.scope, u.user_name, " +
            "COUNT(c.id) AS totalCards, " +
            "COUNT(CASE WHEN p.is_correct = 1 THEN 1 END) AS correctCards, " +
            "d.user_id AS user_id " + // 👈 thêm alias cho dễ dùng
            "FROM Deck d " +
            "JOIN User u ON d.user_id = u.id " +
            "LEFT JOIN Card c ON c.deck_id = d.id " +
            "LEFT JOIN Progress p ON p.card_id = c.id AND p.user_id = :userId " +
            "WHERE ((d.user_id = :userId) OR ( d.scope = 'Public' AND d.user_id != :userId))  AND d.status = true  " +
            "GROUP BY d.id, d.title, d.image, d.des, d.scope, u.user_name, d.user_id", nativeQuery = true)
    List<Object[]> findDeckProgressRaw(@Param("userId") Long userId);
}
