package brunch.domain.post.repository;

import brunch.domain.post.dto.PostQueryDto;
import brunch.domain.post.dto.PostResponseDto;

import java.util.List;

public interface PostRepositoryCustom {

    PostResponseDto readPost(Long postId); //포스트 읽기
    List<PostQueryDto> searchByAccuracyOrder(String keyword); //정확도순 정렬
    List<PostQueryDto> searchByDateOrder(String keyword); //최신순 정렬
}
