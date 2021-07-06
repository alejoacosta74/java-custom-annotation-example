package ar.alejoacosta.examples.customannotation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ar.alejoacosta.examples.customannotation.exceptions.JsonSerializationException;

public class AnnotationTest {
    
    @Test
    public void  givenObjectSerializedThenTrueReturned()  throws JsonSerializationException {
        Person person = new Person("Rick", "Sanchez", "72");
        ObjectToJsonConverter serializer = new ObjectToJsonConverter(); 
        String jsonString = serializer.convertToJson(person);
        assertEquals(
        "{\"personAge\":\"72\",\"firstName\":\"Rick\",\"lastName\":\"Sanchez\"}",
        jsonString);
    }
    
}

