package com.leti.flashcards.card;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@RequestMapping("/cards")
@RestController
public class CardController {

    private CardService cardService;

    @GetMapping("/{groupId}")
    public List<Card> getCardsByGroup(@PathVariable Long groupId) {
        return cardService.getCardsByGroup(groupId);
    }

    @PostMapping
    public void saveCard(CardForm cardForm) {
        cardService.createCard(cardForm);
    }

    @PutMapping("/{cardId}")
    public void updateCard(CardForm cardForm, @PathVariable Long cardId) {
        cardService.updateCard(cardForm, cardId);
    }

    @DeleteMapping("/{cardId}")
    public void deleteCard(@PathVariable Long cardId) {
        cardService.delete(cardId);
    }

    @PostMapping("/save-correct-test-result/{cardId}")
    public void saveCorrectTestResult(@PathVariable Long cardId) {
        Card card = cardService.getCard(cardId);
        // calculate next study session time
        card.setNextStudySessionTime(new Date());
        long amountCorrectAnswers = card.getAmountCorrectAnswers();
        amountCorrectAnswers++;
        card.setAmountCorrectAnswers(amountCorrectAnswers);
        cardService.save(card);
    }

    @PostMapping("/save-wrong-test-result/{cardId}")
    public void saveWrongTestResult(@PathVariable Long cardId) {
        Card card = cardService.getCard(cardId);
        // +10 mins
        card.setNextStudySessionTime(new Date());
        card.setAmountCorrectAnswers(0);
        cardService.save(card);
    }
}
