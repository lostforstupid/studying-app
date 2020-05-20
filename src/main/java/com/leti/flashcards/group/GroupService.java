package com.leti.flashcards.group;

import com.leti.flashcards.card.Card;
import com.leti.flashcards.card.CardService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class GroupService {

    private GroupRepository groupRepository;
    private CardService cardService;

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @Transactional
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

    @Transactional
    @SneakyThrows
    public void addCardToGroup(Long cardId, Long groupId) {
        Group group = getGroup(groupId);
        Card card = cardService.getCard(cardId);
        group.getCards().add(card);
        groupRepository.save(group);
    }

    @Transactional
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
