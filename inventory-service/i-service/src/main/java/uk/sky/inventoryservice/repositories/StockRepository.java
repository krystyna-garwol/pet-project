package uk.sky.inventoryservice.repositories;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import uk.sky.inventoryservice.models.StockItem;

@Repository
public interface StockRepository extends CassandraRepository<StockItem, String> {
}
