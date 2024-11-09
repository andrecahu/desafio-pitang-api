package com.pitang.desafiopitangapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pitang.desafiopitangapi.exceptions.BusinessException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

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

    public void validate() {
        if(year == null)
            throw new BusinessException("“Missing fields", HttpStatus.BAD_REQUEST);
        if(licensePlate == null || licensePlate.isEmpty())
            throw new BusinessException("“Missing fields", HttpStatus.BAD_REQUEST);
        if(model == null || model.isEmpty())
            throw new BusinessException("“Missing fields", HttpStatus.BAD_REQUEST);
        if(color == null || color.isEmpty())
            throw new BusinessException("“Missing fields", HttpStatus.BAD_REQUEST);

        if(year > LocalDateTime.now().getYear())
            throw new BusinessException("Invalid fields", HttpStatus.BAD_REQUEST);

        if(!licensePlate.matches("^[A-Z]{3}-\\d{4}$"))
            throw new BusinessException("Invalid fields", HttpStatus.BAD_REQUEST);
    }

}
