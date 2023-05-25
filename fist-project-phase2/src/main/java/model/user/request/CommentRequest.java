package model.user.request;

import model.item.Comment;

public class CommentRequest extends Request{
    public CommentRequest(Comment comment) {
        this.comment = comment;
    }

    private Comment comment;

    public Comment getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return (new StringBuilder().append("\nRequest ID : ").append(super.idRequest)
                .append(comment.toString()).toString());
    }
}
