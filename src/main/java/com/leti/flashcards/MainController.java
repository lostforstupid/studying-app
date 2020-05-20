package com.leti.flashcards;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@AllArgsConstructor
@Controller
public class MainController {

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
}