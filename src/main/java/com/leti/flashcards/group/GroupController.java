package com.leti.flashcards.group;

import com.leti.flashcards.user.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/groups")
@RestController
public class GroupController {

    private GroupService groupService;

    @GetMapping("/all")
    public List<Group> getAllGroups() {
        return groupService.getAllGroups();
    }

    @PostMapping
    public void createGroup(GroupForm groupForm) {
        Group group = new Group();
        group.setName(groupForm.getName());
        group.setDescription(groupForm.getDescription());
        group.setUser(getCurrentUser());
        groupService.createGroup(group);
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
}
