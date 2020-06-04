package com.leti.flashcards;

import com.leti.flashcards.card.Card;
import com.leti.flashcards.card.CardService;
import com.leti.flashcards.group.Group;
import com.leti.flashcards.group.GroupService;
import com.leti.flashcards.group.GroupView;
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

import java.util.List;

@RequestMapping("/")
@AllArgsConstructor
@Controller
public class MainController {

    private static String CARDS = "cards";
    private static String EXISTING_GROUP = "existingGroup";
    private static String EXISTING_CARDS = "existingCards";

    private CardService cardService;
    private GroupService groupService;

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

    @GetMapping("edit-group/{groupId}")
    public String getEditGroupPage(Model model, @PathVariable Long groupId) {
        Group group = groupService.getGroup(groupId);
        GroupView groupView = new GroupView(group.getId(), group.getName(), group.getDescription());
        model.addAttribute(EXISTING_GROUP, groupView);
        return "create-group";
    }

    @GetMapping("/edit-cards/{groupId}")
    public String getEditCardsPage(Model model, @PathVariable Long groupId) {
        List<Card> cardsByGroup = cardService.getCardsByGroup(groupId);
        model.addAttribute(EXISTING_CARDS, cardsByGroup);
        return "create-cards";
    }

    @GetMapping("user-name-url")
    @ResponseBody
    public String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user.getName();
    }

    @GetMapping("user-img-url")
    @ResponseBody
    public String getUserImageUrl() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user.getProfilePictureUrl();
    }
}
