package brunch.domain.comment;

import brunch.domain.TimeEntity;
import brunch.domain.member.Member;
import brunch.domain.post.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends TimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    private String content;

    @Builder
    private Comment(Member member, Post post, String content) {
        this.member = member;
        this.post = post;
        this.content = content;
    }
}
