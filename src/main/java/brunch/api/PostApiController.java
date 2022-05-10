package brunch.api;

import brunch.domain.member.Member;
import brunch.domain.member.MemberRepository;
import brunch.domain.post.Post;
import brunch.domain.post.dto.PostQueryDto;
import brunch.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostApiController {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @GetMapping("/test/p1")
    public List<PostQueryDto> test1(@RequestParam("word") String keyword) {
        return postRepository.searchByAccuracyOrder(keyword);
    }

    @GetMapping("/test/p2")
    public List<PostQueryDto> test2(@RequestParam("word") String keyword) {
        return postRepository.searchByDateOrder(keyword);
    }

    @PostConstruct
    public void init() {
        Member member = createMember("yunho");
        memberRepository.save(member);

        Post post1 = createPost(member, "find", "find");
        Post post2 = createPost(member, "findfind", "findfind");
        Post post3 = createPost(member, "findfindfind", "findfindfind");
        Post post4 = createPost(member, "findfindfindfind", "findfindfindfind");
        Post post5 = createPost(member, "findfindfindfindfind", "findfindfindfindfind");

        postRepository.save(post1);
        postRepository.save(post3);
        postRepository.save(post5);
        postRepository.save(post2);
        postRepository.save(post4);
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
