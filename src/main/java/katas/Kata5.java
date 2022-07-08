package katas;

import model.Movie;
import util.DataUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
    Goal: Retrieve the largest rating using reduce()
    DataSource: DataUtil.getMovies()
    Output: Double
*/
public class Kata5 {
    public static Double execute() {
        List<Movie> movies = DataUtil.getMovies();

        Double maxRating=movies.stream()
                .map(a->a.getRating())
                .reduce((a,b)->Double.max(a, b))
                .get();
        return maxRating;
    }

    public static void main(String[] args) {
        System.out.println(execute());
    }
}
