package org.syh.demo.spring.springboot;

import java.util.Arrays;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner(final AuthorRepository authorRepository, final BookRepository bookRepository) {
        return args -> {
            Author alfred = authorRepository.save(new Author("Alfred V. Aho"));
            Author andrew = authorRepository.save(new Author("Andrew S. Tanenbaum"));

            bookRepository.saveAll(
                Arrays.asList(
                    new Book("Compilers: Principles, Techniques, and Tools", "CS", alfred),
                    new Book("Principles of Compiler Design", "CS", alfred),
                    new Book("Modern Operating Systems", "CS", andrew),
                    new Book("Distributed Systems: Principles and Paradigms", "CS", andrew)
                )
            );
        };
    }
}
