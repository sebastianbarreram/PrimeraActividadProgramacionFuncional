package katas;

import com.codepoetics.protonpack.StreamUtils;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.Bookmark;
import model.Movie;
import model.MovieList;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
    Goal: Combine videos and bookmarks by index (StreamUtils.zip) (https://github.com/poetix/protonpack)
    DataSource: DataUtil.getMovies()
    Output: List of ImmutableMap.of("videoId", "5", "bookmarkId", "3")
*/
public class Kata8 {
    public static List<Map> execute() {
        Stream<Movie> movies = DataUtil.getMovies().stream();
        Stream<Bookmark> bookMarks = DataUtil.getBookMarks().stream();

        //Con este metodo se puede recorrer el listado de bookMarks con el indice el objeto de la lista movie
//        List<Map> combinacion = movies.stream()
//                .map(movie -> ImmutableMap.of("id", movie.getId(), "bookmarkId", bookMarks.get(movies.indexOf(movie)).getId()))
//                .collect(Collectors.toList());
        List<Map> combinacion = StreamUtils.zip(movies,
                        bookMarks,
                        (movie, bookMark) -> ImmutableMap.of("videoId", movie.getId(),
                                "bookmarkId", bookMark.getId()))
                .collect(Collectors.toList());
        return combinacion;
    }

//    public static void main(String[] args) {
//        System.out.println(execute());
//    }
}
