package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);

    private Map<Integer, User> repository = new ConcurrentHashMap<>();



    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        return repository.remove(id) != null;
    }

    @Override
    public User save(User user) {
        log.info("save {}", user);
        if (!repository.containsKey(user.getId())) return null;
        else {
            repository.put(user.getId(), user);
        }

        return user;
    }

    @Override
    public User get(int id) {
        log.info("get {}", id);
        return repository.getOrDefault(id, null);

    }

    @Override
    public List<User> getAll() {
        log.info("getAll");

       List<User> result = new ArrayList<>( repository.values());
       result.sort(new Comparator<User>() {
           @Override
           public int compare(User o1, User o2) {
               return o1.getName().compareTo(o2.getName());
           }
       });
       return result;


    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        if ( repository.values().stream().anyMatch(user -> user.getEmail().equals(email)) ) {
            return  repository.values().stream().filter(user -> user.getEmail().equals(email)).findFirst().get();
        }
        else return null;

    }
}
