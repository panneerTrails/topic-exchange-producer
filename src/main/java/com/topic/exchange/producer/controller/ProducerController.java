package com.topic.exchange.producer.controller;

import com.topic.exchange.producer.delegate.ProducerDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/AMQPProducer")
public class ProducerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerController.class);

    ProducerDelegate producerDelegate;

    public ProducerController(ProducerDelegate producerDelegate) {
        this.producerDelegate = producerDelegate;
    }

    @PostMapping(value = "/sendEvent")
    public ResponseEntity sendMessage(@RequestBody String msg) {
        LOGGER.info("Inside ProductController::sendMessage");
        if (producerDelegate.sendMessage(msg)) {
            return new ResponseEntity(msg, HttpStatus.OK);
        } else {
            return new ResponseEntity(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}
