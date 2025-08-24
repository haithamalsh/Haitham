package com.advancetech.core.command;

/**
 * Base interface for command handlers
 * 
 * @param <T> The type of the command
 * @param <R> The type of the result
 */
public interface CommandHandler<T extends Command<R>, R> {
    
    /**
     * Handle the command
     * 
     * @param command The command to handle
     * @return The result of handling the command
     */
    R handle(T command);
    
    /**
     * Get the command type this handler can handle
     * 
     * @return The command class
     */
    Class<T> getCommandType();
}
