package com.springframework.spjpa.domain.compose;

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
@IdClass(IdName.class)
public class AuthorCompose {
    @Id
    private String firstName;
    @Id
    private String lastName;
    private String country;

}
