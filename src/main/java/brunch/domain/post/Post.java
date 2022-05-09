package brunch.domain.post;

import brunch.domain.TimeEntity;
import brunch.domain.comment.Comment;
import brunch.domain.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends TimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    private String title;
    private String content;
    private int view;
    private int empathy;
    private int commentNum;

    @Builder
    private Post(Member member, String title, String content, int view, int empathy, int commentNum) {
        this.member = member;
        this.title = title;
        this.content = content;
        this.view = view;
        this.empathy = empathy;
        this.commentNum = commentNum;
    }

    public void updateField(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
