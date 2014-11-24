/**
 * Copyright 2014 Cyril Vlaminck
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.cvlaminck.merging.impl.strategy;

import fr.cvlaminck.merging.api.CloneableObjectMergingStrategy;

import java.util.*;

/**
 * Implementation of ObjectMergingStrategy interface.
 */
public class MutableObjectMergingStrategy
        extends AbstractObjectMergingStrategy
        implements CloneableObjectMergingStrategy {

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
        final Iterator<Map.Entry<Class<?>, String>> defaultStrategyIterator = defaultStrategies.entrySet().iterator();
        String strategy = null;
        while (strategy == null && defaultStrategyIterator.hasNext()) {
            Map.Entry<Class<?>, String> currentEntry = defaultStrategyIterator.next();
            //If the provided type is the current type or a subclass of the current type
            if (currentEntry.getKey().isAssignableFrom(type)) {
                strategy = currentEntry.getValue();
            }
        }
        return strategy;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        final MutableObjectMergingStrategy clone = new MutableObjectMergingStrategy(getObject());
        //Clone specific strategies
        for(Map.Entry<String, String> specificStrategy : specificStrategies.entrySet())
            clone.setSpecificStrategyForField(new String(specificStrategy.getKey()), new String(specificStrategy.getValue()));
        //Clone default strategies
        for(Map.Entry<Class<?>, String> defaultStrategy : defaultStrategies.entrySet())
            clone.setDefaultStrategyForType(defaultStrategy.getKey(), new String(defaultStrategy.getValue()));
        return clone;
    }
}
