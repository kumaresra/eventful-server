package io.selvaesra.eventful.repositories;

import io.selvaesra.eventful.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    @Query(value="SELECT e.event_surrogate_id,c.category_surrogate_id,e.venue_surrogate_id,e.event_name,e.short_description,e.from_datetime,e.to_datetime,photo_url,c.category_name ,v.coordinate, v.location, v.venue_name from EVENT e, CATEGORY c, VENUE v where e.category_surrogate_id = c.category_surrogate_id and e.venue_surrogate_id = v.venue_surrogate_id and v.location=:location and c.category_name=:category and DATE_FORMAT(e.from_datetime,'%Y-%m-%d')=:fromDateTime and DATE_FORMAT(e.to_datetime,'%Y-%m-%d')=:toDateTime",nativeQuery=true)
    List<Event> findByLocationAndCategoryAndFromDateTime(@Param("location") String location,
                                                         @Param("category") String category,
                                                         @Param("fromDateTime") String fromDateTime,
                                                         @Param("toDateTime") String toDateTime);
}