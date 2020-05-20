package com.leti.flashcards.side;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leti.flashcards.card.Card;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "SIDE")
public class Side {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private int orderOnCard;

    @ManyToOne
    @JoinColumn(name = "card_id")
    @JsonIgnore
    private Card card;
}
