package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.Bookmark;
import model.Movie;
import model.MovieList;
import util.DataUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Retrieve each video's id, title, middle interesting moment time, and smallest box art url
    DataSource: DataUtil.getMovies()
    Output: List of ImmutableMap.of("id", 5, "title", "some title", "time", new Date(), "url", "someUrl")
*/
public class Kata9 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();
        List<Map> videoInfo = movieLists.stream()
                .map(movie -> movie.getVideos())
                .flatMap(a -> a.stream().reduce((b, c) -> b.getUri().length() < c.getUri().length() ? b : c)
                        .stream().map(e -> ImmutableMap.of("id", e.getId(),
                                "title", e.getTitle(),
                                "middle time", e.getInterestingMoments().stream()
                                        .reduce((d, f) -> d.getType() == "Middle" ? d : f),
                                "boxart", e.getBoxarts(),
                                "url", e.getUri())))
                .collect(Collectors.toList());


//        return ImmutableList.of(ImmutableMap.of("id", 5, "title", "some title", "time", new Date(), "url", "someUrl"));
        return videoInfo;
    }

    public static void main(String[] args) {
        System.out.println(execute());
    }
}
