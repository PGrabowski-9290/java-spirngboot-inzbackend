package com.paweu.inzappbackend.db;

import com.paweu.inzappbackend.db.models.Settings;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ISettingsRepository extends ReactiveMongoRepository<Settings, String> {
    Mono<Settings> findFirstById(int id);
}
