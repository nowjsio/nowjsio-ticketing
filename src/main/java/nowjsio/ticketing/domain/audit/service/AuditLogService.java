package nowjsio.ticketing.domain.audit.service;

import java.util.List;

import nowjsio.ticketing.domain.audit.dto.AuditLogDto;
import nowjsio.ticketing.domain.audit.entity.ActionType;

public interface AuditLogService {
	void recordNewTx(
		ActionType action,
		String entityName,
		String entityId,
		String previousData,
		String newData,
		String userName,
		String ip,
		String userAgent,
		String description
	);

	List<AuditLogDto> findAllLogs();
}
