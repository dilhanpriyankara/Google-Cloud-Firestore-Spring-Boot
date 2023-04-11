package comfirestorereactive.firestore.google.demo.repository;

import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;

import comfirestorereactive.firestore.google.demo.document.User;
import reactor.core.publisher.Flux;

public interface UserRepository extends FirestoreReactiveRepository<User> {
    Flux<User> findByCountry(String country);
}
