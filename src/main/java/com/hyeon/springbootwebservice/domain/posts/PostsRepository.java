package com.hyeon.springbootwebservice.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
}
// 인터페이스 작성 후 JpaRepository를 상속하면 기본적인 CRUD 메소드가 자동생성