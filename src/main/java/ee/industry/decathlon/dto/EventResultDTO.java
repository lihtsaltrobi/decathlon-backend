package ee.industry.decathlon.dto;

import ee.industry.decathlon.classifier.EventType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class EventResultDTO {
    private Long id;
    @NotEmpty
    private String competitor;
    @Min(0)
    private double score;
    @NotNull
    private EventType eventType;
    private Integer points;
}
