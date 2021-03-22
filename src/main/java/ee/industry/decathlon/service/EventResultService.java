package ee.industry.decathlon.service;

import ee.industry.decathlon.dto.EventResultDTO;

import java.util.List;

public interface EventResultService {

    EventResultDTO create(EventResultDTO dto);
    List<EventResultDTO> getAll();
    List<String> getCompetitors(String filter);

}
