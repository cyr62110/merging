package fr.cvlaminck.merging.impl.strategy;

/**
 * Implementation of ObjectMergingStrategy interface.
 */
public class MutableObjectMergingStrategy
    extends AbstractObjectMergingStrategy {

    public MutableObjectMergingStrategy(Class<?> object) {
        super(object);
    }

    @Override
    public String getSpecificStrategyForField(String fieldName) {
        return null;
    }

    @Override
    public String getDefaultStrategyForType(Class<?> type) {
        return null;
    }

}
