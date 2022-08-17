package com.TeamSk.JMC.Domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;


public interface UserRepository extends JpaRepository<Users, Long> {

}
