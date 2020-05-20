package com.leti.flashcards.card;

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

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @Transactional
    @SneakyThrows
    public Card getCard(Long cardId) {
        Optional<Card> cardOptional = cardRepository.findById(cardId);
        Card card;
        if (cardOptional.isPresent()) {
            card = cardOptional.get();
        } else {
            throw new Exception("Card with id " + cardId + " not found");
        }
        return card;
    }

    public void save(Card card) {
        cardRepository.save(card);
    }
}
