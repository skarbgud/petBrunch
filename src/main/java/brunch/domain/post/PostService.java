package brunch.domain.post;

import brunch.domain.member.Member;
import brunch.domain.member.MemberRepository;
import brunch.domain.post.dto.PostRequestDto;
import brunch.domain.post.dto.PostResponseDto;
import brunch.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public Long makePost(PostRequestDto dto, Long userId) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("Can't find this member : " + userId));

        dto.setMember(member);
        Post post = postRepository.save(dto.toEntity());

        return post.getId();
    }

    public void updatePost(PostRequestDto dto, Long postId, Long userId) {
        Post post = this.findPost(postId);

        if (!post.getMember().getId().equals(userId)) {
            throw new IllegalStateException("You do not have permission.");
        }

        post.updateField(dto.getTitle(), dto.getContent());
    }

    public void deletePost(Long postId, Long userId) {
        Post post = this.findPost(postId);

        if (!post.getMember().getId().equals(userId)) {
            throw new IllegalStateException("You do not have permission.");
        }

        postRepository.delete(post); //cascade delete : Comment
    }

    @Transactional(readOnly = true)
    public PostResponseDto findPostDto(Long postId) {
        Post post = this.findPost(postId);
        return new PostResponseDto(post);
    }

    @Transactional(readOnly = true)
    private Post findPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalStateException("Can't find this post." + postId));
    }
}
