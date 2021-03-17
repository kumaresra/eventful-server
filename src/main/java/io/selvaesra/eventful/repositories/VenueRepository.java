package io.selvaesra.eventful.repositories;

import io.selvaesra.eventful.entity.Venue;
import org.springframework.data.repository.CrudRepository;

public interface VenueRepository extends CrudRepository<Venue, Integer> {
}