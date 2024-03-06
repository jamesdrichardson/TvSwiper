package com.tvswiper.TV_Swiper.dao;

import com.tvswiper.TV_Swiper.exception.DaoException;
import com.tvswiper.TV_Swiper.model.TvShow;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTvShowDao implements TvShowDao{

    private final JdbcTemplate jdbcTemplate;
    public JdbcTvShowDao(DataSource dataSource){jdbcTemplate = new JdbcTemplate(dataSource);}
    @Override
    public TvShow getTvShowById(int tvShowId) {
        TvShow tvShow = null;

        String sql = "select * from tv_show where tv_show_id = ?";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, tvShowId);
            if (results.next()) {
                tvShow = mapRowToTvShow(results);
            }
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server...");
        }
        return tvShow;
    }

    @Override
    public List<TvShow> getTvShows() {
        List<TvShow> tvShows = new ArrayList<>();

        String sql = "select * from tv_show order by tv_show_id";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()){
                TvShow tvShow = mapRowToTvShow(results);
                tvShows.add(tvShow);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server...", e);
        }
        return tvShows;
    }

    @Override
    public List<TvShow> getTvShowByGenre(String genre) {
        List<TvShow> tvShows = new ArrayList<>();

        String sql = "select * from tv_show where tv_show_genre1 = ? or tv_show_genre2 = ? order by tv_show_id";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, genre, genre);
            while (results.next()){
                TvShow tvShow = mapRowToTvShow(results);
                tvShows.add(tvShow);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server...", e);
        }
        return tvShows;
    }

    @Override
    public TvShow createTvShow(TvShow newTvShow) {
        TvShow tvShowToAdd = null;

        String sql = "insert into tv_show (tv_show_name, tv_show_streaming_service, tv_show_genre1, values (?, ?, ?) returning tv_show_id";

        try {
            int newTvShowId = jdbcTemplate.queryForObject(sql, int.class, newTvShow.getName(), newTvShow.getStreamingService(), newTvShow.getGenre1());

            tvShowToAdd = getTvShowById((newTvShowId));
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server...", e);
        } catch (DataIntegrityViolationException e){
            throw new DaoException("Data Integrity Violation", e);
        }

        return tvShowToAdd;
    }

    @Override
    public TvShow updateTvShow(TvShow tvShowToUpdate) {
        TvShow tvShow = null;

        String sql = "update tv_show set tv_show_name = ?, tv_show_streaming_service = ?, tv_show_genre1 = ?, tv_show_genre2 = ?, " +
                "tv_show_description = ?, episode_count = ?, season_count = ?, ";
        try {
            int rowsAffected = jdbcTemplate.update(sql, tvShowToUpdate.getName(), tvShowToUpdate.getStreamingService(), tvShowToUpdate.getGenre1(),
                    tvShowToUpdate.getGenre2(), tvShowToUpdate.getDescription(), tvShowToUpdate.getEpisodeCount(),
                    tvShowToUpdate.getSeasonCount());

            if(rowsAffected == 0){
                throw new DaoException("Zero rows affected, expected at least one...");
            }
            tvShow = getTvShowById(tvShowToUpdate.getId());
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server...");
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Data Integrity Violation", e);
        }
        return tvShow;
    }

    @Override
    public int deleteTvShowById(int tvShowId) {
        int count;

        String sql = "delete from tv_show where tv_show_id = ?";

        try{
            count = jdbcTemplate.update(sql, tvShowId);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server...", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Data Integrity Violation", e);
        }
        return count;
    }

    private TvShow mapRowToTvShow(SqlRowSet rs){
        TvShow tvShow = new TvShow();

        tvShow.setId(rs.getInt("tv_show_id"));
        tvShow.setName(rs.getString("tv_show_name"));
        tvShow.setStreamingService(rs.getString("tv_show_streaming_service"));
        tvShow.setGenre1(rs.getString("tv_show_genre1"));
        tvShow.setGenre2(rs.getString("tv_show_genre2"));
        tvShow.setDescription(rs.getString("tv_show_description"));
        tvShow.setEpisodeCount(rs.getInt("episode_count"));
        tvShow.setSeasonCount(rs.getInt("season_count"));

        return tvShow;
    }
}
