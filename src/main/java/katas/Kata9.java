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
                .map(movieList -> movieList.getVideos())
                .flatMap(video -> video.stream().reduce(
                                (url1, url2) -> url1.getUri().length() < url2.getUri().length() ? url1 : url2)
                        .stream().map(element -> ImmutableMap.of("id", element.getId(),
                                "title", element.getTitle(),
                                "middle time", element.getInterestingMoments().stream()
                                        .filter(time -> time.getType() == "Middle")
                                        .findFirst()
                                        .get(),
                                "boxart", element.getBoxarts(),
                                "url", element.getUri())))
                .collect(Collectors.toList());


//        return ImmutableList.of(ImmutableMap.of("id", 5, "title", "some title", "time", new Date(), "url", "someUrl"));
        return videoInfo;
    }

    public static void main(String[] args) {
        System.out.println(execute());
    }
}
