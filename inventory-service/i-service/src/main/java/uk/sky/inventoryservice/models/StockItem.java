package uk.sky.inventoryservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("stock_item")
@AllArgsConstructor
@Getter
public class StockItem {

    @PrimaryKeyColumn(name = "name", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String name;

    @PrimaryKeyColumn(name = "animal", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private String animal;

    @Column
    private int inStock;

    @Column
    private double price;

    @Column
    private String type;

}
