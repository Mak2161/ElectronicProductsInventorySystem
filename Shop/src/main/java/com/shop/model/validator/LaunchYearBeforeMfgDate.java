package com.shop.model.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface LaunchYearBeforeMfgDate {
	String message() default "Launch year must be before manufacturing date";

	int launchYear() default 2000;

	int MfgYear() default 2020;
	
//	String MfgYearField() default "MfgYear"; 

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}