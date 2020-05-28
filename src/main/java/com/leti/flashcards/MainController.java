package com.leti.flashcards;

import com.leti.flashcards.card.CardService;
import com.leti.flashcards.user.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/")
@AllArgsConstructor
@Controller
public class MainController {

    private static String CARDS = "cards";

    private CardService cardService;

    @GetMapping
    public String getGroupsPage() {
        return "groups";
    }

    @GetMapping("/create-cards")
    public String getCreateCardsPage() {
        return "create-cards";
    }

    @GetMapping("/test/{groupId}")
    public String getTestPage(Model model, @PathVariable Long groupId) {
        model.addAttribute(CARDS, cardService.getCardsByGroup(groupId));
        return "test";
    }

    @GetMapping("create-group")
    public String getCreateGroupPage() {
        return "create-group";
    }

    @GetMapping("user-img-url")
    @ResponseBody
    public String getUserImageUrl() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user.getProfilePictureUrl();
    }
}
