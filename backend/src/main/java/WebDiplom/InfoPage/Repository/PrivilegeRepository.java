package WebDiplom.InfoPage.Repository;

import WebDiplom.InfoPage.Models.PrivilegeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<PrivilegeEntity, Long> {
    PrivilegeEntity findByName(String name);
}
