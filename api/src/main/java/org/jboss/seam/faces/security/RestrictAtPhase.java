package org.jboss.seam.faces.security;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.jboss.seam.faces.event.PhaseIdType;

/**
 * The phase when security restrictions are applied
 * 
 * @author <a href="mailto:bleathem@gmail.com">Brian Leathem</a>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.TYPE })
@Documented
public @interface RestrictAtPhase {
    public PhaseIdType[] value() default {PhaseIdType.INVOKE_APPLICATION, PhaseIdType.RENDER_RESPONSE};
}
