package com.advancetech.core.query;

/**
 * Base interface for all queries in the system
 * 
 * @param <T> The type of the query result
 */
public interface Query<T> {
    
    /**
     * Get the query identifier
     * 
     * @return The query identifier
     */
    String getQueryId();
}
