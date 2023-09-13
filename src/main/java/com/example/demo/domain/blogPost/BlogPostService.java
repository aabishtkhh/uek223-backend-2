package com.example.demo.domain.blogPost;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class BlogPostService {

    @Autowired
    private BlogPostRepository repository;

    public List<BlogPost> getAllBlogPosts(Pageable pageable) {
        List<BlogPost> posts = repository.findAll(pageable).getContent();
        log.info("All blog posts shown");
        return posts;
    }

    public BlogPost getSingleBlogPost(UUID id) throws EmptyResultDataAccessException {
        int uuidInInteger = Integer.parseInt(String.valueOf(id));
        BlogPost onePost = repository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(uuidInInteger));
        log.info("ID: " + id + " blog post");
        return onePost;
    }

    @Transactional
    public BlogPost postABlogPost(BlogPost post) {
        BlogPost savedPost = repository.save(post);
        log.info("ID: " + post.getId() + " blog post created");
        return savedPost;
    }

    @Transactional
    public BlogPost putABlogPost(BlogPost post, UUID id) throws EmptyResultDataAccessException {
        if (repository.existsById(id)) {
            post.setId(id);
            return repository.save(post);
        }
        int uuidInInteger = Integer.parseInt(String.valueOf(id));
        BlogPost updatedPost = repository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(uuidInInteger));
        log.info("ID: " + id + " blog post updated");
        return updatedPost;
    }

    @Transactional
    public void deleteABlogPost(UUID id) {
        repository.deleteById(id);
        log.info(id + " post deleted");
    }
}
