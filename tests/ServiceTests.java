
import movie_review.services.*;

public class ServiceTests {
    private static MovieRatingService service;
    public static void main(String[] args) {
        // checkReviewerDao();
        service = new MovieRatingImpl();
        service.addMovie("DEF", 2005, "Romance");
        service.addMovie("GHI", 2005, "Action");
        service.addMovie("KLN", 2005, "Romance");
        service.addMovie("PQR", 2005, "Romance");
        // service.addMovie("PQR", 2005, "Sci-Fi");
        service.addUser("A");
        service.addUser("B");
        service.addUser("C");
        service.addReview("A", "DEF", 5);
        service.addReview("A", "GHI", 10);
        service.addReview("A", "KLN", 8);
        service.addReview("A", "PQR", 9);

        service.addReview("B", "DEF", 6);
        service.addReview("B", "GHI", 2);
        service.addReview("B", "PQR", 5);
        service.addReview("B", "KLN", 8);

        service.addReview("C", "DEF", 5);
        service.addReview("C", "GHI", 10);
        service.addReview("C", "KLN", 1);
        // checkTopMovieYear(2005);
        checkTopMovieGenre("Romance");
        // checkTopMovieOfYearByCritic(2005);
        // checkTopMovieOfGenreByCritic("Romance");
        // checkTopAvgRatingMovieByYear(2005);
    }
    public static void checkTopMovieYear(int year){
        System.out.println(service.topMovieOfYear(year));
    }
    public static void checkTopMovieGenre(String genre){
        System.out.println(service.topMovieOfGenre(genre));
    }
    public static void checkTopMovieOfYearByCritic(int year){
        System.out.println(service.topMovieOfYearByCritic(year));
    }
    public static void checkTopMovieOfGenreByCritic(String genre){
        System.out.println(service.topMovieOfGenreByCritic(genre));
    }
    public static void checkTopAvgRatingMovieByYear(int year){
        System.out.println(service.topAvgRatingMovieByYear(year));
    }
}
