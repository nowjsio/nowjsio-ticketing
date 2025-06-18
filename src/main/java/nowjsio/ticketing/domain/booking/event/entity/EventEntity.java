package nowjsio.ticketing.domain.booking.event.entity;

import jakarta.persistence.*;
import lombok.*;
import nowjsio.ticketing.domain.booking.schedule.entity.ScheduleEntity;
import nowjsio.ticketing.domain.common.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(columnDefinition = "TEXT")
	private String description;

	@Column(name = "base_price", nullable = false, precision = 10, scale = 2)
	private BigDecimal basePrice;

	@OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ScheduleEntity> schedules;
}
