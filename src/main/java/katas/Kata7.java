package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.Bookmark;
import model.BoxArt;
import model.Movie;
import model.MovieList;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Retrieve the id, title, and smallest box art url for every video
    DataSource: DataUtil.getMovieLists()
    Output: List of ImmutableMap.of("id", "5", "title", "Bad Boys", "boxart": "url)
*/
public class Kata7 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();
        List<Map> shortestUrlMap = movieLists.stream()
                .map(movie -> movie.getVideos())
                .flatMap(a -> a.stream().reduce((b, c) -> b.getUri().length() < c.getUri().length() ? b : c)
                        .stream().map(e -> ImmutableMap.of("id", e.getId(),
                                "title", e.getTitle(),
                                "boxart", e.getBoxarts(),
                                "url", e.getUri())))
                .collect(Collectors.toList());

        return shortestUrlMap;
    }

    public static void main(String[] args) {
        System.out.println(execute().size());
    }
}
