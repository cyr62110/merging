package fr.cvlaminck.merging.impl.mergers.object;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import fr.cvlaminck.merging.api.ObjectMerger;
import fr.cvlaminck.merging.api.ObjectMergingStrategy;
import fr.cvlaminck.merging.api.ValueMerger;
import fr.cvlaminck.merging.api.ValueMergers;
import fr.cvlaminck.merging.impl.DefaultValueMergers;

/**
 * Default implementation of ObjectMapper. This implementation will only support fields that have
 * public setter and getter. Getter and Setter must have matching signature to be recognized as
 * a valid field : fieldType getFieldName(), void setFieldName(fieldType value)
 */
public class DefaultObjectMerger
        implements ObjectMerger {

    private ValueMergers valueMergers = null;

    public DefaultObjectMerger() {
        this(null);
    }

    public DefaultObjectMerger(ValueMergers valueMergers) {
        this.valueMergers = (valueMergers != null) ? valueMergers : new DefaultValueMergers();
    }

    private String getFieldNameFromGetter(Method getter) {
        return StringUtils.uncapitalize(getter.getName().substring(3));
    }

    private String getSetterNameFromGetter(Method getter) {
        return "set" + getter.getName().substring(3);
    }

    private Method getSetterFromGetter(Class<?> object, Method getter) {
        try {
            Method setter = object.getMethod(getSetterNameFromGetter(getter),
                    getter.getReturnType());
            return setter;
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    protected List<Field> getPublicFieldsWithGetterAndSetter(Class<?> object) {
        List<Field> fields = new ArrayList<>();
        for (Method method : object.getMethods()) {
            if (method.getName().startsWith("get")) {
                Method setter = getSetterFromGetter(object, method);
                if (setter != null)
                    fields.add(new Field(getFieldNameFromGetter(method), setter, method));
            }
        }
        return fields;
    }

    @Override
    public ValueMergers getValueMergers() {
        return valueMergers;
    }

    @Override
    public <T> T merge(T left, T right, ObjectMergingStrategy strategy) {
        if (!strategy.getObject().isAssignableFrom(left.getClass()))
            throw new RuntimeException("Left object class is not a subclass of the object of the strategy"); //TODO create proper exception
        if (!strategy.getObject().isAssignableFrom(right.getClass()))
            throw new RuntimeException("Right object class is not a subclass of the object of the strategy"); //TODO create proper exception
        try {
            for (Field field : getPublicFieldsWithGetterAndSetter(strategy.getObject())) {
                ValueMerger valueMerger = valueMergers.getMerger(field.getType(),
                        strategy.getStrategyForField(field.getName(), field.getType()));
                if (valueMerger == null)
                    throw new RuntimeException(String.format("No value merger configured for field %s of type %s", field.getName(), field.getType())); //TODO create proper exception
                Object mergedValue = valueMerger.merge(
                        field.get(left),
                        field.get(right)
                );
                field.set(left, mergedValue);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return left;
    }

    protected static class Field {

        private String fieldName;

        private Method setter;

        private Method getter;

        private Field(String fieldName, Method setter, Method getter) {
            this.fieldName = fieldName;
            this.setter = setter;
            this.getter = getter;
        }

        public Class<?> getType() {
            return getter.getReturnType();
        }

        public String getName() {
            return fieldName;
        }

        public Object get(Object instance) throws InvocationTargetException, IllegalAccessException {
            return getter.invoke(instance);
        }

        public void set(Object instance, Object value) throws InvocationTargetException, IllegalAccessException {
            setter.invoke(instance, value);
        }

    }

}
