package movie_review.dao;

import java.util.HashSet;

import movie_review.dto.*;
import movie_review.utility.CustomException;

public class Reviews {
    private HashSet<Score> reviews;
    
    public Reviews() {
        this.reviews = new HashSet<>();
    }

    public void createReview(Movie movie, Person user, int rating) throws CustomException{
        Score review = new Score(movie, user, rating);
        if(this.reviews.contains(review)){
            throw new CustomException("Exception: Multiple reviews not allowed");
        } else{
            this.reviews.add(review);
        }
    }

    public HashSet<Score> getReviews() {
        return reviews;
    }
}
