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
                .map(movieList -> movieList.getVideos())
                .flatMap(video -> video.stream().reduce(
                                (url1, url2) -> url1.getUri().length() < url2.getUri().length() ? url1 : url2)
                        .stream().map(element -> ImmutableMap.of("id", element.getId(),
                                "title", element.getTitle(),
                                "boxart", element.getBoxarts(),
                                "url", element.getUri())))
                .collect(Collectors.toList());

        return shortestUrlMap;
    }

//    public static void main(String[] args) {
//        System.out.println(execute());
//    }
}
