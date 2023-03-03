package WebDiplom.InfoPage.repository;

import WebDiplom.InfoPage.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);
    Boolean existsByUserName(String username);

}
