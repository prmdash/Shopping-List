package primo.shoppinglist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import primo.shoppinglist.data.entities.ProductEntity;

import java.math.BigDecimal;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {

    @Query("SELECT SUM(p.price) FROM ProductEntity p")
    BigDecimal findTotalPriceOfAllProducts();
}
