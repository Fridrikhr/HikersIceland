package is.hi.g.hikersicelands.hikersicelands.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String hikeId;
    private String userId;
    private String reviewText;
    private int rating;


    // Smiður til þess að gera review object
    public Review(String hikeId, String userId, String reviewText, int rating) {
        this.hikeId = hikeId;
        this.userId = userId;
        this.reviewText = reviewText;
        this.rating = rating;
    }

    // Getters og Setters fyrir allar breyturnar í þessu review entity
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameOfHike() {
        return hikeId;
    }

    public void setNameOfHike(String hikeId) {
        this.hikeId = hikeId;
    }

    public String getNameOfUser() {
        return userId;
    }

    public void setNameOfUser(String nameOfUser) {
        this.userId = nameOfUser;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
