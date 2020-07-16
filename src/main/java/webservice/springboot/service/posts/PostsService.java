package webservice.springboot.service.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import webservice.springboot.domain.posts.Posts;
import webservice.springboot.domain.posts.PostsRepository;
import webservice.springboot.web.dto.PostsResponseDto;
import webservice.springboot.web.dto.PostsSaveRequestDto;
import webservice.springboot.web.dto.PostsUpdateRequestDto;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service

public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }
}
