package com.leti.flashcards.group;

import com.leti.flashcards.card.Card;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "CARD_GROUP")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "card_in_group",
            joinColumns = @JoinColumn(name = "card_group_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id"))
    private List<Card> cards;
}
