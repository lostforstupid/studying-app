package com.leti.flashcards.group;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Transactional
@Service
public class GroupService {

    private GroupRepository groupRepository;

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @SneakyThrows
    public Group getGroup(Long groupId) {
        Optional<Group> groupOptional = groupRepository.findById(groupId);
        Group group;
        if (groupOptional.isPresent()) {
            group = groupOptional.get();
        } else {
            throw new Exception("Group with id " + groupId + " not found");
        }
        return group;
    }

    @SneakyThrows
    public void createGroup(Group newGroup) {
        String groupName = newGroup.getName();
        Optional<Group> group = groupRepository.findByName(groupName);
        if (!group.isPresent()) {
            groupRepository.save(newGroup);
        } else {
            throw new Exception("Group with name '" + groupName + "' already exists");
        }
    }
}
