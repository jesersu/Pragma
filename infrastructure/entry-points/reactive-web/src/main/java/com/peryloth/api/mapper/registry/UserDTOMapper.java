package com.chapi.api.mapper.registry;

import com.chapi.api.DTO.registry.UsuarioRequestDTO;
import com.chapi.model.usuario.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDTOMapper {
    UsuarioRequestDTO mapToRequestDTO(Usuario usuario);

}
