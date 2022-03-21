package uk.sky.inventoryservice.repositories;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;
import uk.sky.inventoryservice.models.Product;

import java.util.Optional;

@Repository
public interface ProductRepository extends CassandraRepository<Product, String> {

    @Query("SELECT stock FROM inventory.product WHERE productid=?0")
    Optional<Integer> findStockByProductId(String productId);
}
