package com.example.define;

/**
 * Command
 *
 * @author huzunrong
 * @since 1.1.1
 */
public interface Command<T> {

    T run();

    T fallback();
}