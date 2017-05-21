package com.eumji.zblog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
    @Value("${spring.datasource.password}")
    String url;
    @Test
    public void main() throws Exception {
        System.out.println(url);
    }

}