package jjfactory.hodol.depth;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class DepthRes {
    private String comment;
    private String articleName;
    private String writerName;
    private String commenterName;

    public DepthRes(String comment, String articleName, String writerName,String commenterName) {
        this.comment = comment;
        this.articleName = articleName;
        this.writerName = writerName;
        this.commenterName = commenterName;
    }

    public DepthRes(Comment comment) {
        this.comment = comment.getContent();
        this.articleName = comment.getArticle().getName();
        this.writerName = comment.getArticle().getWriter().getName();
        this.commenterName = comment.getWriter().getName();
    }
}
