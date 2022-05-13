package com.springframework.spjpa.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthorUUID {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 36, columnDefinition = "varchar(36)", nullable = false, updatable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;
    private String firstName;
    private String lastName;

    public AuthorUUID(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
