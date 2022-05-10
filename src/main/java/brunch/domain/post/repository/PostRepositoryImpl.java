package brunch.domain.post.repository;

import brunch.domain.comment.dto.CommentResponseDto;
import brunch.domain.post.dto.PostQueryDto;
import brunch.domain.post.dto.PostResponseDto;
import brunch.domain.post.dto.QPostQueryDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.*;

import static brunch.domain.comment.QComment.*;
import static brunch.domain.member.QMember.*;
import static brunch.domain.post.QPost.*;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final EntityManager em;

    @Override
    public PostResponseDto readPost(Long postId) {
        PostResponseDto findPost = queryFactory
                .select(Projections.constructor(
                        PostResponseDto.class,
                        post.id,
                        member.id,
                        post.title,
                        post.content,
                        post.view,
                        post.empathy,
                        post.commentNum
                ))
                .from(post)
                .join(post.member, member)
                .where(post.id.eq(postId))
                .fetchOne();

        if (findPost == null) {
            throw new IllegalStateException("Can't find this post : " + postId);
        }

        List<CommentResponseDto> comments = queryFactory
                .select(Projections.constructor(
                        CommentResponseDto.class,
                        comment.id,
                        member.id,
                        comment.post.id,
                        comment.content
                ))
                .from(comment)
                .join(comment.member, member)
                .where(comment.post.id.eq(postId))
                .fetch();

        findPost.setComments(comments);

        return findPost;
    }

    @Override
    public List<PostQueryDto> searchByAccuracyOrder(String keyword) {
        List<PostQueryDto> posts = queryFactory
                .select(new QPostQueryDto(
                        post.id,
                        post.title,
                        post.content,
                        post.commentNum,
                        post.createdDate,
                        member.id,
                        member.nickName
                ))
                .from(post)
                .join(post.member, member)
                .where(post.title.contains(keyword)
                        .or(post.content.contains(keyword))
                )
                .fetch();

        Map<PostQueryDto, Integer> countMap = new HashMap<>();

        for (PostQueryDto post : posts) {
            int count1 = countKeyword(post.getTitle(), keyword);
            int count2 = countKeyword(post.getContent(), keyword);

            countMap.put(post, count1 + count2);
        }

        //value(keyword 개수) 내림차순 정렬
        List<Map.Entry<PostQueryDto, Integer>> entries = new ArrayList<>(countMap.entrySet());
        Collections.sort(entries, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        List<PostQueryDto> sortedPosts = new ArrayList<>();

        for (Map.Entry<PostQueryDto, Integer> entry : entries) {
            sortedPosts.add(entry.getKey());
        }

        return sortedPosts;
    }

    @Override
    public List<PostQueryDto> searchByDateOrder(String keyword) {
        return queryFactory
                .select(new QPostQueryDto(
                        post.id,
                        post.title,
                        post.content,
                        post.commentNum,
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

    private int countKeyword(String str, String keyword) {
        return str.length() - str.replace(keyword, "").length();
    }
}
