package com.maksing.materialconceptdemo.presentation.listing;

import com.maksing.materialconceptdemo.presentation.Presenter;
import com.maksing.moviedbdomain.entity.Movie;
import com.maksing.moviedbdomain.entity.MovieList;
import com.maksing.moviedbdomain.entity.Page;
import com.maksing.moviedbdomain.query.DiscoverQuery;
import com.maksing.moviedbdomain.usecase.movie.GetDiscoverListUseCase;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by maksing on 23/12/14.
 */
public class SingleListPresenter extends Presenter<SingleListView> {
    private final Page mPage;

    private List<Movie> mMovies;
    private final GetDiscoverListUseCase mGetDiscoverListUseCase;
    private Observable<MovieList> mGetMovieListRequest;

    private int mCurrentListPage = 1;
    private boolean mIsLastPage;

    public SingleListPresenter(Page page, GetDiscoverListUseCase getDiscoverListUseCase) {
        mGetDiscoverListUseCase = getDiscoverListUseCase;
        mPage = page;
    }

    @Override
    protected void restoreView() {
    }

    @Override
    protected boolean shouldRestore() {
        return false;
    }

    @Override
    protected void initializeView() {

        mMovies = new ArrayList<>();

        if (mGetMovieListRequest == null) {
            mGetMovieListRequest = mGetDiscoverListUseCase.getObservable(new DiscoverQuery() {
                                                                             @Override
                                                                             public String getQuery() {
                                                                                 return mPage.getDiscoverQueryAt(0);
                                                                             }

                                                                             @Override
                                                                             public int getPage() {
                                                                                 return mCurrentListPage;
                                                                             }
                                                                         }).cache().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        }
        getView().showProgressbar();
        addSubscription(mGetMovieListRequest.subscribe(new Subscriber<MovieList>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                //TODO: error handling
            }

            @Override
            public void onNext(MovieList movieList) {
                mMovies.addAll(movieList.getMovies());
                mCurrentListPage = movieList.getPage();
                mIsLastPage = movieList.isLastPage();
                getView().displayLists(mMovies);
            }
        }));
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    public void onPosterClicked(Movie movie) {
        getView().displayDetailsPage(movie.getId());
    }
}
