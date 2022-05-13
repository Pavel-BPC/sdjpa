package com.springframework.spjpa.domain.compose;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class IdName implements Serializable {
    private String firstName;
    private String lastName;
}
