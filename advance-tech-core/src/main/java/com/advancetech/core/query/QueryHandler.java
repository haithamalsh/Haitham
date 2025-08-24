package com.advancetech.core.query;

/**
 * Base interface for query handlers
 * 
 * @param <T> The type of the query
 * @param <R> The type of the result
 */
public interface QueryHandler<T extends Query<R>, R> {
    
    /**
     * Handle the query
     * 
     * @param query The query to handle
     * @return The result of handling the query
     */
    R handle(T query);
    
    /**
     * Get the query type this handler can handle
     * 
     * @return The query class
     */
    Class<T> getQueryType();
}
