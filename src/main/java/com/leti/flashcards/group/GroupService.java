package com.leti.flashcards.group;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Service
public class GroupService {

    public List<Group> getAllGroups() {
        return Collections.singletonList(new Group(1L, "Group 1"));
    }
}
