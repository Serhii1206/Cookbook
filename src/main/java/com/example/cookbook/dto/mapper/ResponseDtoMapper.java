package com.example.cookbook.dto.mapper;

public interface ResponseDtoMapper<D, T> {
    D mapToDto(T t);
}
