package com.tvswiper.TV_Swiper.service;

import com.tvswiper.TV_Swiper.dao.TvShowDao;
import com.tvswiper.TV_Swiper.model.TvShow;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TvShowService {

    private TvShowDao tvShowDao;

    public TvShowService(TvShowDao tvShowDao) {
        this.tvShowDao = tvShowDao;
    }

    public List<TvShow> getTvShows(){
        List<TvShow> results = new ArrayList<>();

        results = tvShowDao.getTvShows();
        return results;
    }

    public List<TvShow> getTvShowsByGenre(String genre){
        List<TvShow> results = new ArrayList<>();

        results = tvShowDao.getTvShowByGenre(genre);
        return results;
    }

    public TvShow getTvShowById(int tvShowId){
        TvShow tvShow = null;

        tvShow = tvShowDao.getTvShowById(tvShowId);

        return tvShow;
    }

    //Create Update Delete methods
    public TvShow addTvShow(TvShow newTvShow){
        TvShow tvShow = null;
        tvShow = tvShowDao.createTvShow(newTvShow);

        return tvShow;
    }

    public TvShow updateTvShow(TvShow updatedTvShow){
        TvShow tvShow = null;
        // TvShow existingTvShow = tvShowDao.getTvShowById(updatedTvShoe.getId());
        tvShow = tvShowDao.updateTvShow(updatedTvShow);

        return tvShow;
    }

    public boolean deleteTvShow(int tvShowId){
        boolean tvShowDeleted = false;
        TvShow tvShow = tvShowDao.getTvShowById(tvShowId);

        if (tvShow != null){
            int deleteCount = tvShowDao.deleteTvShowById(tvShowId);
            tvShowDeleted = (deleteCount != 0);
        }
        return tvShowDeleted;
    }
}
