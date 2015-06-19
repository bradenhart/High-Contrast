package com.bradenhart.hctester;

/**
 * Created by bradenhart on 17/06/15.
 */
public class ChallengeStatusListItem {

    private String title;
    private Boolean statusCompleted;

    public ChallengeStatusListItem() {}

    public ChallengeStatusListItem(String title) {
        this.title = title;
    }

    public Boolean getStatusCompleted() {
        return statusCompleted;
    }

    public void setStatusCompleted(Boolean statusCompleted) {
        this.statusCompleted = statusCompleted;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
