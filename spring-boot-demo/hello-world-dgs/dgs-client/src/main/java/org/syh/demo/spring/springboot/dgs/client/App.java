package org.syh.demo.spring.springboot.dgs.client;

import org.springframework.web.client.RestClient;
import org.syh.demo.spring.springboot.dgs.client.request.RequestBuilder;

import com.netflix.graphql.dgs.client.codegen.GraphQLQueryRequest;

import java.util.Map;

public class App {
    public static void main(String[] args) {
        String url = "http://localhost:8080/graphql";
        RestClient client = RestClient.builder().build();

        GraphQLQueryRequest mutationRequestContent = RequestBuilder.buildAddShowRequest(
            "ShowB", 2010, "DirectorB", 42, "CompanyB", "US"
        );
        Map<String, String> mutationRequest = Map.of("query", mutationRequestContent.serialize());
        String mutationResponse = client.post().uri(url).body(mutationRequest).retrieve().body(String.class);
        System.out.println(mutationResponse);

        GraphQLQueryRequest queryRequestContent = RequestBuilder.buildShowsRequest("Show");
        Map<String, String> queryRequest = Map.of("query", queryRequestContent.serialize());
        String queryResponse = client.post().uri(url).body(queryRequest).retrieve().body(String.class);
        System.out.println(queryResponse);
    }
}
