package org.example.services;

import org.example.Entities.Rating;
import org.apache.log4j.Logger;
import org.example.dao.RatingDAO;

import java.util.List;
import java.util.Optional;

public class RatingService {
    Logger log = Logger.getLogger(RatingService.class.getName());
    RatingDAO dao = new RatingDAO();

    public Rating insertRating(Rating rating) {
        return dao.insert(rating);
    }

    public List<Rating> findAll() {
        return dao.findAll();
    }

    public Optional<Rating> findEntityById(Long id) {
        return dao.findEntityById(id);
    }

    public boolean delete(Long id) {
        if (findEntityById(id).isEmpty()) return false;
        return dao.delete(id);
    }

    public Rating update(Rating rating) {
        return dao.update(rating);
    }
}