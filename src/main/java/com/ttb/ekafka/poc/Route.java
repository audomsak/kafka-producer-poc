package com.ttb.ekafka.poc;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

@ApplicationScoped
public class Route extends RouteBuilder {
    @Inject
    Counter counter;

    @Override
    public void configure() throws Exception {
        onException(Exception.class)
                .handled(true)
                .logHandled(true)
                .logStackTrace(true)
                .log(LoggingLevel.ERROR, "There was an error occurred. ${exception}");

        from("timer://counter?fixedRate=true&period={{timer.interval}}")
                .routeId("data-generator")
                .process(counter)
                .to("kafka:{{kafka.topic.name}}")
                .log("${body}");
    }
}
