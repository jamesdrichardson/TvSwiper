package com.tvswiper.TV_Swiper.dao;

import com.tvswiper.TV_Swiper.model.TvShow;

import java.util.List;

    public interface TvShowDao {
        //gets TvShow by ID
        TvShow getTvShowById (int tvShowId);
        //gets all TvShow
        List<TvShow> getTvShows();
        //gets TvShow by genre can be overloaded in class for genre2? maybe using sql statement instead of overloading?
        List<TvShow> getTvShowByGenre(String genre);
        //creates TvShow
        TvShow createTvShow(TvShow newTvShow);
        //updates TvShow
        TvShow updateTvShow(TvShow tvShowToUpdate);
        //deletes TvShow by id
        int deleteTvShowById(int tvShowId);

    }
