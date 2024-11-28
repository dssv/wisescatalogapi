package com.wisescatalog.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Entity(name = "books")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Books implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @JsonProperty("genre")
    @NotNull
    private String genre;

    @JsonProperty("author")
    @NotNull
    private String author;

    @JsonProperty("title")
    @NotNull
    private String title;

}