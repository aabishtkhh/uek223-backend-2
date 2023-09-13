package com.example.demo.domain.blogPost;

import com.example.demo.core.generic.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface BlogPostRepository extends AbstractRepository<BlogPost> {
    public Page<BlogPost> findAll(Pageable pageable);

}
