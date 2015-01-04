package com.maksing.moviedbdata.service;

import com.maksing.moviedbdomain.entity.NavItem;
import com.maksing.moviedbdomain.entity.Page;
import com.maksing.moviedbdomain.service.NavigationService;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by maksing on 31/12/14.
 */
public class NavigationDataService extends HttpService implements NavigationService {

    private static class LazyHolder {
        private static final NavigationDataService INSTANCE = new NavigationDataService();
    }

    public static NavigationDataService getInstance() {
        return LazyHolder.INSTANCE;
    }

    @Override
    public Observable<List<NavItem>> getNavItems() {
        List<NavItem> items = new ArrayList<>();

        List<String> queries = new ArrayList<>();
        queries.add("primary_release_date.gte=2014-09-15&primary_release_date.lte=2014-12-31&sort_by=popularity.desc");
        queries.add("sort_by=popularity.desc");
        items.add(new NavItem("Home", new Page("Home", queries, "home")));

        items.add(new NavItem("Kids", new Page("Most popular kids movies", "certification_country=US&certification.lte=G&sort_by=popularity.desc", "kids")));
        items.add(new NavItem("The best of 2014", new Page("The best movies from 2014", "primary_release_year=2014&sort_by=vote_average.desc", "best2014")));
        return Observable.just(items);
    }
}