package WebDiplom.InfoPage.Repository;

import WebDiplom.InfoPage.Models.*;
import WebDiplom.InfoPage.Models.InfoShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InfoShopRepository extends JpaRepository<InfoShop,Long> {
    Optional<InfoShop> findById(Long id);
    @Query(value = "select b from InfoShop b where b.id_Kategory = :id_kategory")
    List<InfoShop> findAllByKategoryName(@Param("id_kategory") Kategory kategory);
}
