package comfirestorereactive.firestore.google.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comfirestorereactive.firestore.google.demo.document.User;
import comfirestorereactive.firestore.google.demo.repository.UserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public Mono<User> save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Mono<User> update(String id, User user) {
        return this.userRepository.findById(id).flatMap(u -> {
            u.setId(id);
            u.setEmail(user.getEmail());
            u.setName(user.getName());
            u.setCountry(user.getCountry());
            return save(u);
        }).switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<User> delete(String id) {
        return this.userRepository.findById(id).flatMap(p -> this.userRepository.deleteById(p.getId()).thenReturn(p));
    }

    @Override
    public Flux<User> findByCountry(String country) {
        return userRepository.findByCountry(country);
    }

    @Override
    public Flux<User> findAll() {
        return userRepository.findAll();
    }

}
