package WebDiplom.InfoPage.repository;

import WebDiplom.InfoPage.models.Favourite;
import WebDiplom.InfoPage.models.InfoShop;
import WebDiplom.InfoPage.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface IFavouriteRepository extends JpaRepository<Favourite,Long> {
    @Query(value = "select b from Favourite b where b.id_users = :users")
    List<Favourite> findAllByUserId(@Param("users") User userId);

    @Query(value = "select b from Favourite b where b.id_users = :users and b.id_shop= :infoshop" )
    List<Favourite> findAllByUserIdAndIdShop(@Param("users") User userId, @Param("infoshop")InfoShop infoShop);
}
