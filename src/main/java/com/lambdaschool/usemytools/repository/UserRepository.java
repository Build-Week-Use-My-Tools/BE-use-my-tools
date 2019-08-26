package com.lambdaschool.usemytools.repository;

import com.lambdaschool.usemytools.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUsername(String username);
}
