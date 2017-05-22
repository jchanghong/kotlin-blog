package com.jchanghong

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

import org.junit.Assert.*

@RunWith(SpringRunner::class)
@SpringBootTest
class ApplicationTest {
    @Value("\${spring.datasource.password}")
    internal var url: String? = null

    @Test
    @Throws(Exception::class)
    fun main() {
      assertEquals(url,"0000")
    }

}