package com.example.demo.domain.blogPost;

import com.example.demo.core.generic.AbstractEntity;
import com.example.demo.domain.category.Category;
import com.example.demo.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//or @Data
@Entity
@Table(name = "blog_posts")
public class BlogPost extends AbstractEntity {

    //Zurzeit wird es ein Fehler in der Terminal ausgeben, da in dieser sowie in der Kategorie Tabelle keine Daten hinzugefügen wurden

    @Column(name = "title")
    @Size(max = 270, message = "{validation.title.size.too_long}") //Validation
    private String title;

    @Column(name = "text")
    private String text;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "blog_post_categories",
            joinColumns = @JoinColumn (name = "blogpostid", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn (name = "categoryid", referencedColumnName = "id")
    )
    private List<Category> categories;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public BlogPost(UUID id, String title, String text, User user) {
        super(id);
        this.title = title;
        this.text = text;
        this.user = user;
    }
}
