package ar.alejoacosta.examples.customannotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import ar.alejoacosta.examples.customannotation.annotations.Init;
import ar.alejoacosta.examples.customannotation.annotations.JsonElement;
import ar.alejoacosta.examples.customannotation.annotations.JsonSerializable;
import ar.alejoacosta.examples.customannotation.exceptions.JsonSerializationException;

public class ObjectToJsonConverter {


    /* 
    check whether our object is null or not, 
    as well as whether its type has the @JsonSerializable annotation or not:
    */
    private void checkIfSerializable(Object object) {
        if (Objects.isNull(object)) {
            throw new JsonSerializationException("The object to serialize is null");
        }
            
        Class<?> clazz = object.getClass();
        if (!clazz.isAnnotationPresent(JsonSerializable.class)) {
            throw new JsonSerializationException("The class " 
              + clazz.getSimpleName() 
              + " is not annotated with JsonSerializable");
        }
    }

    /* 
    Look for any method with the @Init annotation, 
    and we execute it to initialize our object's fields.
    The call of method.setAccessible(true) allows us 
    to execute the private initNames() method
    */

    private void initializeObject(Object object) throws Exception {
        Class<?> clazz = object.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Init.class)) {
                method.setAccessible(true);
                method.invoke(object);
            }
        }
    }

    /* 
    After the initialization: 
     1. iterate over our object's fields, 
     2. retrieve the key and value of JSON elements, 
     3. and put them in a map
     4. Then create the JSON string from the map:

    @dev: field.setAccessible(true) is used because the Person object's fields are private 
    */

    private String getJsonString(Object object) throws IllegalArgumentException, IllegalAccessException {
        Class<?> clazz = object.getClass();
        Map<String, String> jsonElementsMap = new HashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(JsonElement.class)) {
                jsonElementsMap.put(getKey(field), (String) field.get(object));
            }
        }

        String jsonString = jsonElementsMap.entrySet()
            .stream()
            .map(entry -> "\"" + entry.getKey() + "\":\"" + entry.getValue() + "\"")
            .collect(Collectors.joining(","));
        return "{" + jsonString + "}";
    }

    private String getKey(Field field) {
        String value = field.getAnnotation(JsonElement.class)
            .key();
        return value.isEmpty() ? field.getName() : value;
    }

    public String convertToJson(Object object) throws JsonSerializationException {
        try {
            checkIfSerializable(object);
            initializeObject(object);
              return getJsonString(object);

        } catch (Exception e) {
            throw new JsonSerializationException(e.getMessage());
        }
    }
}

    
