package com.pius.spring;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/coffees")
class RestApiDemoController {
    private final CoffeeRepository coffeeRepository;

    public RestApiDemoController(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
        this.coffeeRepository.saveAll(List.of(
                new Coffee("mocha"),
                new Coffee("latte"),
                new Coffee("americano"),
                new Coffee("cappuccino")
        ));
    }

    //	@RequestMapping(value = "/coffees", method = RequestMethod.GET)
    @GetMapping
    Iterable<Coffee> getCoffees() {
        return coffeeRepository.findAll();
    }

    @GetMapping("/{id}")
    Optional<Coffee> getCoffeeById(@PathVariable String id) { // Optional은 null을 반환하지 않고, 객체를 감싸서 반환
        return coffeeRepository.findById(id);
    }

    @PostMapping
    ResponseEntity<Coffee> postCoffee(@RequestBody Coffee coffee) {
        return new ResponseEntity<>(coffeeRepository.save(coffee), HttpStatus.CREATED);
    }

    // PUT 메서드 응답 시 상태 코드는 필수
    @PutMapping("/{id}")
    ResponseEntity<Coffee> putCoffee(@PathVariable String id,
                                     @RequestBody Coffee coffee) {
        // 업데이트하거나 생성된 Coffee 객체만 반환하는 대신, 해당 객체와 적절한 HTTP 상태 코드가 포함된 ResponseEntity를 반환.
        return (coffeeRepository.existsById(id))
                ? new ResponseEntity<>(coffeeRepository.save(coffee), HttpStatus.OK)
                : new ResponseEntity<>(coffeeRepository.save(coffee), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    void deleteCoffee(@PathVariable String id) {
        coffeeRepository.deleteById(id);
    }
}
