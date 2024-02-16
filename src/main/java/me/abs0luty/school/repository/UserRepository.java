package me.abs0luty.school.repository;

import me.abs0luty.school.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Long> {

    Optional<ApplicationUser> maybeFindByEmail(String email);
    ApplicationUser findByEmail(String email);

    @Modifying
    @Query(
        value = "UPDATE Account a SET a.status = 'removed' WHERE a.email = ?1",
        nativeQuery = true)
    void deleteByEmail(String email);
}
