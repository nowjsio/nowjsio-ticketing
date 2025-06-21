package nowjsio.ticketing.domain.audit.config;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import nowjsio.ticketing.domain.audit.entity.ActionType;
import nowjsio.ticketing.domain.audit.service.AuditLogService;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Aspect
@Component
@RequiredArgsConstructor
public class AuditAspect {

	private final AuditLogService auditLogService;
	private final ObjectMapper objectMapper;

	@Pointcut("@annotation(auditable)")
	public void auditablePointcut(Auditable auditable) {}

	@AfterReturning(
		pointcut  = "auditablePointcut(auditable)",
		returning = "retVal",
		argNames = "jp,auditable,retVal"
	)
	public void onSuccess(JoinPoint jp, Auditable auditable, Object retVal) {
		String entityId = extractId(retVal, jp.getArgs());
		String newData  = toJson(retVal);

		auditLogService.recordNewTx(
			auditable.action(),
			auditable.entity(),
			entityId,
			/*previousData=*/ null,
			/*newData=*/ newData,
			getUserName(),
			getClientIp(),
			getUserAgent(),
			auditable.description()
		);
	}

	@AfterThrowing(
		pointcut = "auditablePointcut(auditable)",
		throwing = "ex",
		argNames = "jp,auditable,ex"
	)
	public void onFailure(JoinPoint jp, Auditable auditable, Throwable ex) {
		String desc = auditable.description()
			+ " 실패: " + ex.getClass().getSimpleName()
			+ "[" + ex.getMessage() + "]";

		auditLogService.recordNewTx(
			ActionType.EXCEPTION,
			auditable.entity(),
			/*entityId=*/ null,
			/*previousData=*/ null,
			/*newData=*/ null,
			getUserName(),
			getClientIp(),
			getUserAgent(),
			desc
		);
	}

	// ────────────────────────────────────────────────────────────────────
	// 이하 유틸 메서드: ID 추출, JSON 직렬화, 사용자·IP·UA 가져오기 등
	// ────────────────────────────────────────────────────────────────────

	private String extractId(Object retVal, Object[] args) {
		try {
			if (retVal != null) {
				var m = retVal.getClass().getMethod("getId");
				Object id = m.invoke(retVal);
				if (id != null) return id.toString();
			}
			for (Object arg : args) {
				if (arg == null) continue;
				var m = arg.getClass().getMethod("getId");
				Object id = m.invoke(arg);
				if (id != null) return id.toString();
			}
		} catch (Exception ignored) {}
		return null;
	}

	private String toJson(Object obj) {
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			return null;
		}
	}

	private String getUserName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getPrincipal() instanceof UserDetails) {
			return ((UserDetails) auth.getPrincipal()).getUsername();
		}
		return null;
	}

	private String getClientIp() {
		var attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return attrs != null ? attrs.getRequest().getRemoteAddr() : null;
	}

	private String getUserAgent() {
		var attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return attrs != null ? attrs.getRequest().getHeader("User-Agent") : null;
	}
}
