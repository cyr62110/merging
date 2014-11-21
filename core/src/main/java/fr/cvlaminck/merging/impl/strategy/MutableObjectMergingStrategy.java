package fr.cvlaminck.merging.impl.strategy;

import java.util.*;

/**
 * Implementation of ObjectMergingStrategy interface.
 */
public class MutableObjectMergingStrategy
        extends AbstractObjectMergingStrategy {

    /**
     * Map containing all default strategies and the types they are
     * associated with.
     * The order in this map does matter when we iterate on it. Classes that inherit from
     * a class already contained in the map must be placed before their parent classes.
     */
    private SortedMap<Class<?>, String> defaultStrategies = null;

    private Map<String, String> specificStrategies = null;

    public MutableObjectMergingStrategy(Class<?> object) {
        super(object);
        this.defaultStrategies = new TreeMap<Class<?>, String>(new Comparator<Class<?>>() {
            @Override
            public int compare(Class<?> c1, Class<?> c2) {
                if (c1 == c2)
                    return 0;
                else if (c2.isAssignableFrom(c1))
                    return -1;
                else
                    return 1;
            }
        });
        this.specificStrategies = new HashMap<>();
    }

    public void setSpecificStrategyForField(String fieldName, String strategy) {
        specificStrategies.put(fieldName, strategy);
    }

    public void removeSpecificStrategyForField(String fieldName) {
        specificStrategies.remove(fieldName);
    }

    public void setDefaultStrategyForType(Class<?> type, String strategy) {
        defaultStrategies.put(type, strategy);
    }

    public void removeDefaultStrategyForType(Class<?> type) {
        defaultStrategies.remove(type);
    }

    @Override
    public String getSpecificStrategyForField(String fieldName) {
        return specificStrategies.get(fieldName);
    }

    @Override
    public String getDefaultStrategyForType(Class<?> type) {
        Iterator<Map.Entry<Class<?>, String>> defaultStrategyIterator = defaultStrategies.entrySet().iterator();
        String strategy = null;
        while(strategy == null && defaultStrategyIterator.hasNext()) {
            Map.Entry<Class<?>, String> currentEntry = defaultStrategyIterator.next();
            //If the provided type is the current type or a subclass of the current type
            if(currentEntry.getKey().isAssignableFrom(type)) {
                strategy = currentEntry.getValue();
            }
        }
        return strategy;
    }

}
