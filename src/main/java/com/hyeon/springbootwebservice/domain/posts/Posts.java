package com.hyeon.springbootwebservice.domain.posts;

import com.hyeon.springbootwebservice.domain.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter  // 룸복 (Getter 메소드 자동생성)
@NoArgsConstructor  // 룸복 (기본 생성자 추가)
@Entity  //JPA 어노테이션 (테이블과 링크될 클래스임을 나타냄)
//@DynamicUpdate 이거 붙이면 5개 업데이터 쳐주던거 update메소드에 있는거만 업데이트 쳐주기는 하는데 modified_date도 쳐주는데 왜인지 확인해보기

public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //GenerationType.IDENTITY : auto_increment
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder //해당 클래스의 빌더 패턴 클래스를 생성, 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}