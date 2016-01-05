package com.webapp.kafka;

/**
 * Created by king on 2015/11/24.
 */

@FunctionalInterface
public interface TopicRecord<R> {

    void apply(R record);

}
