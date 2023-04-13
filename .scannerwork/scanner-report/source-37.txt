package com.sofkau.models.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseGame {

    private int id;
    private String title;
    private String thumbnail;
    private String status;
    private String shortDescription;
    private String description;
    private String gameUrl;
    private String genre;
    private String platform;
    private String publisher;
    private String developer;
    private String releaseDate;
    private String freeToGameProfileUrl;
    private MinimumSystemRequirements minimumSystemRequirements;
    private List<Screenshot> screenshots;


    public ResponseGame() {
    }

    public ResponseGame(int id, String title, String thumbnail, String status, String shortDescription, String description, String gameUrl, String genre, String platform, String publisher, String developer, String releaseDate, String freeToGameProfileUrl, MinimumSystemRequirements minimumSystemRequirements, List<Screenshot> screenshots) {
        this.id = id;
        this.title = title;
        this.thumbnail = thumbnail;
        this.status = status;
        this.shortDescription = shortDescription;
        this.description = description;
        this.gameUrl = gameUrl;
        this.genre = genre;
        this.platform = platform;
        this.publisher = publisher;
        this.developer = developer;
        this.releaseDate = releaseDate;
        this.freeToGameProfileUrl = freeToGameProfileUrl;
        this.minimumSystemRequirements = minimumSystemRequirements;
        this.screenshots = screenshots;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGameUrl() {
        return gameUrl;
    }

    public void setGameUrl(String gameUrl) {
        this.gameUrl = gameUrl;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getFreeToGameProfileUrl() {
        return freeToGameProfileUrl;
    }

    public void setFreeToGameProfileUrl(String freeToGameProfileUrl) {
        this.freeToGameProfileUrl = freeToGameProfileUrl;
    }

    public MinimumSystemRequirements getMinimumSystemRequirements() {
        return minimumSystemRequirements;
    }

    public void setMinimumSystemRequirements(MinimumSystemRequirements minimumSystemRequirements) {
        this.minimumSystemRequirements = minimumSystemRequirements;
    }

    public List<Screenshot> getScreenshots() {
        return screenshots;
    }

    public void setScreenshots(List<Screenshot> screenshots) {
        this.screenshots = screenshots;
    }
}
