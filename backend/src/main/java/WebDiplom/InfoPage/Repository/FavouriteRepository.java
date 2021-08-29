package WebDiplom.InfoPage.Repository;

import WebDiplom.InfoPage.Models.Favourite;
import WebDiplom.InfoPage.Models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavouriteRepository extends JpaRepository<Favourite,Long> {
    @Query(value = "select b from Favourite b where b.id_users = :users")
    List<Favourite> findAllByUserId(@Param("users") UserEntity userId);
}
