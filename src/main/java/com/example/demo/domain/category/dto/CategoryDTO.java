package com.example.demo.domain.category.dto;

import com.example.demo.core.generic.AbstractDTO;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class CategoryDTO extends AbstractDTO {

    @Column(name = "name")
    private String name;

    public CategoryDTO(UUID id, String name){
        super(id);
        this.name = name;
    }

}