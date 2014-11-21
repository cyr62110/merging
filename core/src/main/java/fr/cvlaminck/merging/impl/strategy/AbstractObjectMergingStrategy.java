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
        if (strategy == null) {
            strategy = getDefaultStrategyForType(type);
            if (strategy == null)
                throw new RuntimeException(String.format("No strategy defined for field %s with type %s", fieldName, type)); //TODO Do a proper exception
        }
        return strategy;
    }
}
