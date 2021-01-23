package com.orbitallcorp.hack21.cards.repositories;

import com.orbitallcorp.hack21.cards.domains.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {

}