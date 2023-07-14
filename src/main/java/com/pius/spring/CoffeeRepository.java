package com.pius.spring;

import org.springframework.data.repository.CrudRepository;

interface CoffeeRepository extends CrudRepository<Coffee, String> { // Coffee 객체를 저장하고 조회하는 인터페이스
}
