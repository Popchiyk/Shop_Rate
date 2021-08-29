package WebDiplom.InfoPage.Repository;

import WebDiplom.InfoPage.Models.Favourite;
import WebDiplom.InfoPage.Models.InfoShop;
import WebDiplom.InfoPage.Models.ReviewEntity;
import WebDiplom.InfoPage.Models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity,Long> {
    @Query(value = "select b from ReviewEntity b where b.id_shop = :id_shop")
    List<ReviewEntity> findAllByShopId(@Param("id_shop") InfoShop id_shop);
    @Query(value = "select b from ReviewEntity b where b.id_shop = :id_shop")
    List<ReviewEntity> findAllById(@Param("id_shop") InfoShop id_shop);
}
