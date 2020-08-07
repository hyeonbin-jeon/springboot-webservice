package com.hyeon.springbootwebservice.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
// 인터페이스 작성 후 JpaRepository를 상속하면 기본적인 CRUD 메소드가 자동생성
// MyBatis에서는 Dao라 불리는 DB Layer 접근자
//JpaREpository<Entity 클래스, PK타입>
public interface PostsRepository extends JpaRepository<Posts, Long> {
    @Query("select p from Posts p order by p.id desc")
    List<Posts> findAllDesc();
}
