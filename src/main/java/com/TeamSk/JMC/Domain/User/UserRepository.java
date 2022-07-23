package com.TeamSk.JMC.Domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;


public interface UserRepository extends JpaRepository<User,Long> {
}
