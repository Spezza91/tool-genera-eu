package it.poste.generafile.configurations.checks;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.sf.oval.configuration.annotation.Constraint;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Constraint(checkWith = DateFormatOvalCheck.class)
public @interface DateFormatOval {

	String errorCode() default "errorCode";
	String message() default "invalid date format";
}
