package org.syh.demo.spring.springboot.request;

import org.syh.demo.spring.springboot.dgs.type.client.AddShowGraphQLQuery;
import org.syh.demo.spring.springboot.dgs.type.client.ShowsGraphQLQuery;
import org.syh.demo.spring.springboot.dgs.type.client.ShowsProjectionRoot;
import org.syh.demo.spring.springboot.dgs.type.types.DirectorInput;
import org.syh.demo.spring.springboot.dgs.type.types.CompanyInput;

import com.netflix.graphql.dgs.client.codegen.GraphQLQueryRequest;

public class RequestBuilder {
    public static GraphQLQueryRequest buildShowsRequest(String titleFilter) {
        return new GraphQLQueryRequest(
            ShowsGraphQLQuery
                .newRequest()
                .titleFilter(titleFilter)
                .build(),
            
            new ShowsProjectionRoot()
                .title()
                .year()
                .director()
                .name()
                .age()
                .company()
                .name()
                .country()
        );
    }

    public static GraphQLQueryRequest buildAddShowRequest(
        String title, 
        int year, 
        String directorName, 
        int directorAge, 
        String directorCompanyName, 
        String directorCompanyCountry) {
        return new GraphQLQueryRequest(
            AddShowGraphQLQuery
                .newRequest()
                .title(title)
                .year(year)
                .director(
                    DirectorInput.newBuilder()
                        .name(directorName)
                        .age(directorAge)
                        .company(
                            CompanyInput.newBuilder()
                                .name(directorCompanyName)
                                .country(directorCompanyCountry)
                                .build()
                        
                        )
                        .build()
                )
                .build(),

            
            new ShowsProjectionRoot()
                .title()
                .year()
                .director()
                .name()
                .age()
                .company()
                .name()
                .country()
        );
    }
}
