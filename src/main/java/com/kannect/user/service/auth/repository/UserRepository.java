package com.kannect.user.service.auth.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kannect.user.service.auth.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUserName(String userName);

	List<User> findByActive(Boolean active);

	Optional<User> findByUserNameAndActive(String userName, boolean b);

}
