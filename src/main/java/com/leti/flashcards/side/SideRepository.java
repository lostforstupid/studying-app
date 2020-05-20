package com.leti.flashcards.side;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SideRepository extends CrudRepository<Side, Long> {
    List<Side> findAllByCard_Id(Long cardId);
}
