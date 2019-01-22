package com.keyonelabs.howdyconfigdemo;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@EnableAutoConfiguration
public class HowdyController {

    @Value("${howdy.from}")
    private String from;

    @Value("${howdy.hostname}")
    private String hostname;

    Logger logger = LoggerFactory.getLogger(HowdyController.class);

    @GetMapping("/")
    public String howdy() {
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            logger.warn("Cannot get hostname using default " + hostname);
        }
        return String.format("Howdy from %s in %s", from, hostname);
    }
}