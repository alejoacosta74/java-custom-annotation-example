package ar.alejoacosta.examples.customannotation.annotations ;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* 
//Class Level Annotation Example//

Next custom annotation will have runtime visibility
and  we can apply it to types (classes). 
Moreover, it has no methods, and thus serves as a simple marker to 
mark classes that can be serialized into JSON.
 */

@Retention(RetentionPolicy.RUNTIME) 
@Target(ElementType.TYPE)
public @interface JsonSerializable {

}