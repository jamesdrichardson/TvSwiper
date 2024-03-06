package com.tvswiper.TV_Swiper.model;

import java.awt.*;
import java.math.BigDecimal;
    public class TvShow {

        int id;
        String name;
        String streamingService;
        String genre1;
        String genre2;
        String description;
        int episodeCount;
        int seasonCount;

        //for creating new tvShows without id


        public TvShow(int id, String name, String streamingService, String genre1, String genre2, String description, int episodeCount, int seasonCount) {
            this.id = id;
            this.name = name;
            this.streamingService = streamingService;
            this.genre1 = genre1;
            this.genre2 = genre2;
            this.description = description;
            this.episodeCount = episodeCount;
            this.seasonCount = seasonCount;
        }

        public TvShow(String name, String streamingService, String genre1, String genre2, String description, int episodeCount, int seasonCount) {
            this.name = name;
            this.streamingService = streamingService;
            this.genre1 = genre1;
            this.genre2 = genre2;
            this.description = description;
            this.episodeCount = episodeCount;
            this.seasonCount = seasonCount;
        }

        //for use with mapping functions
        public TvShow() {

        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStreamingService() {
            return streamingService;
        }

        public void setStreamingService(String streamingService) {
            this.streamingService = streamingService;
        }

        public String getGenre1() {
            return genre1;
        }

        public void setGenre1(String genre1) {
            this.genre1 = genre1;
        }

        public String getGenre2() {
            return genre2;
        }

        public void setGenre2(String genre2) {
            this.genre2 = genre2;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getEpisodeCount() {
            return episodeCount;
        }

        public void setEpisodeCount(int episodeCount) {
            this.episodeCount = episodeCount;
        }

        public int getSeasonCount() {
            return seasonCount;
        }

        public void setSeasonCount(int seasonCount) {
            this.seasonCount = seasonCount;
        }

        @Override
        public String toString() {
            return "TvShow{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", streamingService='" + streamingService + '\'' +
                    ", genre1='" + genre1 + '\'' +
                    ", genre2='" + genre2 + '\'' +
                    ", description='" + description + '\'' +
                    ", episodeCount=" + episodeCount +
                    ", seasonCount=" + seasonCount +
                    '}';
        }
    }

