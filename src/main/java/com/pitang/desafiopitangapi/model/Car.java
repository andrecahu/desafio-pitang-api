package com.pitang.desafiopitangapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CARS")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "CAR_ID")
    private String id;

    @Column(name = "CAR_YEAR", nullable = false)
    private Integer year;

    @Column(name = "LICENSE_PLATE", nullable = false)
    private String licensePlate;

    @Column(name = "MODEL", nullable = false)
    private String model;

    @Column(name = "COLOR", nullable = false)
    private String color;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    @JsonBackReference
    private User user;

}
