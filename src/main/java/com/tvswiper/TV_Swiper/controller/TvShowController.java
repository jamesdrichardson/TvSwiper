package com.tvswiper.TV_Swiper.controller;

import com.tvswiper.TV_Swiper.exception.DaoException;
import com.tvswiper.TV_Swiper.model.TvShow;
import com.tvswiper.TV_Swiper.service.TvShowService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/tvShows")
//delete when users are set up
@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
        }
)

public class TvShowController {

    private TvShowService tvShowService;

    public TvShowController(TvShowService tvShowService){this.tvShowService = tvShowService;}

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("permitAll()")
    public List<TvShow>getAllTvShows(){
        List<TvShow> tvShows = new ArrayList<>();

        try{
            tvShows = tvShowService.getTvShows();
        } catch (DaoException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return tvShows;
    }

    @RequestMapping(path = "/genre/{genre}", method = RequestMethod.GET)
    public List<TvShow> getTvShowsByGenre(@PathVariable String genre){
        List<TvShow> tvShows = new ArrayList<>();

        try{
            tvShows = tvShowService.getTvShowsByGenre(genre);
        } catch (DaoException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return tvShows;
    }


    //todo FIXED line 62 added path variable
    @RequestMapping(path = "/{tvShowId}", method = RequestMethod.GET)
    public TvShow getTvShowById(@PathVariable int tvShowId){
        TvShow tvShow = null;

        try{
            tvShow = tvShowService.getTvShowById(tvShowId);
            if (tvShow == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TV show not found...");
            }
        }catch (DaoException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return tvShow;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public TvShow addTvShow(@RequestBody TvShow newTvShow){
        TvShow tvShow = null;

        try{
            tvShow = tvShowService.addTvShow(newTvShow);
        }catch (DaoException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return tvShow;
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/{tvShowId}" , method = RequestMethod.DELETE)
    public void deleteTvShow(@PathVariable int tvShowId){
        TvShow tvShow = null;

        try{
            if (tvShowService.deleteTvShow(tvShowId) == false){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TV show not found...");
            }
        }catch (DaoException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @RequestMapping(path = "/{tvShowId}" , method = RequestMethod.PUT)
    public TvShow updateTvShow(@PathVariable int tvShowId, @RequestBody TvShow modifiedTvShow){
        TvShow tvShow = null;

        try{
            modifiedTvShow.setId(tvShowId);
            tvShow = tvShowService.updateTvShow(modifiedTvShow);
            if(tvShow == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TV show not found...");
            }
        }catch (DaoException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return tvShow;
    }


}
