package nowjsio.ticketing.domain.audit.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import nowjsio.ticketing.domain.audit.dto.AuditLogDto;
import nowjsio.ticketing.domain.audit.entity.AuditLogEntity;
import nowjsio.ticketing.domain.audit.entity.ActionType;
import nowjsio.ticketing.domain.audit.repository.AuditLogRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuditLogServiceImpl implements AuditLogService {
	private final AuditLogRepository auditLogRepository;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void recordNewTx(ActionType action,
		String entityName,
		String entityId,
		String previousData,
		String newData,
		Long userId,
		String ip,
		String userAgent,
		String description) {
		AuditLogEntity log = AuditLogEntity.builder()
			.action(action)
			.entityName(entityName)
			.entityId(entityId)
			.previousData(previousData)
			.newData(newData)
			.userId(userId)
			.ipAddress(ip)
			.userAgent(userAgent)
			.description(description)
			.build();
		auditLogRepository.save(log);
	}

	@Override
	public List<AuditLogDto> findAllLogs() {
		return auditLogRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"))
			.stream()
			.map(AuditLogDto::fromEntity)
			.collect(Collectors.toList());
	}
}
