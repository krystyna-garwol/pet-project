package uk.sky.inventoryservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("product")
@AllArgsConstructor
@Getter
public class Product {

    @PrimaryKeyColumn(name = "productId", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String productId;

    @Column
    private String name;

    @Column
    private String animal;

    @Column
    private int stock;

    @Column
    private double price;

    @Column
    private String type;

}
