package com.leti.flashcards.card;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/cards")
@RestController
public class CardController {

    private CardService cardService;

    @GetMapping
    public List<Card> getAllCards() {
        return cardService.getAllCards();
    }

    @PostMapping
    public void saveCards(CardForm cardForm) {
        cardService.saveCards(cardForm);
    }
}
