package com.leti.flashcards.card;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Card {
    private Long id;
    private String name;
    private String explanation;
    // groups (many to many)
}
