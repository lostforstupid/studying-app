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

    public List<Card> getQuickTestCards() {
        return getAllCards().stream()
                .filter(card -> card.getLastInterval() == 0)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    public Card getCard(Long id) {
        return cardRepository.findById(id).orElseThrow(() -> new Exception("No card with such id"));
    }

    public void createCard(CardForm cardForm) {
        Card card = new Card();
        fillCardFields(card, cardForm);
        save(card);
    }

    public void updateCard(CardForm cardForm, Long cardId) {
        Card card = getCard(cardId);
        fillCardFields(card, cardForm);
        save(card);
    }

    public void save(Card card) {
        cardRepository.save(card);
    }

    public void delete(Long cardId) {
        cardRepository.deleteById(cardId);
    }

    private void fillCardFields(Card card, CardForm cardForm) {
        String front = cardForm.getFront();
        String cardFront = card.getFront();
        String back = cardForm.getBack();
        String cardBack = card.getBack();
        if (!front.equalsIgnoreCase(cardFront) || !back.equalsIgnoreCase(cardBack)) {
            card.setFront(front);
            card.setBack(back);
            card.setNextStudySessionTime(new Date());
            card.setLastInterval(0);
        }
        card.setGroup(groupService.getGroup(cardForm.getGroupId()));
    }
}
