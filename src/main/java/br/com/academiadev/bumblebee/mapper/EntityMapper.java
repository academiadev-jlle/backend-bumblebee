package br.com.academiadev.bumblebee.mapper;

import java.util.List;

public interface EntityMapper<T, DTO> {

    DTO toDTO(T entity);

    T toEntity(DTO dto);

    List<DTO> toDTO(List<T> dtos);

    List<T> toEntity(List<DTO> entities);

}
