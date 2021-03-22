package ee.industry.decathlon.controller;

import ee.industry.decathlon.dto.EventResultDTO;
import ee.industry.decathlon.service.PointService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/points")
public class PointController {

    private final PointService pointService;

    public PointController(PointService pointService) {
        this.pointService = pointService;
    }

    @GetMapping
    public int calculatePoints(EventResultDTO dto) {
        return pointService.calculateEventPoints(dto.getEventType(), dto.getScore());
    }

}
