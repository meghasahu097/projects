package com.example.UserAuthentication.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.example.UserAuthentication.entity.UserEntity;


@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer>{

	@Query("SELECT u FROM UserEntity u WHERE u.username = :username")
     public UserEntity findByUsername(@Param("username") String username);
}
