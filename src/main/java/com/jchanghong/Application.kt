package com.jchanghong

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableScheduling

/**
 * Application.java

 * @desc 不积跬步无以至千里, 不积小流无以至千里
 * *
 * @author:EumJi
 * *
 * @year: 2017
 * *
 * @month: 04
 * *
 * @day: 02
 * *
 * @time: 2017/4/2
 */
@SpringBootApplication
@EnableScheduling
class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}