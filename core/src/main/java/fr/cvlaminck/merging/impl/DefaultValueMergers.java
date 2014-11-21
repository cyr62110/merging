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
package fr.cvlaminck.merging.impl;

import fr.cvlaminck.merging.api.ValueMerger;
import fr.cvlaminck.merging.api.ValueMergers;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class DefaultValueMergers
        implements ValueMergers {

    /**
     * List of type that are supported by register mergers.
     * Since a merger supporting a type also supports all subtypes, the order of type in
     * this list does matter. Subtypes of a listed type must be inserted before the type.
     */
    private Set<Class<?>> registeredMergerTypes = null;

    /**
     * Map containing all registered field mergers.
     * Key : Pair made of the type and the merging strategy supported by the FieldMerger instance
     * Value : a FieldMerger instance
     */
    private Map<Pair<Class<?>, String>, ValueMerger> mergers = null;

    public DefaultValueMergers() {
        this.registeredMergerTypes = new TreeSet<Class<?>>(new Comparator<Class<?>>() {
            @Override
            public int compare(Class<?> c1, Class<?> c2) {
                if (c2.isAssignableFrom(c1))
                    return -1;
                return 1;
            }
        });
        this.mergers = new HashMap<>();
    }

    @Override
    public ValueMerger getMerger(Class<?> fieldType, String mergingStrategy) {
        ValueMerger merger = null;
        Iterator<Class<?>> registeredMergerTypeIterator = registeredMergerTypes.iterator();
        MutablePair<Class<?>, String> key = new MutablePair<Class<?>, String>(fieldType, mergingStrategy);
        Class<?> currentType = null;
        while (merger == null && registeredMergerTypeIterator.hasNext()) {
            currentType = registeredMergerTypeIterator.next();
            //If the field is of or of a subclass the current type
            if (currentType.isAssignableFrom(fieldType)) {
                //We try to retrieve the associated merger
                //If this merger do not exists, we continue in the list.
                key.setLeft(currentType);
                merger = mergers.get(key);
            }
        }
        return merger;
    }

    @Override
    public void registerValueMerger(ValueMerger valueMerger) {
        //If the fieldMerger is already registered, we ignore
        if (mergers.containsValue(valueMerger))
            return;
        //Otherwise, we register it
        registeredMergerTypes.add(valueMerger.getType());
        Pair<Class<?>, String> key = new ImmutablePair<Class<?>, String>(valueMerger.getType(), valueMerger.getMergingStrategy());
        mergers.put(key, valueMerger);
    }

    @Override
    public void unregisterValueMerger(Class<?> mergerType, String mergingStrategy) {
        Pair<Class<?>, String> key = new ImmutablePair<Class<?>, String>(mergerType, mergingStrategy);
        mergers.remove(key);
    }

    @Override
    public void unregisterAllValueMergers() {
        registeredMergerTypes.clear();
        mergers.clear();
    }
}
