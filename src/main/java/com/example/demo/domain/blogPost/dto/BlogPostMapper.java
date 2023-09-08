package com.example.demo.domain.blogPost.dto;

import com.example.demo.core.generic.AbstractMapper;
import com.example.demo.domain.blogPost.BlogPost;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BlogPostMapper extends AbstractMapper<BlogPost, BlogPostDTO> {
}
