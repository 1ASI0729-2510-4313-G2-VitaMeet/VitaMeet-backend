package upc.edu.pe.vitameet_backend.evaluation.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public class Rating {

    private int score;

    protected Rating() {}

    public Rating(int score) {
        if (score < 1 || score > 5)
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
