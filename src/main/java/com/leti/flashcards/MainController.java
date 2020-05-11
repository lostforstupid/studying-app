package com.leti.flashcards;

import com.leti.flashcards.card.CardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@AllArgsConstructor
@Controller
public class MainController {

    private final static String CARDS = "cards";

    private CardService cardService;

    @GetMapping
    public String getMainPage() {
        return "main-page";
    }

    @GetMapping("/all-cards")
    public String getAllCardsPage(Model model) {
        return "all-cards";
    }
}
