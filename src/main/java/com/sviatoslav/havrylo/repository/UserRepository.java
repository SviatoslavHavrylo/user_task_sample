package com.sviatoslav.havrylo.repository;

import com.sviatoslav.havrylo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
