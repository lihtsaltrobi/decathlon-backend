package ee.industry.decathlon.model;

import ee.industry.decathlon.classifier.EventType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
public class EventResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String competitor;
    private EventType eventType;
    private Double score;
    private Integer points;

}
