package com.leti.flashcards.group;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Group {
    private Long id;
    private String name;
    // cards (many to many)
}
