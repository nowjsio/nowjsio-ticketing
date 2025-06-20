package nowjsio.ticketing.domain.audit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nowjsio.ticketing.domain.audit.entity.AuditLogEntity;

public interface AuditLogRepository extends JpaRepository<AuditLogEntity, Long> {
}