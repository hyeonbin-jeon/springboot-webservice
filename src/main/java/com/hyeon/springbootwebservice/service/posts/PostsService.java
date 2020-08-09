package com.hyeon.springbootwebservice.service.posts;


import com.hyeon.springbootwebservice.domain.posts.Posts;
import com.hyeon.springbootwebservice.domain.posts.PostsRepository;
import com.hyeon.springbootwebservice.web.dto.PostsListResponseDto;
import com.hyeon.springbootwebservice.web.dto.PostsResponseDto;
import com.hyeon.springbootwebservice.web.dto.PostsSaveRequestDto;
import com.hyeon.springbootwebservice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }
//더티 체킹으로 생성되는 모든 update문의 쿼리는 기본적으로 모든 필드를 업데이트 함
    //부분적인 필드만 update치고싶으면 @DynamicUpdate로 변경된 필드만 반열할 수도 있음
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 게시글이 없습니다. id= " + id));
        return new PostsResponseDto(entity);
    }

    @Transactional
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
