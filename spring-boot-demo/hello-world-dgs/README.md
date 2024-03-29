# Notes

## Query
### Single Query
```
query {
    allShows: shows {
        title
        year
        director {
            name
            age
            company {
                country
            }
        }
    }
    showsWithFilter: shows(titleFilter: "Y") {
        title
        year
    }
}
```

### Bulk Query
```
query {
    queryForX: shows(titleFilter: "X") {
        title
        year
    }
    queryForY: shows(titleFilter: "Y") {
        title
        year
    }
    queryForZ: shows(titleFilter: "Z") {
        title
        year
    }
}
```

## Mutation
```
mutation($title: String!, $year: Int!, $director: DirectorInput) {
    addShow(title: $title, year: $year, director: $director) {
        title
        year
        director {
            name
            company {
                name
                country
            }
        }
    }
}
```

```
{
    "title": "ShowA",
    "year": 1995,
    "director": {
        "name": "directorA",
        "age": 58,
        "company": {
            "name": "CompanyX",
            "country": "CN"
        }
    }
}
```

## Metadata
### How to get the metadata
#### Get all the queries
```
query {
    __schema {
        queryType {
            name
            fields {
                name
                type {
                name
                    kind
                    ofType {
                        name
                        kind
                    }
                }
                args {
                    name
                    type {
                        name
                        kind
                        ofType {
                            name
                            kind
                        }
                    }
                }
            }
        }
    }
}
```

#### Get all the mutations
```
query {
    __schema {
        mutationType {
            name
            fields {
                name
                type {
                    name
                    kind
                    ofType {
                        name
                        kind
                    }
                }
                args {
                    name
                    type {
                        name
                        kind
                        ofType {
                            name
                            kind
                        }
                    }
                }
            }
        }
    }
}
```

#### Get all the types
```
{
    __schema {
        types {
            name
        }
    }
}
```

#### Check sub-fields of type `CompanyInput`:
```
{
    __type(name: "CompanyInput") {
        inputFields {
            name
            type {
                name
            }
        }
    }
}
```

#### Check sub-fields of type `Company`:
```
{
    __type(name: "Company") {
        fields {
            name
            type {
                name
            }
        }
    }
}
```

#### Get all the queries with more details about args and return type
```
{
    __type(name: "Query") {
        fields {
            name
            args {
                name
                type {
                    name
                    ofType {
                        name
                    }
                }
            }
            type {
                name
                ofType {
                    name
                    fields {
                        name
                        type {
                            name
                            ofType {
                                name
                            }
                        }
                    }
                }
            }
        }
    }
}
```
