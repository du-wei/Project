package com.webapp.kafka;

/**
 * Created by king on 2015/11/24.
 */

@FunctionalInterface
public interface TopicKeyVal<T, V> {

    void apply(T topic, V val);

}
