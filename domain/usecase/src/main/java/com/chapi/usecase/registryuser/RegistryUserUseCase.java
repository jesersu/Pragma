package com.chapi.usecase.registryuser;

import com.chapi.model.rol.gateways.RolRepository;
import com.chapi.model.usuario.Usuario;
import com.chapi.model.usuario.gateways.UsuarioRepository;
import com.chapi.usecase.registryuser.commandQueue.UsuarioValidationQueue;
import com.chapi.usecase.registryuser.commandQueue.validations.ApellidoValidation;
import com.chapi.usecase.registryuser.commandQueue.validations.EmailValidation;
import com.chapi.usecase.registryuser.commandQueue.validations.NombreValidation;
import com.chapi.usecase.registryuser.commandQueue.validations.SalarioBaseValidation;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.math.BigInteger;


@RequiredArgsConstructor
public class RegistryUserUseCase implements IRegistryUserUseCase {
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    @Override
    public Mono<Void> RegistryUser(Usuario usuario) {
        UsuarioValidationQueue validationQueue = new UsuarioValidationQueue()
                .addValidation(new NombreValidation())
                .addValidation(new ApellidoValidation())
                .addValidation(new EmailValidation(usuarioRepository))
                .addValidation(new SalarioBaseValidation());

        BigInteger rolIdFijo = BigInteger.ONE;

        return validationQueue.validate(usuario)
                .then(rolRepository.getRolById(rolIdFijo)
                        .switchIfEmpty(Mono.error(new IllegalArgumentException("Rol fijo no encontrado")))
                        .flatMap(rol -> {
                            Usuario usuarioConRol = usuario.toBuilder().rol(rol).build();
                            System.out.println("Usuario con rol asignado: " + usuarioConRol.getRol().getUniqueId());
                            return usuarioRepository.saveUsuario(usuarioConRol);
                        })
                ).then();
    }
}
