package com.ismaildurcan.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
// Aynı galeristin aynı arabayı birden fazla kaydetmesini engellemek için unique
// constraint ekledik
@Table(name = "gallerist_car", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "gallerist_id", "car_id" }, name = "uq_gallerist_car")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GalleristCar extends BaseEntity {

    @ManyToOne
    private Gallerist gallerist;

    @ManyToOne
    private Car car;

}
