package model.for_item;
import model.for_user.User;

public class Comment {
    public Comment(User commentUser, String idItem, String commentText, CommentStatus commentStatus, boolean buyer) {
        this.commentUser = commentUser;
        this.idItem = idItem;
        this.commentText = commentText;
        this.commentStatus = commentStatus;
        this.buyer = buyer;
    }
    private User commentUser;
    private String idItem;
    private String commentText;
    private CommentStatus commentStatus;
    private boolean buyer;

    public User getCommentUser() {
        return commentUser;
    }

    public String getIdItem() {
        return idItem;
    }

    public String getCommentText() {
        return commentText;
    }

    public CommentStatus getCommentStatus() {
        return commentStatus;
    }

    public boolean isBuyer() {
        return buyer;
    }
}