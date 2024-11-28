package com.wisescatalog.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity(name = "books")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Books implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
    @JsonProperty("genre")  @NonNull private String genre;
    @JsonProperty("author") @NonNull private String author;
    @JsonProperty("title") @NonNull private String title;

}