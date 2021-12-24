package com.darkchoco.f1.springboot.domain.posts;

import com.darkchoco.f1.domain.posts.Posts;
import com.darkchoco.f1.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    PostsRepository postsRepositoory;

    @After
    public void cleanup(){
        postsRepositoory.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        //given
        String title = "테스트 게시글";
        String content ="테스트 본문";

        postsRepositoory.save(Posts.builder()
                        .title(title)
                        .content(content)
                        .author("darkchoco@gmail.com")
                        .build());

        //when
        List<Posts> postsList = postsRepositoory.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
    }

}
