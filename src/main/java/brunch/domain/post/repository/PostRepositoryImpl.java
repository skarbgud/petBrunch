package brunch.domain.post.repository;

import brunch.domain.post.dto.PostQueryDto;
import brunch.domain.post.dto.QPostQueryDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static brunch.domain.member.QMember.*;
import static brunch.domain.post.QPost.*;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<PostQueryDto> searchByAccuracyOrder(String keyword) {
        return queryFactory
                .select(new QPostQueryDto(
                        post.id,
                        post.title,
                        post.content,
                        post.commentNum,
                        post.picture,
                        post.createdDate,
                        member.id,
                        member.nickName
                ))
                .from(post)
                .join(post.member, member)
                .where(post.title.contains(keyword)
                        .or(post.content.contains(keyword))
                )
                .orderBy()
                .fetch();
    }

    @Override
    public List<PostQueryDto> searchByDateOrder(String keyword) {
        return queryFactory
                .select(new QPostQueryDto(
                        post.id,
                        post.title,
                        post.content,
                        post.commentNum,
                        post.picture,
                        post.createdDate,
                        member.id,
                        member.nickName
                ))
                .from(post)
                .join(post.member, member)
                .where(post.title.contains(keyword)
                        .or(post.content.contains(keyword))
                )
                .orderBy(post.createdDate.desc())
                .fetch();
    }
}
