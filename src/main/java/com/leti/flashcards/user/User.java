package com.leti.flashcards.user;

import com.leti.flashcards.group.Group;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "USER")
public class User {

    @Id
    private String id;

    private String name;

    private String email;

    private String profilePictureUrl;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Group> groups;
}
