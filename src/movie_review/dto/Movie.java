package movie_review.dto;

import java.util.Comparator;
public class Movie implements Comparator<Movie>{
    private String name;
    private int releaseYear;
    private String genre;

    public Movie(String name) {
        this.name = name;
    }
    
    public Movie(String name, int releaseYear, String genre) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.genre = genre;
    }

    public int compare(Movie m1, Movie m2){
        return m1.getName().compareTo(m2.getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Movie other = (Movie) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Movie [genre=" + genre + ", name=" + name + ", releaseYear=" + releaseYear + "]";
    }

}
