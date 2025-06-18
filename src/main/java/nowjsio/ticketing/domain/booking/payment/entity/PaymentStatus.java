package nowjsio.ticketing.domain.booking.payment.entity;

public enum PaymentStatus {
	/** 결제 요청은 되었으나 아직 최종 승인 전 상태 */
	PENDING,
	/** 결제 완료 상태 */
	COMPLETED,
	/** 결제 실패 상태 */
	FAILED,
	/** 결제 취소 상태 */
	CANCELLED
}