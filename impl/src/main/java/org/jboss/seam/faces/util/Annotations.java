package org.jboss.seam.faces.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.enterprise.inject.Stereotype;

/**
 * Utility class for common @{@link Annotation} operations.
 * <p>
 * TODO: This should probably go into weld-extensions so other portable extensions can leverage it.
 * 
 * @author <a href="mailto:lincolnbaxter@gmail.com>Lincoln Baxter, III</a>
 * 
 */
public class Annotations {
    /**
     * Discover if a Method <b>m</b> has been annotated with <b>type</b>. This also discovers annotations defined through a @
     * {@link Stereotype}.
     * 
     * @param m The method to inspect.
     * @param type The targeted annotation class
     * 
     * @return True if annotation is present either on the method itself, or on the declaring class of the method. Returns false
     *         if the annotation is not present.
     */
    public static boolean isAnnotationPresent(final Method m, final Class<? extends Annotation> type) {
        boolean result = false;
        if (m.isAnnotationPresent(type)) {
            result = true;
        } else {
            for (Annotation a : m.getAnnotations()) {
                if (a.annotationType().isAnnotationPresent(type)) {
                    result = true;
                }
            }
        }

        if (result == false) {
            result = isAnnotationPresent(m.getDeclaringClass(), type);
        }
        return result;
    }

    /**
     * Discover if a Class <b>c</b> has been annotated with <b>type</b>. This also discovers annotations defined through a @
     * {@link Stereotype}.
     * 
     * @param c The class to inspect.
     * @param type The targeted annotation class
     * 
     * @return True if annotation is present either on class, false if the annotation is not present.
     */
    public static boolean isAnnotationPresent(final Class<?> c, final Class<? extends Annotation> type) {
        boolean result = false;
        if (c.isAnnotationPresent(type)) {
            result = true;
        } else {
            for (Annotation a : c.getAnnotations()) {
                if (a.annotationType().isAnnotationPresent(type)) {
                    result = true;
                }
            }
        }
        return result;
    }

    /**
     * Inspect method <b>m</b> for a specific <b>type</b> of annotation. This also discovers annotations defined through a @
     * {@link Stereotype}.
     * 
     * @param m The method to inspect.
     * @param type The targeted annotation class
     * 
     * @return The annotation instance found on this method or enclosing class, or null if no matching annotation was found.
     */
    public static <A extends Annotation> A getAnnotation(final Method m, final Class<A> type) {
        A result = m.getAnnotation(type);
        if (result == null) {
            for (Annotation a : m.getAnnotations()) {
                if (a.annotationType().isAnnotationPresent(type)) {
                    result = a.annotationType().getAnnotation(type);
                }
            }
        }
        if (result == null) {
            result = getAnnotation(m.getDeclaringClass(), type);
        }
        return result;
    }

    /**
     * Inspect class <b>c</b> for a specific <b>type</b> of annotation. This also discovers annotations defined through a @
     * {@link Stereotype}.
     * 
     * @param c The class to inspect.
     * @param type The targeted annotation class
     * 
     * @return The annotation instance found on this class, or null if no matching annotation was found.
     */
    public static <A extends Annotation> A getAnnotation(final Class<?> c, final Class<A> type) {
        A result = c.getAnnotation(type);
        if (result == null) {
            for (Annotation a : c.getAnnotations()) {
                if (a.annotationType().isAnnotationPresent(type)) {
                    result = a.annotationType().getAnnotation(type);
                }
            }
        }
        return result;
    }
}
