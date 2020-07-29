package com.hyeon.springbootwebservice.dto;

import com.hyeon.springbootwebservice.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc; //웹 API 테스트할 때 사용. HTTP GET, POST 등에 대한 API 테스트

    @Test
    public void hello_리턴() throws Exception{

        String hello = "hello";

        mvc.perform(get("/hello")) // hello 주소로 HTTP Get 요청함.
                .andExpect(status().isOk()) // 200(에러)상태 인지 아닌지 검증
                .andExpect(content().string(hello)); //Controller에서 Hello 리턴하기 때문에 이 값이 맞는지 검증
    }

    @Test
    public void helloDto_리턴() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                .param("name", name)
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
