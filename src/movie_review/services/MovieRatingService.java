package movie_review.services;

// import movie_review.utility.CustomException;

public interface MovieRatingService {
    void addMovie(String name, int year, String genre);
    void addUser(String name);
    void addReview(String userName, String movieName, int rating);
    
    String topMovieOfYear(int year);
    String topMovieOfGenre(String genre);

    String topMovieOfYearByCritic(int year);
    String topMovieOfGenreByCritic(String genre);

    String topAvgRatingMovieByYear(int year);
    String topAvgRatingMovieByGenre(String genre);
    float avgRatingOfMovie(String name);
}
