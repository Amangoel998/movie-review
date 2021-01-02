import java.util.HashSet;

import movie_review.dao.*;
import movie_review.dto.*;
import movie_review.utility.CustomException;

public class DAOTests {
    public static void main(String[] args) throws CustomException {
        checkReviewerDao();
        // checkUsersDao();
    }
    private static void checkUsersDao() throws CustomException {
        Users users = new Users();
        users.createUser("DEF");
        // Test Fails, since already exists
        users.createUser("DEF");

        users.createUser("AF");
    }
    private static void checkReviewerDao() throws CustomException {
        Reviews reviews = new Reviews();
        Movie mv1 = new Movie("ABC");
        Person us1 = new Person("DEF");
        Person us2 = new Person("EF");
        reviews.createReview(mv1, us1, 4);
        reviews.createReview(mv1, us2, 7);
        // reviews.createReview("ABC", "DEF", 8);
        HashSet<Score> set = reviews.getReviews();
        for (Score entry : set) {
            System.out.println(entry.getMovie().getName() + " " + entry.getUser().getName() + " " + entry.getRating());
        }
    }
}
