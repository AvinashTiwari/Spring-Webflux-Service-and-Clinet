package com.avinash.learn.webflux.mongodb.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.avinash.learn.webflux.mongodb.domain.Quote;
import com.avinash.learn.webflux.mongodb.repositories.QuoteRepository;

import reactor.core.publisher.Flux;
import reactor.core.Disposable;


@Component
public class QuoteRunner implements CommandLineRunner {

	private final StockQuoteClient stockQuoteClient;
    private final QuoteRepository repository;

    public QuoteRunner(StockQuoteClient stockQuoteClient, QuoteRepository repository) {
        this.stockQuoteClient = stockQuoteClient;
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {

        Flux<Quote> quoteFlux = repository.findWithTailableCursorBy();

        Disposable disposable = quoteFlux.subscribe(quote -> {
            System.out.println("*#*#*#*#*#*#*#*#*#*#*#*#* Id: " + quote.getId());
        });

        disposable.dispose();
    }
}