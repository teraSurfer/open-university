package com.example.revenueservice.services;

import com.example.revenueservice.dto.Actor;
import com.example.revenueservice.entities.Counter;
import com.example.revenueservice.persistence.CounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CounterService {

    @Autowired
    CounterRepository repository;

    @Value("${app.counters.faculty-spending}")
    private String FACULTY_SPENDING;

    @Value("${app.counters.student-fees}")
    private String STUDENT_FEES;

    public Counter getCounterByName(String name) {
        return repository.getCountersByName(name);
    }

    public Counter createCounter(Counter counter) {
        return repository.save(counter);
    }

    public Counter updateCounter(Actor actor) {
        switch (actor.getActorType()) {
            case FACULTY:
                return updateCounterByName(FACULTY_SPENDING, actor.getSalary());
            case STUDENT:
                return updateCounterByName(STUDENT_FEES, actor.getFees());
            default:
                throw new RuntimeException("NO SUCH ACTOR TYPE");
        }
    }

    private Counter updateCounterByName(String name, int value) {
        Counter counter = repository.getCountersByName(name);
        counter.setValue(counter.getValue() + value);
        return repository.save(counter);
    }

}
