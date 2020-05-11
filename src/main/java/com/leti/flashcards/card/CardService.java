package com.leti.flashcards.card;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Service
public class CardService {

    public List<Card> getAllCards() {
        return Arrays.asList(new Card(1L, "Name 1", "Explanation 1"),
                new Card(2L, "Name 2", "Explanation 2"));
    }
}
