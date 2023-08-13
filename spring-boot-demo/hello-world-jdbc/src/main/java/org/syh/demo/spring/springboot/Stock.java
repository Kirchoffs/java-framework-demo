package org.syh.demo.spring.springboot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@Builder
@Table(name = "STOCK")
@Entity
public class Stock {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "VOLUME")
    private Integer volume;

    @Column(name = "MARKET_CAP")
    private Integer marketCap;
}
