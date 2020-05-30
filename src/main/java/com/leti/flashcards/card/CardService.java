package com.leti.flashcards.card;

import com.leti.flashcards.group.GroupService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Transactional
@Service
public class CardService {

    private CardRepository cardRepository;
    private GroupService groupService;

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public List<Card> getCardsByGroup(Long groupId) {
        return getAllCards().stream()
                .filter(card -> groupId.equals(card.getGroup().getId()))
                .filter(card -> card.getNextStudySessionTime().before(new Date()))
                .collect(Collectors.toList());
    }

    @SneakyThrows
    public Card getCard(Long id) {
        return cardRepository.findById(id).orElseThrow(() -> new Exception("No card with such id"));
    }

    public void saveCards(CardForm cardForm) {
        Card card = new Card();
        card.setFront(cardForm.getFront());
        card.setBack(cardForm.getBack());
        card.setNextStudySessionTime(new Date());
        card.setAmountCorrectAnswers(0);
        card.setGroup(groupService.getGroup(cardForm.getGroupId()));
        save(card);
    }

    public void save(Card card) {
        cardRepository.save(card);
    }
}
