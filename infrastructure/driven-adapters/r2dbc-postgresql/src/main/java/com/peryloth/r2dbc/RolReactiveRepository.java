package com.chapi.r2dbc;

import com.chapi.r2dbc.entity.RolEntity;
import com.chapi.r2dbc.entity.UsuarioEntity;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.math.BigInteger;

public interface RolReactiveRepository extends ReactiveCrudRepository<RolEntity, BigInteger>, ReactiveQueryByExampleExecutor<RolEntity> {
}
