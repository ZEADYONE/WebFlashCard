package com.example.flc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.flc.domain.Deck;
import java.util.List;

@Repository
public interface DeckRepository extends JpaRepository<Deck, Long> {
        @Query("SELECT d FROM Deck d WHERE d.user.id = :userId AND d.status = true")
        List<Deck> findByUserId(@Param("userId") Long userId);

        @Query("SELECT d FROM Deck d WHERE " +
                        "(:keyword IS NULL OR d.title LIKE %:keyword%) AND " +
                        "(:status IS NULL OR d.status IN :status)")
        Page<Deck> findAllWithFilter(@Param("keyword") String keyword,
                        @Param("status") List<Boolean> status,
                        Pageable pageable);

        List<Deck> findByScope(String scope);

        // COMMUNITY
        @Query("SELECT DISTINCT d FROM Deck d " +
                        "WHERE d.status = true AND d.scope = 'Public'" +
                        "AND (:keyword IS NULL OR d.title LIKE %:keyword%)")
        Page<Deck> findPublicActiveDeck(@Param("keyword") String keywork, Pageable pageable);

        // COURSE
        @Query("SELECT d FROM Deck d " +
                        "JOIN d.user u " +
                        "JOIN u.role r " +
                        "WHERE d.status = true " +
                        "AND d.scope = 'Public' " +
                        "AND r.name = 'ADMIN'" +
                        "AND (:keyword IS NULL OR d.title LIKE %:keyword%)")
        Page<Deck> findDeckCourse(@Param("keyword") String keyword, Pageable pageable);

        // LIBRARY
        @Query(value = "SELECT d.id, d.title, d.image, d.des, d.scope, u.user_name, " +
                        "COUNT(DISTINCT c.id) AS totalCards, " +
                        "COUNT(DISTINCT CASE WHEN p.is_correct = 1 THEN c.id END) AS correctCards, " +
                        "d.user_id " +
                        "FROM Deck d " +
                        "JOIN User u ON d.user_id = u.id " +
                        "JOIN Role r ON u.role_id = r.id " +
                        "LEFT JOIN Card c ON c.deck_id = d.id " +
                        "LEFT JOIN Progress p ON p.card_id = c.id AND p.user_id = :userId " +
                        "WHERE (:keyword IS NULL OR d.title LIKE CONCAT('%', :keyword, '%')) " +
                        "AND d.status = true " +
                        "AND (" +
                        "    'ALL' IN :filters OR ( " + // <--- SỬA DÒNG NÀY
                        "        ('Public' IN :filters AND d.scope = 'Public') OR " +
                        "        ('Private' IN :filters AND d.scope = 'Private') OR " +
                        "        ('Mine' IN :filters AND d.user_id = :userId) OR " +
                        "        ('Admin' IN :filters AND r.name = 'ADMIN') OR " +
                        "        ('Other' IN :filters AND d.user_id != :userId AND r.name != 'ADMIN') " +
                        "    )" +
                        ") " +
                        "AND (d.user_id = :userId OR ( d.user_id != :userId AND d.scope = 'Public' AND p.id IS NOT NULL)) "
                        +
                        "GROUP BY d.id, d.title, d.image, d.des, d.scope, u.user_name, d.user_id", countQuery = "SELECT COUNT(DISTINCT d.id) FROM Deck d "
                                        +
                                        "JOIN User u ON d.user_id = u.id JOIN Role r ON u.role_id = r.id " +
                                        "WHERE (:keyword IS NULL OR d.title LIKE CONCAT('%', :keyword, '%')) " +
                                        "AND d.status = true " +
                                        "AND ('ALL' IN :filters OR ( " + // <--- SỬA DÒNG NÀY TRONG COUNT QUERY NỮA
                                        "    ('Public' IN :filters AND d.scope = 'Public') OR " +
                                        "    ('Private' IN :filters AND d.scope = 'Private') OR " +
                                        "    ('Mine' IN :filters AND d.user_id = :userId) OR " +
                                        "    ('Admin' IN :filters AND r.name = 'ADMIN') OR " +
                                        "    ('Other' IN :filters AND d.user_id != :userId AND r.name != 'ADMIN') " +
                                        ")) " +
                                        "AND (d.user_id = :userId OR d.scope = 'Public')", nativeQuery = true)
        Page<Object[]> findAdvancedDecks(@Param("keyword") String keyword,
                        @Param("filters") List<String> filters,
                        @Param("userId") Long userId,
                        Pageable pageable);
}

// @Query(value = "SELECT d.id, d.title, d.image, d.des, d.scope, u.user_name, "
// +

// "COUNT(c.id) AS totalCards, " +

// "COUNT(CASE WHEN p.is_correct = 1 THEN 1 END) AS correctCards, " +

// "d.user_id AS user_id " + // 👈 thêm alias cho dễ dùng

// "FROM Deck d " +

// "JOIN User u ON d.user_id = u.id " +

// "LEFT JOIN Card c ON c.deck_id = d.id " +

// "LEFT JOIN Progress p ON p.card_id = c.id AND p.user_id = :userId " +

// "WHERE ((d.user_id = :userId) OR ( d.scope = 'Public' AND d.user_id !=
// :userId)) AND d.status = true " +

// "GROUP BY d.id, d.title, d.image, d.des, d.scope, u.user_name, d.user_id",
// nativeQuery = true)

// List<Object[]> findDeckProgressRaw(@Param("userId") Long userId)