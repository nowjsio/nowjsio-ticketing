package nowjsio.ticketing.domain.booking.seat.entity;

import jakarta.persistence.*;
import lombok.*;
import nowjsio.ticketing.domain.booking.section.entity.SectionEntity;
import nowjsio.ticketing.domain.booking.ticket.entity.TicketEntity;
import nowjsio.ticketing.domain.common.entity.BaseEntity;

import java.util.List;

@Entity
@Table(name = "seats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "section_id", nullable = false)
	private SectionEntity section;

	@Column(name = "row_label", length = 5, nullable = false)
	private String rowLabel;

	@Column(nullable = false)
	private int num;

	@OneToMany(mappedBy = "seat", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TicketEntity> tickets;
}