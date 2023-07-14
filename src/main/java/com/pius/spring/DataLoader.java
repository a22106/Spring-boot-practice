package com.pius.spring;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class DataLoader { // 데이터 생성 담당
    private final CoffeeRepository coffeeRepository;

    public DataLoader(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    @PostConstruct // 스프링이 애플리케이션 컨텍스트를 생성한 후에 실행되는 메서드
    private void loadData() {
        coffeeRepository.saveAll(List.of(
                new Coffee("mocha"),
                new Coffee("latte"),
                new Coffee("americano"),
                new Coffee("cappuccino")
        ));
    }
}
