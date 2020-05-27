package com.leti.flashcards.group;

import com.leti.flashcards.card.Card;
import com.leti.flashcards.card.CardService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/groups")
@RestController
public class GroupController {

    private GroupService groupService;
    private CardService cardService;

    @GetMapping("/all")
    public List<Group> getAllGroups() {
        return groupService.getAllGroups();
    }

    @GetMapping("/init")
    public void createGroups() {
        Group group1 = new Group();
        group1.setName("Group 1");
        Group group2 = new Group();
        group2.setName("Group 2");

        groupService.createGroup(group1);
        groupService.createGroup(group2);

        Card card1 = new Card();
        Card card2 = new Card();

        card1.setGroup(group1);
        card2.setGroup(group2);

        cardService.save(card1);
        cardService.save(card2);
    }
}
