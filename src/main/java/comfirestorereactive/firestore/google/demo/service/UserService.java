package comfirestorereactive.firestore.google.demo.service;

import comfirestorereactive.firestore.google.demo.document.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<User> save(User user);

    Mono<User> update(String id, User user);

    Mono<User> delete(String id);

    Flux<User> findByCountry(String country);

    Flux<User> findAll();
}
