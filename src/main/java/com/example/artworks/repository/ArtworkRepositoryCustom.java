package com.example.artworks.repository;

import java.util.List;

public interface ArtworkRepositoryCustom {
    List<ArtworkRepositoryCustomImpl.ArtworkCount> countArtworksByArtist();
}