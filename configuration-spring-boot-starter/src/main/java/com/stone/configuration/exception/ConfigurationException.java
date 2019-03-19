package com.stone.configuration.exception;

/**
 * @author honorstone
 * @date 2019/3/18 22:24
 */
public class ConfigurationException extends RuntimeException{
    private static final long serialVersionUID = -3376130012906197352L;


    public ConfigurationException() {
    }

    public ConfigurationException(String message) {
        super(message);
    }

    public ConfigurationException(Throwable cause) {
        super(cause);
    }

    public ConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}
