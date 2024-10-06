package ru.collection.doll_collection.repository;

import org.springframework.stereotype.Repository;
import ru.collection.doll_collection.entity.Doll;

import java.util.HashMap;

@Repository
public class DollRepositoryImpl implements DollRepository {
    private HashMap<Integer, Doll> dollHashMap = new HashMap<>();

    private Integer id = 1;

    private Integer incId() {
        return id++;
    }

    public Doll saveDoll(Doll doll) {
        dollHashMap.put(incId(), doll);
        return doll;
    }

    public Doll getDoll(Integer id) {
        return dollHashMap.get(id);
    }
}
