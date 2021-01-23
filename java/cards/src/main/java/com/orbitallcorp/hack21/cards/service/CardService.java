package com.orbitallcorp.hack21.cards.service;

import com.orbitallcorp.hack21.cards.domains.Card;
import com.orbitallcorp.hack21.cards.exceptionhandler.BadRequestException;
import com.orbitallcorp.hack21.cards.repositories.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;

    public Page<Card> listAll(Pageable pageable){
        return cardRepository.findAll(pageable);
    }

    public List<Card> listAllNonPageable(){
        return cardRepository.findAll();
    }

    public Card findByIdOrThrowBadRequestException(Long id){
        return cardRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Card not found"));
    }

    @Transactional
    public Card save(Card card){
        return cardRepository.save(card);
    }

    public void delete(Long id){
        cardRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(Card card){
        cardRepository.save(card);
    }
}
