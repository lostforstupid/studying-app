package com.leti.flashcards.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupView {
    private Long id;
    private String name;
    private String description;

    @Override
    public String toString() {
        return "{\"id\":" + id
                + ", \"name\":\"" + name
                + "\", \"description\":\"" + description + "\"}";
    }
}
