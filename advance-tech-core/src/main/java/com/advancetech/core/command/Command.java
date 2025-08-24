package com.advancetech.core.command;

/**
 * Base interface for all commands in the system
 * 
 * @param <T> The type of the command result
 */
public interface Command<T> {
    
    /**
     * Execute the command
     * 
     * @return The result of the command execution
     */
    T execute();
}
