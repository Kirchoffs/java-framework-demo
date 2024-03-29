package org.syh.demo.spring.springboot.dgs.server;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Component;

import com.netflix.graphql.types.errors.TypedGraphQLError;

import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.execution.DataFetcherExceptionHandlerResult;
import graphql.GraphQLError;

@Component
public class ShowsDataFetchingExceptionHandler implements DataFetcherExceptionHandler {
    @Override
    public CompletableFuture<DataFetcherExceptionHandlerResult> handleException(
            DataFetcherExceptionHandlerParameters handlerParameters) {
        Throwable exception = handlerParameters.getException();
        if (exception instanceof InvalidInputException) {
            Map<String, Object> debugInfo = new HashMap<>();
            debugInfo.put("exception", exception.getMessage());

            GraphQLError graphQLError = TypedGraphQLError.newInternalErrorBuilder()
                .message("Invalid input")
                .debugInfo(debugInfo)
                .path(handlerParameters.getPath())
                .build();

            DataFetcherExceptionHandlerResult result = DataFetcherExceptionHandlerResult.newResult()
                .error(graphQLError)
                .build();
            
            return CompletableFuture.completedFuture(result);
        } else {
            GraphQLError graphQLError = TypedGraphQLError.newInternalErrorBuilder()
                .message("An unexpected error occurred")
                .path(handlerParameters.getPath())
                .build();

            DataFetcherExceptionHandlerResult result = DataFetcherExceptionHandlerResult.newResult()
                .error(graphQLError)
                .build();

            return CompletableFuture.completedFuture(result);
        }
    }
}
