package nowjsio.ticketing.domain.booking.section.entity;

import jakarta.persistence.*;
import lombok.*;
import nowjsio.ticketing.domain.booking.pricing.entity.SectionPriceEntity;
import nowjsio.ticketing.domain.booking.seat.entity.SeatEntity;
import nowjsio.ticketing.domain.common.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "sections")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SectionEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String name;

	@Column(nullable = false, precision = 4, scale = 2)
	private BigDecimal multiplier;

	@OneToMany(mappedBy = "section", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SeatEntity> seats;

	@OneToMany(mappedBy = "section", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SectionPriceEntity> sectionPrices;
}