package fr.cvlaminck.merging.impl.strategy;

import fr.cvlaminck.merging.api.ObjectMergingStrategy;

/* package */ abstract class AbstractObjectMergingStrategy
    implements ObjectMergingStrategy {

    private Class<?> object;

    protected AbstractObjectMergingStrategy(Class<?> object) {
        this.object = object;
    }

    @Override
    public Class<?> getObject() {
        return object;
    }

    @Override
    public String getStrategyForField(String fieldName, Class<?> type) {
        String strategy = getSpecificStrategyForField(fieldName);
        if(strategy == null) {
            strategy = getDefaultStrategyForType(type);
            if (strategy == null)
                throw new RuntimeException(String.format("No strategy defined for field %s with type %s", fieldName, type)); //TODO Do a proper exception
        }
        return strategy;
    }
}
