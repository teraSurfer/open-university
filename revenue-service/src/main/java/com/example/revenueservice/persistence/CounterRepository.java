package com.example.revenueservice.persistence;

import com.example.revenueservice.entities.Counter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterRepository extends JpaRepository<Counter, Long> {
    Counter getCountersByName(String name);
}
