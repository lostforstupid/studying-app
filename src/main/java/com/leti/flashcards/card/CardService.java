package com.leti.flashcards.card;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CardService {

    private CardRepository cardRepository;

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }
}
