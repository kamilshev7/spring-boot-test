package net.guard.passer.repository;

import net.guard.passer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

// there are may JpaRepository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT a FROM User a WHERE a.email = :email1")
    User getUserByEmail(@Param("email1") String email1);

}
