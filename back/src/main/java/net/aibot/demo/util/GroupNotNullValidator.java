package net.aibot.demo.util;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class GroupNotNullValidator {
    @NoArgsConstructor
    @Data
    static class GroupCount {
        private int totalCount;
        private int nullValueCount;

        public void addTotalCount() {this.totalCount += 1;}
        public void addNullValueCount() {this.nullValueCount += 1;}
    }

    public static <T> void validateGroupNotNull(T input) {
        Map<String, GroupCount> groupCountMap = new HashMap<>();
        try {
            for (Field field : input.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                GroupNotNull annotation = field.getAnnotation(GroupNotNull.class);
                if(annotation == null) continue;

                String key = annotation.name();
                GroupCount currentObject = groupCountMap.getOrDefault(key, new GroupCount());
                currentObject.addTotalCount();

                Object inputValue = field.get(input);
                if (inputValue == null) {
                    currentObject.addNullValueCount();
                }
                groupCountMap.put(key, currentObject);
            }
        } catch (IllegalAccessException e) {
            log.error("error occurs during toMap() fields of " + input.getClass(), e);
        }

        for (String key : groupCountMap.keySet()) {
            GroupCount currentObject = groupCountMap.get(key);
            if (currentObject.getTotalCount() == currentObject.getNullValueCount()) {
                throw new IllegalArgumentException("Check your input value");
            }
        }
    }
}
