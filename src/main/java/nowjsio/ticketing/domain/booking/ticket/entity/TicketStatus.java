package nowjsio.ticketing.domain.booking.ticket.entity;

public enum TicketStatus {
	/** 발행 후 아직 예약(판매)되지 않은 상태 */
	AVAILABLE,
	/** 예약 혹은 판매 완료 상태 */
	RESERVED,
	/** 실제 사용(입장) 완료 상태 */
	USED,
	/** 티켓이 취소된 상태 */
	CANCELLED
}
