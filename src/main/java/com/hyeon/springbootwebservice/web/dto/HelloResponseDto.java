package com.hyeon.springbootwebservice.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter //선언된 모든 필드에 get 메소드 생성
@RequiredArgsConstructor //선언된 모든 final 필드가 포함된 생성자 생성 (final 필드 없으면 포함 X
public class HelloResponseDto {

    private final String name;
    private final int amount;

}

