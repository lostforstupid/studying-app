package com.leti.flashcards.group;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends CrudRepository<Group, Long> {
    List<Group> findAll();
    Optional<Group> findByName(String name);
}
