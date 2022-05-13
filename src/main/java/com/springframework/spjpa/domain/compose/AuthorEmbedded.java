package com.springframework.spjpa.domain.compose;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "author_compose")
@Builder
public class AuthorEmbedded {
    @EmbeddedId
    private IdName idName;
    private String country;

}
