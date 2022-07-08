package katas;

import model.Movie;
import util.DataUtil;

import java.util.Arrays;
import java.util.List;

/*
    Goal: Retrieve the url of the largest boxart using map() and reduce()
    DataSource: DataUtil.getMovieLists()
    Output: String
*/
public class Kata6 {
    public static String execute() {
        List<Movie> movies = DataUtil.getMovies();
        String longestUrl = movies.stream()
                .map(movie -> movie.getBoxarts())
                .flatMap(bokArtList -> bokArtList.stream().map(url -> url.getUrl()))
                .reduce((string1, string2) -> (string1.length() > string2.length()) ? string1 : string2)
                .get();

        return longestUrl;
    }

    public static void main(String[] args) {
        System.out.println(execute());
    }
}
