package com.bfst.titrefoncier.validation;

import com.bfst.titrefoncier.CommitteeValidation.CommitteeValidation;
import com.bfst.titrefoncier.landTitleRequest.LandTitleRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Builder
public class Validation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "validation_seq")
    @SequenceGenerator(
            name = "validation_seq",
            sequenceName = "validation_seq",
            allocationSize = 1
    )
    private Long id;
    @ManyToOne
    private LandTitleRequest landTitleRequest;
    @ManyToOne
    private CommitteeValidation committeeValidation;
    private String comment;
    private Decision decision;
}
