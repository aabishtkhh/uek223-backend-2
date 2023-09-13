package com.example.demo.domain.blogPost;

import com.example.demo.core.generic.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.UUID;

public interface BlogPostRepository extends AbstractRepository<BlogPost> {
    public List<BlogPost> getBlogPostByCategories(UUID uuid, Pageable pageable);


}
