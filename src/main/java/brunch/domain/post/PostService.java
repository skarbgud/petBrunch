package brunch.domain.post;

import brunch.domain.member.Member;
import brunch.domain.member.MemberRepository;
import brunch.domain.post.dto.PostRequestDto;
import brunch.domain.post.dto.PostResponseDto;
import brunch.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalStateException("Can't find this post." + postId));

        if (!post.getMember().getId().equals(userId)) {
            throw new IllegalStateException("You do not have permission.");
        }

        post.updateField(dto.getTitle(), dto.getContent(), dto.getImage());
    }

    public void uploadImage(Long postId, MultipartFile file) throws Exception {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalStateException("Can't find this post." + postId));

        Byte[] image = new Byte[file.getBytes().length];
        int i = 0;

        for (Byte b : image) {
            image[i++] = b;
        }

        post.updateImage(image);
    }

    public void deletePost(Long postId, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalStateException("Can't find this post." + postId));

        if (!post.getMember().getId().equals(userId)) {
            throw new IllegalStateException("You do not have permission.");
        }

        postRepository.delete(post); //cascade delete : Comment
    }

    @Transactional(readOnly = true)
    public PostResponseDto findPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalStateException("Can't find this post : " + postId));

        return new PostResponseDto(post);
    }
}
