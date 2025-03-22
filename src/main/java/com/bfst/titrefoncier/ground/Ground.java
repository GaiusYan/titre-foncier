package com.bfst.titrefoncier.ground;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Data
public class Ground {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ground_seq")
    @SequenceGenerator(
            name = "ground_seq",
            sequenceName = "ground_seq",
            allocationSize = 1
    )
    private Long id;
    private String address;
    private double area;
    private GroundUsage usage;

}
