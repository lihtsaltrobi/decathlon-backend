package ee.industry.decathlon.controller;

import ee.industry.decathlon.dto.EventResultDTO;
import ee.industry.decathlon.service.EventResultService;
import ee.industry.decathlon.service.PointService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/event")
public class EventResultController {

    private final EventResultService eventResultService;

    public EventResultController(EventResultService eventResultService) {
        this.eventResultService = eventResultService;
    }

    @PostMapping
    public EventResultDTO saveResults(@Valid @RequestBody EventResultDTO dto) {
        return eventResultService.create(dto);
    }

    @GetMapping
    public List<EventResultDTO> getAllResults() {
        return eventResultService.getAll();
    }

    @GetMapping("/competitors")
    public List<String> getCompetitors(@RequestParam String competitor) {
        return eventResultService.getCompetitors(competitor);
    }

}
