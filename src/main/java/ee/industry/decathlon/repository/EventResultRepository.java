package ee.industry.decathlon.repository;

import ee.industry.decathlon.model.EventResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventResultRepository extends JpaRepository<EventResult, Long> {

    @Query("select distinct e.competitor from EventResult e where lower(e.competitor) like %:competitor% order by e.competitor")
    List<String> getCompetitors(String competitor);
}
