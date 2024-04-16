package com.example.artworks.repository;

import java.util.List;

import com.example.artworks.model.Artworks;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtworksRepository extends MongoRepository<Artworks, String> {
    List<Artworks> findByArtist(String Artist);

    // Custom query methods if necessary
}