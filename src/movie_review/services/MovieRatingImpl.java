package movie_review.services;

import movie_review.utility.CustomException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import movie_review.dao.*;
import movie_review.dto.Movie;
import movie_review.dto.Person;
import movie_review.dto.Score;

public class MovieRatingImpl implements MovieRatingService {
    private Movies movies;
    private Reviews reviews;
    private Users users;

    public MovieRatingImpl() {
        this.movies = new Movies();
        this.reviews = new Reviews();
        this.users = new Users();
    }

    @Override
    public void addMovie(String name, int year, String genre) {
        try {
            this.movies.addMovie(name, year, genre);
        } catch (CustomException ce) {
            System.out.println(ce.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void addUser(String name) {
        try {
            this.users.createUser(name);
        } catch (CustomException ce) {
            System.out.println(ce.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void addReview(String userName, String movieName, int rating) {
        try {
            if (userName == null || userName.length() < 1) {
                throw new CustomException("User doesn't exists");
            }
            if (movieName == null || movieName.length() < 1) {
                throw new CustomException("Movie doesn't exists");
            }
            if (rating < 0 || rating > 10) {
                throw new CustomException("Invalid Rating");
            }
            boolean isNotCritic = true;
            Person user = this.users.getCritic(userName);
            if (user != null) {
                isNotCritic = false;
                rating = rating * 2;
            } else {
                user = this.users.getUser(userName);
                if (user == null) {
                    throw new CustomException("User doesn't exists");
                }
            }
            HashSet<Score> set = this.reviews.getReviews();
            int count = 0;
            for (Score entry : set) {
                if (entry.getUser().getName().equals(userName)) {
                    count++;
                }
            }
            if (isNotCritic && count == 2) {
                users.makeCritic(user);

            }
            Movie movie = this.movies.getMovie(movieName);
            if (movie == null) {
                throw new CustomException("Exception: Movie Doesn't Exists");
            } else if (movie.getReleaseYear() > 2020) {
                throw new CustomException("Exception: Movie not released yet");
            }
            this.reviews.createReview(movie, user, rating);
        } catch (CustomException ce) {
            System.out.println(ce.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String topMovieOfYear(int year) {
        if (year < 1900 && year > 2030) {
            System.out.println("Exception: No Movie in that year!");
        }
        List<String> result = new ArrayList<>();
        int maxRating = 0;
        HashMap<String, Integer> movieRatings = new HashMap<>();
        HashSet<Score> set = this.reviews.getReviews();
        for (Score entry : set) {
            Movie movie = entry.getMovie();
            if (movie.getReleaseYear() == year) {
                Integer rating = movieRatings.get(movie.getName());
                if (rating != null) {
                    rating += entry.getRating();
                    movieRatings.put(movie.getName(), rating);
                    if (rating == maxRating) {
                        result.add(movie.getName());
                    } else if (rating > maxRating) {
                        maxRating = rating;
                        result.clear();
                        result.add(movie.getName());
                    }
                } else {
                    rating = entry.getRating();
                    if (rating > maxRating) {
                        maxRating = rating;
                        result.clear();
                        result.add(movie.getName());
                    }
                    movieRatings.put(movie.getName(), entry.getRating());
                }
            }
        }
        if (result.size() < 1) {
            System.out.println("Exception: No Movie in that year!");
            return null;
        } else {
            return String.join(", ", result) + " - " + maxRating;
        }
    }

    @Override
    public String topMovieOfGenre(String genre) {
        if (genre == null) {
            System.out.println("Exception: No Movie in that genre!");
            return null;
        }
        List<String> result = new ArrayList<>();
        int maxRating = 0;
        HashMap<String, Integer> movieRatings = new HashMap<>();
        HashSet<Score> set = this.reviews.getReviews();
        for (Score entry : set) {
            Movie movie = entry.getMovie();
            if (genre.equals(movie.getGenre())) {
                Integer rating = movieRatings.get(movie.getName());
                if (rating != null) {
                    rating += entry.getRating();
                    movieRatings.put(movie.getName(), rating);
                    if (rating == maxRating) {
                        result.add(movie.getName());
                    } else if (rating > maxRating) {
                        maxRating = rating;
                        result.clear();
                        result.add(movie.getName());
                    }
                } else {
                    rating = entry.getRating();
                    if (rating > maxRating) {
                        maxRating = rating;
                        result.clear();
                        result.add(movie.getName());
                    }
                    movieRatings.put(movie.getName(), entry.getRating());
                }
            }
        }
        if (result.size() < 1) {
            System.out.println("Exception: No Movie in that genre!");
            return null;
        } else {
            return String.join(", ", result) + " - " + maxRating;
        }
    }

    @Override
    public String topMovieOfYearByCritic(int year) {
        if (year < 1900 && year > 2030) {
            System.out.println("Exception: No Movie in that year!");
        }
        List<String> result = new ArrayList<>();
        int maxRating = 0;
        HashMap<String, Integer> movieRatings = new HashMap<>();
        HashSet<Score> set = this.reviews.getReviews();
        for (Score entry : set) {
            Movie movie = entry.getMovie();
            Person user = users.getCritic(entry.getUser());
            if (user!=null && entry.getMovie().getReleaseYear()==year) {
                Integer rating = movieRatings.get(movie.getName());
                if (rating != null) {
                    rating += entry.getRating();
                    movieRatings.put(movie.getName(), rating);
                    if (rating == maxRating) {
                        result.add(movie.getName());
                    } else if (rating > maxRating) {
                        maxRating = rating;
                        result.clear();
                        result.add(movie.getName());
                    }
                } else {
                    rating = entry.getRating();
                    if (rating > maxRating) {
                        maxRating = rating;
                        result.clear();
                        result.add(movie.getName());
                    }
                    movieRatings.put(movie.getName(), entry.getRating());
                }
            }
        }
        if (result.size() < 1) {
            System.out.println("Exception: No Movie in that genre!");
            return null;
        } else {
            return String.join(", ", result) + " - " + maxRating;
        }
    }

    @Override
    public String topMovieOfGenreByCritic(String genre) {
        if (genre == null) {
            System.out.println("Exception: No Movie in that genre!");
            return null;
        }
        List<String> result = new ArrayList<>();
        int maxRating = 0;
        HashMap<String, Integer> movieRatings = new HashMap<>();
        HashSet<Score> set = this.reviews.getReviews();
        for (Score entry : set) {
            Movie movie = entry.getMovie();
            Person user = users.getCritic(entry.getUser());
            if (user!=null && genre.equals(movie.getGenre())) {
                Integer rating = movieRatings.get(movie.getName());
                if (rating != null) {
                    rating += entry.getRating();
                    movieRatings.put(movie.getName(), rating);
                    if (rating == maxRating) {
                        result.add(movie.getName());
                    } else if (rating > maxRating) {
                        maxRating = rating;
                        result.clear();
                        result.add(movie.getName());
                    }
                } else {
                    rating = entry.getRating();
                    if (rating > maxRating) {
                        maxRating = rating;
                        result.clear();
                        result.add(movie.getName());
                    }
                    movieRatings.put(movie.getName(), entry.getRating());
                }
            }
        }
        if (result.size() < 1) {
            System.out.println("Exception: No Movie in that genre!");
            return null;
        } else {
            return String.join(", ", result) + " - " + maxRating;
        }
    }

    @Override
    public String topAvgRatingMovieByYear(int year) {
        if (year < 1900 && year > 2030) {
            System.out.println("Exception: No Movie in that year!");
        }
        String result = null;
        float maxAvgRating = 0;
        HashMap<String, Integer[]> movieRatings = new HashMap<>();
        HashSet<Score> set = this.reviews.getReviews();
        for (Score entry : set) {
            Movie movie = entry.getMovie();
            if (movie.getReleaseYear() == year) {
                Integer[] rating = movieRatings.get(movie.getName());
                if (rating != null) {
                    rating[0] += entry.getRating();
                    rating[1]++;
                    movieRatings.put(movie.getName(), rating);
                    float avgRating = (float)rating[0]/rating[1];
                    if (avgRating > maxAvgRating) {
                        maxAvgRating = avgRating;
                        result = movie.getName();
                    }
                } else {
                    Integer[] res = {entry.getRating(), 1};
                    if (res[0] > maxAvgRating) {
                        maxAvgRating = res[0];
                        result = movie.getName();
                    }
                    movieRatings.put(movie.getName(), res);
                }
            }
        }
        if (result == null) {
            System.out.println("Exception: No Movie in that year!");
            return null;
        } else {
            return result + " - " + maxAvgRating;
        }
    }

    @Override
    public String topAvgRatingMovieByGenre(String genre) {
        if (genre == null) {
            System.out.println("Exception: No Movie in that genre!");
            return null;
        }
        String result = null;
        float maxAvgRating = 0;
        HashMap<String, Integer[]> movieRatings = new HashMap<>();
        HashSet<Score> set = this.reviews.getReviews();
        for (Score entry : set) {
            Movie movie = entry.getMovie();
            Person user = users.getCritic(entry.getUser());
            if (user!=null && genre.equals(movie.getGenre())) {
                Integer[] rating = movieRatings.get(movie.getName());
                if (rating != null) {
                    rating[0] += entry.getRating();
                    rating[1]++;
                    movieRatings.put(movie.getName(), rating);
                    float avgRating = (float)rating[0]/rating[1];
                    if (avgRating > maxAvgRating) {
                        maxAvgRating = avgRating;
                        result = movie.getName();
                    }
                } else {
                    Integer[] res = {entry.getRating(), 1};
                    if (res[0] > maxAvgRating) {
                        maxAvgRating = res[0];
                        result = movie.getName();
                    }
                    movieRatings.put(movie.getName(), res);
                }
            }
        }
        if (result == null) {
            System.out.println("Exception: No Movie in that genre!");
            return null;
        } else {
            return result + " - " + maxAvgRating;
        }
    }

    @Override
    public float avgRatingOfMovie(String name) {
        int total = 0;
        int count = 0;
        HashSet<Score> set = this.reviews.getReviews();
        for (Score entry : set) {
            Movie movie = entry.getMovie();
            if (name.equals(movie.getName())) {
                total += entry.getRating(); 
                count++;
            }
        }
        if(count == 0){
            System.out.println("No Rating for that movie!");
            return 0;
        }
        return (float)total/count;
    }

}
