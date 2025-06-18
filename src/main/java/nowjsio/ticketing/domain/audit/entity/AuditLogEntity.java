// package nowjsio.ticketing.domain.audit.entity;
//
// import java.time.LocalDateTime;
//
// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Table;
// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;
//
// @Entity
// @Getter
// @Setter
// @Builder
// @AllArgsConstructor
// @NoArgsConstructor
// @Table(name = "audit_logs")
// public class AuditLogEntity {
//
// 	@Id
// 	@GeneratedValue(strategy = GenerationType.IDENTITY)
// 	private Long id;
//
// 	@Column(nullable = false)
// 	private String action;
//
// 	@Column(nullable = false)
// 	private String details;
//
// 	@Column(nullable = false)
// 	private Long userId;
//
// 	@Column(nullable = false)
// 	private LocalDateTime timestamp;
// }