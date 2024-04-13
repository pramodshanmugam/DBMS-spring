package com.example.artworks.controller;

import java.util.List;

import com.example.artworks.model.Artworks;
import com.example.artworks.service.ArtworksService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/api/artworks")
public class ArtworksController {
    private static final Logger log = LoggerFactory.getLogger(ArtworksController.class);
    private final ArtworksService service;

    public ArtworksController(ArtworksService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Artworks>> getAllArtworks() {
        List<Artworks> artworks = service.findAll();
        return ResponseEntity.ok(artworks);
    }

    @GetMapping("/{id}")
    public Artworks getArtworksById(@PathVariable String id) {
        log.info("Fetching artwork with ID: {}", id); // Ensure you have a Logger instance
        return service.findById(id)
                      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Artwork not found: " + id));
    }
    @GetMapping("/artist/{artist}")
    public ResponseEntity<List<Artworks>> findByArtist(@PathVariable String artist) {
        List<Artworks> artworks = service.findByArtist(artist);
        if (artworks.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(artworks);
    }
    @PostMapping
    public ResponseEntity<Artworks> createArtworks(@RequestBody Artworks artworks) {
        Artworks savedArtwork = service.save(artworks);
        return new ResponseEntity<>(savedArtwork, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artworks> updateArtworks(@PathVariable String id, @RequestBody Artworks artworks) {
        return service.findById(id)
                      .map(existingArtwork -> {
                          artworks.setId(id);
                          Artworks updatedArtwork = service.save(artworks);
                          return new ResponseEntity<>(updatedArtwork, HttpStatus.OK);
                      })
                      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Artwork not found"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteArtworks(@PathVariable String id) {
        return service.findById(id)
                      .map(artwork -> {
                          service.deleteById(id);
                          return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                      })
                      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Artwork not found"));

    }
}