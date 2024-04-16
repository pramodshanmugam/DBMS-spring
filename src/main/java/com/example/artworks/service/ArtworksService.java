package com.example.artworks.service;

import com.example.artworks.model.Artworks;
import com.example.artworks.repository.ArtworksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ArtworksService {
    private final ArtworksRepository repository;
    private final MongoTemplate mongoTemplate;

    // Constructor-based Dependency Injection
    @Autowired
    public ArtworksService(ArtworksRepository repository, MongoTemplate mongoTemplate) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
    }

    // Find artworks by artist name
    public List<Artworks> findByArtist(String artist) {
        return repository.findByArtist(artist);
    }

    // Find all artworks
    public List<Artworks> findAll() {
        return repository.findAll();
    }

    // Find artwork by ID
    public Optional<Artworks> findById(String id) {
        return repository.findById(id);
    }

    // Save or update an artwork
    public Artworks save(Artworks artwork) {
        return repository.save(artwork);
    }

    // Delete an artwork by ID
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    // Find first ten artworks with pagination
    public List<Artworks> findFirstTen(Pageable pageable) {
        Page<Artworks> page = repository.findAll(pageable);
        return page.getContent();
    }

    // Map-Reduce operation to count artworks by artist
    public MapReduceResults<ArtworkCount> countArtworksByArtist() {
        String mapFunction = "function() { emit(this.artist, 1); }";
        String reduceFunction = "function(key, values) { return Array.sum(values); }";

        return mongoTemplate.mapReduce(
                "artworks", // Adjust if your MongoDB collection name is different
                mapFunction,
                reduceFunction,
                ArtworkCount.class // Class to capture map-reduce output
        );
    }

    // Inner class to capture the results of the map-reduce operation
    public static class ArtworkCount {
        private String id; // This will store the artist name as the key
        private int value; // This will store the count of artworks

        // Getters and setters
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
