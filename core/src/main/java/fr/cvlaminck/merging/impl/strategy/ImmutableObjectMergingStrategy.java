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
import fr.cvlaminck.merging.api.ObjectMergingStrategy;
import org.apache.commons.lang3.tuple.Pair;

/**
 * An object merging strategy that cannot be modified after being instantiated.
 *
 * @since 1.1
 */
public class ImmutableObjectMergingStrategy
        implements ObjectMergingStrategy {

    /**
     * All calls to the ObjectMergingStrategy will be delegated to this objectMergingStrategy.
     */
    private ObjectMergingStrategy wrappedObjectMergingStrategy = null;

    /**
     * Create an immutable merging strategy based on another merging strategy.
     * The strategy passed as parameter will be cloned so any further modification to this strategy will
     * not affect the immutable version based on it.
     *
     * @param objectMergingStrategy ObjectMergingStrategy on which this immutable version will be based.
     * @since 1.1
     */
    public ImmutableObjectMergingStrategy(CloneableObjectMergingStrategy objectMergingStrategy) {
        try {
            this.wrappedObjectMergingStrategy = (ObjectMergingStrategy) objectMergingStrategy.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(String.format("clone() thrown CloneNotSupportedException. %s should implement this method to be wrapped into an ImmutableObjectMergingStrategy"
                    , objectMergingStrategy.getClass().getSimpleName())); //TODO  Do a proper exception
        }
    }

    /**
     * Create an immutable object merging strategy.
     *
     * @since 1.1
     */
    public ImmutableObjectMergingStrategy(Class<?> object, Pair... strategies) {
        final MutableObjectMergingStrategy objectMergingStrategy = new MutableObjectMergingStrategy(object);
        //All pairs that do not math the requirements will be simply ignored
        for (Pair strategy : strategies) {
            if (strategy.getRight() instanceof String) {
                //If we have a Pair<String, String> then the user has described a strategy specific to a field
                if (strategy.getLeft() instanceof String)
                    objectMergingStrategy.setSpecificStrategyForField((String) strategy.getLeft(), (String) strategy.getRight());
                //If we have a Pair<Object, String> then the user has described a default strategy
                if (strategy.getLeft() instanceof Class)
                    objectMergingStrategy.setDefaultStrategyForType((Class<?>) strategy.getLeft(), (String) strategy.getRight());
            }
        }
        this.wrappedObjectMergingStrategy = objectMergingStrategy;
    }

    @Override
    public Class<?> getObject() {
        return wrappedObjectMergingStrategy.getObject();
    }

    @Override
    public String getStrategyForField(String fieldName, Class<?> type) {
        return wrappedObjectMergingStrategy.getStrategyForField(fieldName, type);
    }

    @Override
    public String getSpecificStrategyForField(String fieldName) {
        return wrappedObjectMergingStrategy.getSpecificStrategyForField(fieldName);
    }

    @Override
    public String getDefaultStrategyForType(Class<?> type) {
        return wrappedObjectMergingStrategy.getDefaultStrategyForType(type);
    }
}
