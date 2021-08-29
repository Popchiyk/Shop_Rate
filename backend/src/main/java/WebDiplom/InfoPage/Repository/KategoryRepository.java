package WebDiplom.InfoPage.Repository;

import WebDiplom.InfoPage.Models.Kategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface KategoryRepository extends JpaRepository<Kategory,Long> {
}
