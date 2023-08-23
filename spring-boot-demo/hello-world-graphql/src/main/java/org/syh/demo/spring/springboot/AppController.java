package org.syh.demo.spring.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;

@Controller
public class AppController {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AppController(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @QueryMapping
    public Iterable<Author> authors() {
        return authorRepository.findAll();
    }

    @QueryMapping
    public Author authorById(@Argument Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @MutationMapping
    public Book addBook(@Argument BookInput bookInput) throws Exception {
        Author author = authorRepository
            .findById(bookInput.getAuthorId())
            .orElseThrow(() -> new IllegalArgumentException("Author not found"));

        Book book = new Book(
            bookInput.getTitle(), 
            bookInput.getPublisher(), 
            author
        );

        return bookRepository.save(book);
    }
}
