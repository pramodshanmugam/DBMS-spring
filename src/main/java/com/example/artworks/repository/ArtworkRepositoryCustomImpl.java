package com.example.artworks.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ArtworkRepositoryCustomImpl implements ArtworkRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public ArtworkRepositoryCustomImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<ArtworkCount> countArtworksByArtist() {
        GroupOperation groupByArtist = Aggregation.group("artist")
                                                  .count().as("total");
        Aggregation aggregation = Aggregation.newAggregation(groupByArtist);
        AggregationResults<ArtworkCount> results = mongoTemplate.aggregate(
                aggregation, "artworks", ArtworkCount.class);
        return results.getMappedResults();
    }

    public static class ArtworkCount {
        private String id; // artist name
        private int total; // count of artworks

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
}
