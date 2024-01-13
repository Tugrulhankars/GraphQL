package com.metropol.grapql.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GraphqlException extends DataFetcherExceptionResolverAdapter {


    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        if (ex instanceof UserNotFoundException) {
            return toGraphqlError(ex);
        } else if (ex instanceof Exception) {
            return toGraphqlError(ex);
        }
        return super.resolveToSingleError(ex, env);
    }

    private GraphQLError toGraphqlError(Throwable throwable) {
        return GraphqlErrorBuilder.newError()
                .message(throwable.getMessage())
                .errorType(ErrorType.DataFetchingException)
                .build();
    }
}
