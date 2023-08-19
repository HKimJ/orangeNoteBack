// ----------------------------
// -    현재 사용하지 않음    -
// ----------------------------

//package com.example.orangeNote.api;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import javax.persistence.AttributeConverter;
//import javax.persistence.Converter;
//import java.util.Map;
//
//@Converter
//public class JsonAttributeConverter implements AttributeConverter<Map<String, Object>, String> {
//
//    private final ObjectMapper objectMapper = new ObjectMapper();
//    @Override
//    public String convertToDatabaseColumn(Map<String, Object> attribute) {
//        try {
//            return objectMapper.writeValueAsString(attribute);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException("Error converting JSON to string", e);
//        }
//    }
//
//    @Override
//    public Map<String, Object> convertToEntityAttribute(String dbData) {
//        try {
//            return objectMapper.readValue(dbData, new TypeReference<Map<String, Object>>() {});
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException("Error converting string to JSON", e);
//        }
//    }
//}
