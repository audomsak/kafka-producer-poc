package com.ttb.ekafka.poc;

import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.kafka.KafkaConstants;
import org.jboss.logging.Logger;

import java.time.LocalDateTime;

@ApplicationScoped
public class Counter implements Processor {
    private static final Logger LOG = Logger.getLogger(Counter.class);
    public static int COUNTER = 1;

    @Override
    public void process(Exchange exchange) throws Exception {
        String body = COUNTER + " - " + LocalDateTime.now();
        //body = LocalDateTime.now().toString();
        exchange.getIn().setHeader(KafkaConstants.KEY, COUNTER);
        exchange.getIn().setBody(body);
        COUNTER++;
        //LOG.info(body);
    }
}
