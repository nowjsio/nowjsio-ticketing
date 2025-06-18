package nowjsio.ticketing.domain.booking.schedule.entity;

import jakarta.persistence.*;
import lombok.*;
import nowjsio.ticketing.domain.booking.event.entity.EventEntity;
import nowjsio.ticketing.domain.booking.ticket.entity.TicketEntity;
import nowjsio.ticketing.domain.common.entity.BaseEntity;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "event_schedules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id", nullable = false)
	private EventEntity event;

	@Column(name = "starts_at", nullable = false)
	private LocalDateTime startsAt;

	@OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TicketEntity> tickets;
}