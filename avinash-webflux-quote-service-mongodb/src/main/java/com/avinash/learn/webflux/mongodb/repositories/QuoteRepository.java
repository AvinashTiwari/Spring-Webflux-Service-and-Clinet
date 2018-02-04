package com.avinash.learn.webflux.mongodb.repositories;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.avinash.learn.webflux.mongodb.domain.Quote;




import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;



public interface QuoteRepository extends ReactiveMongoRepository<Quote, String> {

    @Tailable
    Flux<Quote> findWithTailableCursorBy(); //must be capped collection
}