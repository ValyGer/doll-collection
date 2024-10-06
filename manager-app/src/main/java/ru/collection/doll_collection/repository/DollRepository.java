package ru.collection.doll_collection.repository;

import ru.collection.doll_collection.entity.Doll;

public interface DollRepository {

    Doll saveDoll(Doll doll);

    Doll getDoll(Integer id);
}
