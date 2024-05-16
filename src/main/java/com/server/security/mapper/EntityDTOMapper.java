package com.server.security.mapper;

public interface EntityDTOMapper <E,D>{
    D toDto(E entity);
    E toEntity(D dto);

}
