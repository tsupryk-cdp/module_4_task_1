package com.tsupryk.api.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * The class Film.
 * <p/>
 * User: Vitaliy
 * Date: 27.10.13
 */
@Document
public class Film {

    @Id
    private String id;

    private String title;

    private List<String> actors;

    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
