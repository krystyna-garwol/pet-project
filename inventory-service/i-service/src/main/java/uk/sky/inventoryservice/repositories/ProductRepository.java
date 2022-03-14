package uk.sky.inventoryservice.repositories;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import uk.sky.inventoryservice.models.Product;

@Repository
public interface ProductRepository extends CassandraRepository<Product, String> {
}
