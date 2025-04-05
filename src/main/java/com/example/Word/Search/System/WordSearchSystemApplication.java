package com.example.Word.Search.System;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class WordSearchSystemApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(WordSearchSystemApplication.class, args);
        WordSearchService service = context.getBean(WordSearchService.class);
        service.insert("hello");
        service.insert("hero");
        System.out.println("Words inserted: hello, hero");
	}

}
