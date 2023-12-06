# Notes

## GraphQL
### Query and Mutation
Query: This type is used for read-only fetch operations. When you define your schema, you list all the available queries under the Query type. These queries can fetch data but do not modify it. For example, if you have a blog application, your Query type might include operations to fetch posts, comments, or user profiles.

Mutation: This type is used for write operations that modify data. Just like with Query, you define available mutations under the Mutation type in your schema. Mutations are used for operations like adding, updating, or deleting data. In the blog application example, mutations might include creating a new post, updating a user profile, or deleting a comment.

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

### Request
Send request to localhost:8080/graphql with the following body:
```
query {
  authorById(id: 1) {
    id
    name
    books {
      title
    }
  }
}
```

Another parametric way:
```
query($id: ID!) {
  authorById(id: $id) {
    id
    name
    books {
      title
    }
  }
}
```

And the variables:
```
{
  "id": 1
}
```
