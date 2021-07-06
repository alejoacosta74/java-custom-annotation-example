package ar.alejoacosta.examples.customannotation.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* 
//Method Level Annotation Example//

Let's imagine that before serializing an object to a JSON string, 
we want to execute some method to initialize an object. 
For that reason, we're going to create an annotation to mark this method:
It is a public annotation with runtime visibility that we can apply to our classes' methods.
*/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Init {
    
}
