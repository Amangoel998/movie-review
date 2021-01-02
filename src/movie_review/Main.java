package movie_review;

import movie_review.services.*;
class Main{
    private static MovieRatingService service;
    public static void main(String[] args){
        service = new MovieRatingImpl();
        service.addMovie("Don", 2006, "Action");
        service.addMovie("Tiger", 2008, "Drama");
        service.addMovie("Padmaavat", 2006, "Romance");
        service.addMovie("Lunchbox", 2021, "Comedy");
        service.addMovie("Guru", 2006, "Drama");
        service.addMovie("Metro", 2006, "Romance");

        service.addUser("Rahul");
        service.addUser("Salmaan");
        service.addUser("Simran");

        service.addReview("Rahul", "Don", 2);
        service.addReview("Rahul", "Padmaavat", 8);

        service.addReview("Salmaan", "Don", 5);

        service.addReview("Simran", "Don", 9);
        service.addReview("Simran", "Guru", 6);
        
        // Exception
        service.addReview("Rahul", "Don", 10);

        // Exception
        service.addReview("Simran", "Lunchbox", 5);

        service.addReview("Rahul", "Tiger", 5);
        service.addReview("Rahul", "Metro", 7);

        int year = 2006;
        String genre = "Romance";
        
        System.out.println("Top movie of year "+year);
        String result = service.topMovieOfYear(year);
        if(result!=null)System.out.println(result);
        
        System.out.println("Top movie in "+genre);
        result = service.topMovieOfGenre(genre);
        if(result!=null)System.out.println(result);
        
        System.out.println("Top movie by Critics of year "+year);
        result = service.topMovieOfYearByCritic(year);
        if(result!=null)System.out.println(result);
        
        genre = "Drama";
        System.out.println("Top movie by Critics in "+genre);
        result = service.topMovieOfGenreByCritic(genre);
        if(result!=null)System.out.println(result);
        
        System.out.println("Top movie of year "+year+" by Average Rating");
        result = service.topAvgRatingMovieByYear(year);
        if(result!=null)System.out.println(result);
        
        System.out.println("Top movie in "+genre+" by Average Rating");
        result = service.topAvgRatingMovieByGenre(genre);
        if(result!=null)System.out.println(result);

        String movie = "Don";
        System.out.println("Average Rating of movie: "+movie);
        float rating = service.avgRatingOfMovie(movie);
        if(rating!=0)System.out.println(rating);
    }
}