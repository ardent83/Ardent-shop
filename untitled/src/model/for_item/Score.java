package model.for_item;

import model.for_user.User;

public class Score {
    public Score(User user, double score, Item item) {
        this.user = user;
        this.score = score;
        this.item = item;
    }
    private final User user;
    private double score;
    private final Item item;

    public User getUser() {
        return user;
    }

    public double getScore() {
        return score;
    }

    public Item getItem() {
        return item;
    }
}
