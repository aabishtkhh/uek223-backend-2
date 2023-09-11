package com.example.demo.domain.category.dto;

import com.example.demo.core.generic.AbstractMapper;
import com.example.demo.domain.category.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper extends AbstractMapper<Category, CategoryDTO> {
}
