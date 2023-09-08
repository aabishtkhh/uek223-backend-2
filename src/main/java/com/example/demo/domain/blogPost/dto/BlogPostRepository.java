package com.example.demo.domain.blogPost.dto;

import com.example.demo.core.generic.AbstractRepository;
import com.example.demo.domain.blogPost.dto.BlogPost;
import com.example.demo.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BlogPostRepository extends AbstractRepository<BlogPost> {
}
