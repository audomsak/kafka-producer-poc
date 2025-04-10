package com.ttb.ekafka.poc;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.builder.RouteBuilder;

@ApplicationScoped
public class Route extends RouteBuilder {
    @Inject
    Counter counter;

    @Override
    public void configure() throws Exception {
        from("timer://counter?fixedRate=true&period=1000")
                .routeId("data-generator")
                .process(counter)
                .to("kafka:{{kafka.topic.name}}")
                .log("${body}");
    }
}
