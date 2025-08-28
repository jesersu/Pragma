package com.chapi.api.mapper.registry;

import com.chapi.api.DTO.registry.RegistryUserDTO;
import com.chapi.api.DTO.registry.UsuarioRequestDTO;
import com.chapi.api.DTO.registry.UsuarioResponseDTO;
import com.chapi.model.usuario.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDTOMapper {
    Usuario mapToEntity(RegistryUserDTO userDTO);

    RegistryUserDTO mapToDTO(Usuario usuario);

    UsuarioRequestDTO mapToRequestDTO(Usuario usuario);

    Usuario mapToEntity(UsuarioRequestDTO usuarioRequestDTO);

    UsuarioResponseDTO mapToResponseDTO(Usuario usuario);

    Usuario mapToEntity(UsuarioResponseDTO usuarioResponseDTO);


}
