package com.leti.flashcards.side;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SideService {

    private final SideRepository sideRepository;

    public List<Side> getSidesByCard(Long cardId) {
        return sideRepository.findAllByCard_Id(cardId);
    }

    public void save(Side side) {
        sideRepository.save(side);
    }
}
