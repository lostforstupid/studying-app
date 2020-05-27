package com.leti.flashcards.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CardForm {
    private String front;
    private String back;
    private Long groupId;
}
