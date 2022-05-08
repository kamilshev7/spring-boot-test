package net.guard.passer.repository;

import net.guard.passer.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query("SELECT a FROM Role a WHERE a.name = :name1")
    Role findByName(@Param("name1") String name1);
}
