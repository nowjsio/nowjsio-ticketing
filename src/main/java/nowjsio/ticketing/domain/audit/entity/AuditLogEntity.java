package nowjsio.ticketing.domain.audit.entity;

import jakarta.persistence.*;
import lombok.*;
import nowjsio.ticketing.domain.common.entity.BaseEntity;

@Entity
@Table(name = "audit_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditLogEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 20)
	@Enumerated(EnumType.STRING)
	private ActionType action;

	@Column(name = "entity_name", nullable = false, length = 100)
	private String entityName;

	@Column(name = "entity_id", length = 50)
	private String entityId;

	@Column(columnDefinition = "TEXT")
	private String previousData;

	@Column(columnDefinition = "TEXT")
	private String newData;

	@Column(name = "user_id")
	private Long userId;

	@Column(length = 45)
	private String ipAddress;

	@Column(length = 255)
	private String userAgent;

	@Column(length = 255)
	private String description;

	// BaseEntity에서 createdAt 자동 상속
}