package com.parkcar.repository;

import com.parkcar.domain.entity.Garagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GaragemRepository extends JpaRepository<Garagem, Long> {
}