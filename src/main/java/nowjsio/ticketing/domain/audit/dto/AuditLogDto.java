package nowjsio.ticketing.domain.audit.dto;

import java.time.LocalDateTime;

import lombok.*;
import nowjsio.ticketing.domain.audit.entity.AuditLogEntity;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class AuditLogDto {
	private Long id;
	private String action;
	private String entityName;
	private String entityId;
	private String previousData;
	private String newData;
	private String userName;
	private String ipAddress;
	private String userAgent;
	private String description;
	private LocalDateTime createdAt;

	public static AuditLogDto fromEntity(AuditLogEntity e) {
		return AuditLogDto.builder()
			.id(e.getId())
			.action(e.getAction().name())
			.entityName(e.getEntityName())
			.entityId(e.getEntityId())
			.previousData(e.getPreviousData())
			.newData(e.getNewData())
			.userName(e.getUserName())
			.ipAddress(e.getIpAddress())
			.userAgent(e.getUserAgent())
			.description(e.getDescription())
			.createdAt(e.getCreatedAt())
			.build();
	}
}
