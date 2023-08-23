package org.syh.demo.spring.springboot;

class BookInput {
    private String title;
    private String publisher;
    private Long authorId;

    public BookInput(String title, String publisher, Long authorId) {
        this.title = title;
        this.publisher = publisher;
        this.authorId = authorId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getAuthorId() {
        return authorId;
    }
}