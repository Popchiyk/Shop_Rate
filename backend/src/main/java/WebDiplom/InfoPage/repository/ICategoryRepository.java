package WebDiplom.InfoPage.repository;

import WebDiplom.InfoPage.models.Kategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Kategory,Long> {
}
