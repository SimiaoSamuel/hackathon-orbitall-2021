package com.orbitallcorp.hack21.cards.controller;

import com.orbitallcorp.hack21.cards.domains.Card;
import com.orbitallcorp.hack21.cards.service.CardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @GetMapping()
    public ResponseEntity<List<Card>> listAllNonPageable(){
        return ResponseEntity.ok(cardService.listAllNonPageable());
    }

    @GetMapping(path = "/paginationAndSorting")
    public ResponseEntity<Page<Card>> list(Pageable pageable){
        return ResponseEntity.ok(cardService.listAll(pageable));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Card> findById(@PathVariable Long id){
        return ResponseEntity.ok(cardService.findByIdOrThrowBadRequestException(id));
    }

    @PostMapping
    public ResponseEntity<Card> save(@RequestBody Card card){
        return new ResponseEntity<>(cardService.save(card), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        cardService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = {"/{id}"})
    public ResponseEntity<Void> replace(@PathVariable Long id, @RequestBody Card card){
        card.setId(id);
        cardService.replace(card);
        return ResponseEntity.noContent().build();
    }
}
