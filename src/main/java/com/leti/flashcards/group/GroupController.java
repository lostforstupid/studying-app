package com.leti.flashcards.group;

import com.leti.flashcards.card.CardService;
import com.leti.flashcards.user.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RequestMapping("/groups")
@RestController
public class GroupController {

    private GroupService groupService;
    private CardService cardService;

    @GetMapping("/all")
    @ResponseBody
    public String getAllGroups() {
        List<String> groupsAsStrings = groupService.getAllGroups().stream()
                .map(group -> new GroupView(group.getId(), group.getName(), group.getDescription()))
                .map(GroupView::toString)
                .collect(Collectors.toList());
        StringBuilder builder = new StringBuilder("[");
        for (String groupAsString : groupsAsStrings) {
            builder.append(groupAsString)
                    .append(", ");
        }
        String result = builder.substring(0, builder.length() - 2);
        return result + "]";
    }

    @GetMapping("/has-cards/{groupId}")
    public boolean isGroupHasCards(@PathVariable Long groupId) {
        return cardService.getCardsByGroup(groupId).size() > 0;
    }

    @PostMapping
    public void createGroup(GroupForm groupForm) {
        Group group = new Group();
        group.setName(groupForm.getName());
        group.setDescription(groupForm.getDescription());
        group.setUser(getCurrentUser());
        groupService.createGroup(group);
    }

    @PutMapping("/{groupId}")
    public void updateGroup(@PathVariable Long groupId, GroupForm groupForm) {
        groupService.updateGroup(groupId, groupForm);
    }

    @DeleteMapping("/{groupId}")
    public void deleteGroup(@PathVariable Long groupId) {
        groupService.deleteGroup(groupId);
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
}
