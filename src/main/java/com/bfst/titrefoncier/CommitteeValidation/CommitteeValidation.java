package com.bfst.titrefoncier.CommitteeValidation;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.SEQUENCE;

@AllArgsConstructor
@Entity
@NoArgsConstructor
@Builder
@Table
public class CommitteeValidation {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "committee_validation_seq")
    @SequenceGenerator(
            name = "committee_validation_seq",
            sequenceName = "committee_validation_seq",
            allocationSize = 1
    )
    private Long id;
    private String name;
    private String role;
}
