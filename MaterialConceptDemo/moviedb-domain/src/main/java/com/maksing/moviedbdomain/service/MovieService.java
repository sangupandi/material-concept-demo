package com.maksing.moviedbdomain.service;

import com.maksing.moviedbdomain.entity.Movie;
import com.maksing.moviedbdomain.entity.MovieList;

import rx.Observable;

/**
 * Created by maksing on 26/12/14.
 */
public interface MovieService {
    public Observable<MovieList> getDiscoverMovieList(String query, int page, String posterBasePath, String backdropBasePath);
    public Observable<Movie> getMovieById(String id, String posterBasePath, String backdropBasePath);
    public Observable<Movie> getReviewsByMovieId(int id);
}
