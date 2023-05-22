package com.example.cookbook.dto.mapper;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import com.example.cookbook.dto.request.CookbookRequestDto;
import com.example.cookbook.dto.response.CookbookResponseDto;
import com.example.cookbook.model.Cookbook;
import com.example.cookbook.service.CookbookService;

@AllArgsConstructor
@Component
@Primary
public class CookbookMapper implements ResponseDtoMapper<CookbookResponseDto, Cookbook>,
        RequestDtoMapper<CookbookRequestDto, Cookbook> {
    private final CookbookService cookbookService;

    @Override
    public Cookbook mapToModel(CookbookRequestDto dto) {
        Cookbook cookbook = new Cookbook();
        cookbook.setName(dto.getName());
        return cookbook;
    }

    @Override
    public CookbookResponseDto mapToDto(Cookbook cookbook) {
        CookbookResponseDto cookbookResponseDto = new CookbookResponseDto();
        cookbookResponseDto.setId(cookbook.getId());
        cookbookResponseDto.setName(cookbookResponseDto.getName());
        return cookbookResponseDto;
    }
}
