package com.example.demo.domain.category;

import com.example.demo.core.generic.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor@Table(name = "categories")
public class BlogPostCategory extends AbstractEntity {

    @Column(name = "name")
    private String name;

    public BlogPostCategory(UUID id, String name){
        super(id);
        this.name = name;
    }

}
