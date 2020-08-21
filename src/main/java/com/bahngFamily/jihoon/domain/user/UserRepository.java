package com.bahngFamily.jihoon.domain.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserImpl, Long> {
  Optional<UserImpl> findByEmail(String email);
}
