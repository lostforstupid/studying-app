package com.leti.flashcards.group;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/cards")
@RestController
public class GroupController {

    private GroupService groupService;

    @GetMapping
    public List<Group> getAllGroups() {
        return groupService.getAllGroups();
    }
}
