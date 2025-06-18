package nowjsio.ticketing.domain.booking.reservation.entity;

public enum ReservationStatus {
	/** 예약 요청만 생성된 상태(포인트 인출 전) */
	PENDING,
	/** 예약이 확정(포인트 차감 완료)된 상태 */
	CONFIRMED,
	/** 사용자가 취소 요청한 상태 */
	CANCELLED,
	/** 예약 유효 기간이 지났거나 만료된 상태 */
	EXPIRED
}