package com.bfst.titrefoncier.landTitleRequest;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class LandTitleRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "landTitle_sequence")
    @SequenceGenerator(name = "landTitle_sequence",
            sequenceName = "landTitle_sequence",
            allocationSize = 1)
    private Long id;
    private String reference;
    private StatusRequest status;
    private LocalDate dateRequest;
    private Timestamp timeRequest;

}
