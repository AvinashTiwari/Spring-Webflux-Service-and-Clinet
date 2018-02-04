package com.avinash.learn.webflux.mongodb.client;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.avinash.learn.webflux.mongodb.domain.Quote;

import reactor.core.publisher.Flux;

/**
 * Created by jt on 10/19/17.
 */
@Slf4j
@Setter
@Component
@ConfigurationProperties("avinash")
public class StockQuoteClient {

    private String host;
    private String port;
    private String path;

    public Flux<Quote> getQuoteStream(){

        String url = "http://" + host + ":"+ port;

        log.debug("Url Set is: " + url);

        return WebClient.builder()
                .baseUrl(url)
                .build()
                .get()
                .uri(path)
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve()
                .bodyToFlux(Quote.class);
    }
}