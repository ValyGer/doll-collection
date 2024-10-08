package ru.collection.doll_collection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.collection.doll_collection.entity.Doll;

@Repository
public interface DollRepository extends JpaRepository<Doll, Integer> {
}
