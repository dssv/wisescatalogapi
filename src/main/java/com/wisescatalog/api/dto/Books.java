package com.wisescatalog.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity(name = "books")
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Books implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
    @JsonProperty("genre")  private String genre;
    @JsonProperty("author") private String author;
    @JsonProperty("title") private String title;

}