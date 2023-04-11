package comfirestorereactive.firestore.google.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import comfirestorereactive.firestore.google.demo.document.User;
import comfirestorereactive.firestore.google.demo.service.UserService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    private Mono<User> save(@RequestBody User user) {

        return userService.save(user);
    }

    @PutMapping("/users/{id}")
    private Mono<ResponseEntity<User>> update(@PathVariable String id, @RequestBody User user) {
        return userService.update(id, user).flatMap(user1 -> Mono.just(ResponseEntity.ok(user1)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));

    }

    @DeleteMapping("/users/{id}")
    private Mono<ResponseEntity<String>> delete(@PathVariable String id) {

        return userService.delete(id).flatMap(user -> Mono.just(ResponseEntity.ok("Deleted Successfully")))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));

    }

    @GetMapping(value = "/users/country/{name}")
    private Flux<User> findAllUsersByCountry(@PathVariable String name) {

        return userService.findByCountry(name);
    }

    @GetMapping(value = "/users")
    private Flux<User> findAll() {

        return userService.findAll();
    }

}
