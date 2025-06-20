package nowjsio.ticketing.domain.audit.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import nowjsio.ticketing.domain.audit.entity.ActionType;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Auditable {
	ActionType action();

	String entity();

	String description() default "";
}