package com.bfst.titrefoncier.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table
@Data
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    private Long id;
    private String name;
    private String email;
    private String username;
    private String password;
    private LocalDate dateCreated;
    private LocalDate dateModified;
    private boolean enabled;
    private Role role;
}
