package com.bfst.titrefoncier.Owner;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table
@Builder
public class Owner {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "owner_sequence")
    @SequenceGenerator(
            name = "owner_sequence",
            sequenceName = "owner_sequence",
            allocationSize = 1
    )
    private Long id;
    private String name;
    private String cni;
    private String contact;


}
