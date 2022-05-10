package brunch.domain.post.repository;

import brunch.domain.member.Member;
import brunch.domain.post.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
class PostRepositoryImplTest {

    @Autowired PostRepository postRepository;
    @Autowired EntityManager em;
    Member member;

    @BeforeEach
    void beforeEach() {
        member = createMember("qkrdbsgh");
        em.persist(member);
    }

    @Test
    @Commit
    void searchByAccuracyOrder() throws Exception {
        //given
        Post post1 = createPost(member, "find", "find");
        Post post2 = createPost(member, "findfind", "findfind");
        Post post3 = createPost(member, "findfindfind", "findfindfind");
        Post post4 = createPost(member, "findfindfindfind", "findfindfindfind");
        Post post5 = createPost(member, "findfindfindfindfind", "findfindfindfindfind");

        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);
        postRepository.save(post4);
        postRepository.save(post5);

        //when
        postRepository.searchByAccuracyOrder("find");

        //then
    }

    private Member createMember(String nickName) {
        return Member.builder()
                .nickName(nickName)
                .build();
    }

    private Post createPost(Member member, String title, String content) {
        return Post.builder()
                .member(member)
                .title(title)
                .content(content)
                .build();
    }
}