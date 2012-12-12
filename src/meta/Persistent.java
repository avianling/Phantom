package meta;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)

/**
 * A field with the persistant data type will be saved in world files and loaded again at runtime.
 * @author alexander.boorsboom
 *
 */
public @interface Persistent {

}
