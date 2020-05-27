package com.leti.flashcards;

import com.leti.flashcards.card.CardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@AllArgsConstructor
@Controller
public class MainController {

    private static String CARDS = "cards";

    private CardService cardService;

    @GetMapping
    public String getMainPage() {
        return "main-page";
    }

    @GetMapping("/all-cards")
    public String getAllCardsPage() {
        return "all-cards";
    }

    @GetMapping("/groups")
    public String getGroupsPage() {
        return "groups";
    }

    @GetMapping("/create-cards")
    public String getCreateCardsPage() {
        return "create-cards";
    }

    @GetMapping("/test/{groupId}")
    public String getTest(Model model, @PathVariable Long groupId) {
        model.addAttribute(CARDS, cardService.getCardsByGroup(groupId));
        return "test";
    }
}
