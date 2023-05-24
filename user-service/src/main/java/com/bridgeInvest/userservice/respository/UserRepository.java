package com.bridgeInvest.userservice.respository;


import com.bridgeInvest.userservice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends  JpaRepository<User,Long> {
	/**
	 *
	 * @param username
	 * @return
	 */
	Optional<User> findByName(String username);
}
 