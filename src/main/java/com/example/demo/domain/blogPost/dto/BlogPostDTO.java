package com.example.demo.domain.blogPost.dto;

import com.example.demo.core.generic.AbstractDTO;
import com.example.demo.domain.category.Category;
import com.example.demo.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class BlogPostDTO extends AbstractDTO {

    /**
     * In this special case, we don't have to hide anything, so the DTO and the main file is kept same
     * */

    @Column(name = "title")
    @Size(max = 270, message = "{validation.title.size.too_long}")
    private String title;

    @Column(name = "text")
    private String text;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "blog_post_categories",
            joinColumns = @JoinColumn(name = "blogpostid", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn (name = "categoryid", referencedColumnName = "id")
    )
    private List<Category> categories;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public BlogPostDTO(UUID id, String title, String text, User user) {
        super(id);
        this.title = title;
        this.text = text;
        this.user = user;
    }

}