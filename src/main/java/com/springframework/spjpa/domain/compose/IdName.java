package com.springframework.spjpa.domain.compose;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IdName implements Serializable {
    private String firstName;
    private String lastName;
}
