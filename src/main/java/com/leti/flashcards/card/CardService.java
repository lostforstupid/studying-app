package com.leti.flashcards.card;

import com.leti.flashcards.group.GroupService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CardService {

    private CardRepository cardRepository;
    private GroupService groupService;

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public void saveCards(CardForm cardForm) {
        Card card = new Card();
        card.setFront(cardForm.getFront());
        card.setBack(cardForm.getBack());
        card.setGroup(groupService.getGroup(cardForm.getGroupId()));
        save(card);
    }

    public void save(Card card) {
        cardRepository.save(card);
    }
}
