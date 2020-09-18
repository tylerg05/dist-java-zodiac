package edu.wctc.distjavazodiac.repo;

import edu.wctc.distjavazodiac.entity.Fortune;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FortuneRepository extends CrudRepository<Fortune, Integer> {
    List<Fortune> findAllById(int id);
    Fortune findById(int id);
}
