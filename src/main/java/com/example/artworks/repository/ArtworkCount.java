package com.example.artworks.repository;

public class ArtworkCount {
    private String id; // This will store the artist name as the key
    private int total; // This will store the count of artworks

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
