package com.example.artworks.model;

import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Getter
@AllArgsConstructor
@Setter
@Document(collection = "management")
public class Artworks {
    @Id
    private String id;
    private String Title;
    private String Artist;
    private String Year; // Year of creation
    private String Medium; // The medium used, e.g., oil on canvas
    private String Dimensions; // Physical dimensions of the artwork
    private String Description; // A brief description of the artwork
    private String ImageURL; // URL to an image of the artwork

    public void setId(String id) {
        this.id = id;
    }

    // Constructors
    public Artworks() {}




}