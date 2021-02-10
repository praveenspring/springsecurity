package com.duedash.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.duedash.io.entity.UserEntity;
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
UserEntity findUserByEmail(String email);

UserEntity findByUserId(String userId);
}
