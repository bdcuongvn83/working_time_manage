/**
 * 
 */
package net.service.interceptor.anotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * @author cuongbd
 *
 */
@Qualifier
@Retention(RUNTIME)
@Target({TYPE, METHOD,ElementType.FIELD, ElementType.PARAMETER})
@Documented
public @interface Login {

}
