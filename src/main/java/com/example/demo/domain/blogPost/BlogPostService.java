package com.example.demo.domain.blogPost;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class BlogPostService {

    @Autowired
    private BlogPostRepository repository;

    public List<BlogPost> getAllBlogPosts(String filterText) {
        log.info("All blog posts shown");
        List<BlogPost> posts;
        List<BlogPost> filteredPosts = new ArrayList<>();
        posts = repository.findAll();
        if (filterText != null){ //it checks if the reviews name is equal to the filteredName
            for (BlogPost post : posts) {
                if (post.getText().equals(filterText)){
                    filteredPosts.add(post); //when matches it adds to the filteredDrinks
                }
            }
            return filteredPosts;
        }else {
            return posts;
        }
    }

    public BlogPost getSingleBlogPost(UUID id) {
        log.info("ID: " + id + " blog post");
        return repository.findById(id).orElseThrow();
    }

    public BlogPost postABlogPost(BlogPost post) {
        log.info("ID: " + post.getId() + " blog post created");
        return repository.save(post);
    }

    public BlogPost putABlogPost(BlogPost post, UUID id) {
        log.info("ID: " + id + " blog post updated");
        if (repository.existsById(id)) {
            post.setId(id);
            return repository.save(post);
        }
        return repository.findById(id).orElseThrow();
    }

    public void deleteABlogPost(UUID id) {
        log.info(id + " review deleted");
        repository.deleteById(id);
    }
}
