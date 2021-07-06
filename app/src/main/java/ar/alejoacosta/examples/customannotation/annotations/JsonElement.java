package ar.alejoacosta.examples.customannotation.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//Field Level Annotation Example//
/* 
Annotation to mark the fields that we are going to include in the generated JSON:
Declares one String parameter with the name key and an empty string as the default value.
Methods annotation must have no parameters, and cannot throw an exception. 
Also, the return types are restricted to primitives, String, Class, enums, annotations, and arrays of these types, 
and the default value cannot be null.
 */

@Retention(RetentionPolicy.RUNTIME) 
@Target(ElementType.FIELD)
public @interface JsonElement {
    public String key() default "";   
}
