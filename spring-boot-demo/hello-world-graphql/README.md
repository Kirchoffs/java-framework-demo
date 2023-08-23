# Notes

## GraphQL
### UI
Go to the http://localhost:8080/graphiql to test the API.

Simple query:
```
query {
  authors {
    id
    name
  }
}
```

```
query {
  authors {
    id
    name
    books {
      title
    }
  }
}
```

```
query {
  authorById(id: 1) {
    id
    name
  }
}
```

It supports multiple queries in one request.
```
query {
  authors {
    id
    name
    books {
      title
    }
  }
  authorById(id: 1) {
    id
    name
  }
}
```

It also supports mutations.
```
mutation {
  addBook(bookInput: {
    title: "Spring Cloud in Action", 
    publisher: "Manning", 
    authorId: 1}) {
    id
    title
  }
}
```