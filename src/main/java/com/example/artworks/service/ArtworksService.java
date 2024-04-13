package com.example.artworks.service;


import com.example.artworks.model.Artworks;
import com.example.artworks.repository.ArtworksRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtworksService {
    private final ArtworksRepository repository;

    public ArtworksService(ArtworksRepository repository) {
        this.repository = repository;
    }
    public List<Artworks> findByArtist(String artist) {
        return repository.findByArtist(artist);
    }


    public List<Artworks> findAll() {
        return repository.findAll();
    }

    public Optional<Artworks> findById(String id) {
        return repository.findById(id);
    }

    public Artworks save(Artworks artwork) {
        return repository.save(artwork);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    // Add more methods as needed, such as update
}
