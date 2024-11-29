package back.End.Concesionario.Model;

import back.End.Concesionario.Model.Enum.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Vehicle {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    Long id;

    @Column(name = "moldel", length = 100)
    String model;

    @Column(name = "year")
    LocalDate year;

    @Column(name = "price")
    Double price;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    Brand brand;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    Status status;
}
