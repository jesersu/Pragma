package com.chapi.api.DTO.registry;

public record UsuarioResponseDTO(
        String idUsuario,
        String nombre,
        String apellido,
        String email,
        String documentoIdentidad,
        String telefono,
        Long salarioBase,
        String rolNombre
) {}
