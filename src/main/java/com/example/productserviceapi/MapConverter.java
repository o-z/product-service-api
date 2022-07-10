package com.example.productserviceapi;

import org.neo4j.driver.Value;
import org.neo4j.driver.Values;
import org.neo4j.driver.types.IsoDuration;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.lang.Nullable;

import java.time.Duration;
import java.time.Period;
import java.util.*;

@WritingConverter
final class MapConverter implements GenericConverter {
    private final Set<ConvertiblePair> convertibleTypes;
    MapConverter() {
        Set<ConvertiblePair> tmp = new HashSet();
        tmp.add(new ConvertiblePair(LinkedHashMap.class, Value.class));
        this.convertibleTypes = Collections.unmodifiableSet(tmp);
    }
    public Set<ConvertiblePair> getConvertibleTypes() {
        return this.convertibleTypes;
    }

    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (LinkedHashMap.class.isAssignableFrom(sourceType.getType())){
            if ((LinkedHashMap<UUID, Boolean>).class.isAssignableFrom(sourceType.getType())) {
                return Value.class.isAssignableFrom(targetType.getType()) ? Values.NULL : null;
            } else {
                return Value.class.isAssignableFrom(sourceType.getType()) ? Enum.valueOf(targetType.getType(), ((Value)source).asString()) : Values.value(((Enum)source).name());
            }
        }
    }



    @ReadingConverter
    @WritingConverter
    static final class EnumConverter implements GenericConverter {
        private final Set<ConvertiblePair> convertibleTypes;

        EnumConverter() {
            Set<ConvertiblePair> tmp = new HashSet();
            tmp.add(new ConvertiblePair(Value.class, Enum.class));
            tmp.add(new ConvertiblePair(Enum.class, Value.class));
            this.convertibleTypes = Collections.unmodifiableSet(tmp);
        }

        public Set<ConvertiblePair> getConvertibleTypes() {
            return this.convertibleTypes;
        }

        public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
            if (source == null) {
                return Value.class.isAssignableFrom(targetType.getType()) ? Values.NULL : null;
            } else {
                return Value.class.isAssignableFrom(sourceType.getType()) ? Enum.valueOf(targetType.getType(), ((Value)source).asString()) : Values.value(((Enum)source).name());
            }
        }
    }
}
