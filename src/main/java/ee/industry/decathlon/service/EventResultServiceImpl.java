package ee.industry.decathlon.service;

import ee.industry.decathlon.dto.EventResultDTO;
import ee.industry.decathlon.model.EventResult;
import ee.industry.decathlon.repository.EventResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventResultServiceImpl implements EventResultService {

    private final EventResultRepository eventResultRepository;
    private final PointService pointService;

    public EventResultServiceImpl(EventResultRepository eventResultRepository, PointService pointService) {
        this.eventResultRepository = eventResultRepository;
        this.pointService = pointService;
    }

    @Override
    public EventResultDTO create(EventResultDTO dto) {
        int points = pointService.calculateEventPoints(dto.getEventType(), dto.getScore());
        dto.setPoints(points);
        EventResult entity = eventResultRepository.save(buildEntity(dto));
        return buildDto(entity);
    }

    @Override
    public List<EventResultDTO> getAll() {
        return eventResultRepository.findAll().stream().map(this::buildDto).collect(Collectors.toList());
    }

    @Override
    public List<String> getCompetitors(String filter) {
        return eventResultRepository.getCompetitors(filter.toLowerCase());
    }

    private EventResult buildEntity(EventResultDTO dto) {
        return EventResult.builder()
                .eventType(dto.getEventType())
                .competitor(dto.getCompetitor())
                .points(dto.getPoints())
                .score(dto.getScore())
                .build();

    }

    private EventResultDTO buildDto(EventResult entity) {
        return EventResultDTO.builder()
                .id(entity.getId())
                .eventType(entity.getEventType())
                .competitor(entity.getCompetitor())
                .points(entity.getPoints())
                .score(entity.getScore())
                .build();
    }
}
