package com.pius.spring;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
class Coffee {
    @Id
    private String id; // cannot be changed once set
    private String name;

    public Coffee() { // 기본 생성자
    }

    public Coffee(String id, String name) { // 첫 번째 생성자
        this.id = id;
        this.name = name;
    }

    public Coffee(String name) { // 두 번째 생성자. id를 입력 받지 않았을 때 자동으로 생성
        this(UUID.randomUUID().toString(), name);
    }

    public void setId(String id) { // 변경자 mutator
        this.id = id;
    }

    public String getId() { // 접근자 accessor
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { // 변경자 mutator
        this.name = name;
    }
}
