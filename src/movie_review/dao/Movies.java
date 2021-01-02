package movie_review.dao;

import java.util.HashSet;
import movie_review.dto.Movie;
import movie_review.utility.CustomException;

public class Movies {
    private HashSet<Movie> movies;

	public Movies() {
        this.movies = new HashSet<>();
    }
    
    public void addMovie(String movieName, int releaseYear, String genre) throws CustomException{
        Movie movie = new Movie(movieName, releaseYear, genre);
        if(movies.contains(movie)){
            throw new CustomException("Movie already exists");
        } else {
            movies.add(movie);
        }
    }

    public Movie getMovie(String movieName){
        Movie movie = new Movie(movieName);
        for(Movie mv: movies){
            if(mv.equals(movie)){
                return mv;
            }
        }
        return null;
    }
}
