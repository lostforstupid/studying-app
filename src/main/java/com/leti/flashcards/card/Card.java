package com.leti.flashcards.card;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leti.flashcards.group.Group;
import com.leti.flashcards.side.Side;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "CARD")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "card", fetch = FetchType.EAGER)
    private List<Side> sides;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @JsonIgnore
    private Group group;
}
