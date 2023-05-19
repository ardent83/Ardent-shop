package model.for_item;
import model.for_user.User;

public class Comment {
    public Comment(User commentUser, String idItem, String commentText, boolean buyer) {
        this.commentUser = commentUser;
        this.idItem = idItem;
        this.commentText = commentText;
        this.commentStatus = CommentStatus.WAITING;
        this.buyer = buyer;
    }
    private final User commentUser;
    private final String idItem;
    private final String commentText;
    private CommentStatus commentStatus;
    private final boolean buyer;

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

    public void setCommentStatus(CommentStatus commentStatus) {
        this.commentStatus = commentStatus;
    }

    public boolean isBuyer() {
        return buyer;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nuser ID : ").append(commentUser);
        if (buyer){
            stringBuilder.append("\nis a buyer");
        } else {
            stringBuilder.append("\nnot a buyer");
        }
        stringBuilder.append("\n").append(commentText);

        return  stringBuilder.toString();
    }
}