package io.selvaesra.eventful.repositories;

import io.selvaesra.eventful.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
}