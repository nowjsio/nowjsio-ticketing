-- 1) 메인 로그 이벤트 테이블
CREATE TABLE IF NOT EXISTS logging_event (
    event_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    timestmp BIGINT NOT NULL,
    formatted_message TEXT,
    logger_name VARCHAR(254),
    level_string VARCHAR(254),
    thread_name VARCHAR(254),
    reference_flag SMALLINT,
    arg0 VARCHAR(254),
    arg1 VARCHAR(254),
    arg2 VARCHAR(254),
    arg3 VARCHAR(254),
    caller_filename VARCHAR(254),
    caller_class VARCHAR(254),
    caller_method VARCHAR(254),
    caller_line CHAR(4)
);

-- 2) 속성 테이블 (MDC, 파라미터 등 key/value)
CREATE TABLE IF NOT EXISTS logging_event_property (
    event_id BIGINT NOT NULL,
    mapped_key VARCHAR(254) NOT NULL,
    mapped_value VARCHAR(254),
    PRIMARY KEY (event_id, mapped_key),
    INDEX idx_event_id (event_id),
    CONSTRAINT fk_logging_event
    FOREIGN KEY (event_id)
    REFERENCES logging_event(event_id)
    ON DELETE CASCADE
);

-- 3) 예외 스택트레이스 저장용 테이블
CREATE TABLE IF NOT EXISTS logging_event_exception (
    event_id BIGINT       NOT NULL,
    i        SMALLINT     NOT NULL,    -- 스택트레이스 라인 순서
    trace_line VARCHAR(254) NOT NULL,  -- 한 줄씩 저장
    PRIMARY KEY (event_id, i),
    INDEX idx_exc_event_id (event_id),
    CONSTRAINT fk_logging_event_exc
    FOREIGN KEY (event_id)
    REFERENCES logging_event(event_id)
    ON DELETE CASCADE
);
