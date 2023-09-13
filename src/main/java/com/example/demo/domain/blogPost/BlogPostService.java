package com.example.demo.domain.blogPost;

import jakarta.transaction.Transactional;
import com.example.demo.core.exception.IdNotFoundResponseError;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class BlogPostService {

    @Autowired
    private BlogPostRepository repository;

    public List<BlogPost> getAllBlogPosts() {
        List<BlogPost> posts = repository.findAll();
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
    public BlogPost putABlogPost(BlogPost post, UUID id) throws IdNotFoundResponseError {
        log.info("ID: " + id + " blog post updated");
        if (repository.existsById(id)) {
            post.setId(id);
            return repository.save(post);
        }
        BlogPost updatedPost = repository.findById(id).orElseThrow(() -> new IdNotFoundResponseError(id.toString()));
        log.info("ID: " + id + " blog post updated");
        return updatedPost;
    }

    @Transactional
    public void deleteABlogPost(UUID id) {
        repository.deleteById(id);
        log.info(id + " post deleted");
    }

    public List<BlogPost> getBlogPostByCategories(UUID blogPostId, Pageable pageable){
        List<BlogPost> posts = repository.getBlogPostByCategories(blogPostId, pageable);
        log.info("got all posts by categories");
        return posts;
    }
}
