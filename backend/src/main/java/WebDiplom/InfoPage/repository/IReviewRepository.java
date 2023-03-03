package WebDiplom.InfoPage.repository;

import WebDiplom.InfoPage.models.InfoShop;
import WebDiplom.InfoPage.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IReviewRepository extends JpaRepository<Review,Long> {
    @Query(value = "select b from Review b where b.id_shop = :id_shop")
    List<Review> findAllByShopId(@Param("id_shop") InfoShop id_shop);
    @Query(value = "select b from Review b where b.id_shop = :id_shop")
    List<Review> findAllById(@Param("id_shop") InfoShop id_shop);
}