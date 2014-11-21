package fr.cvlaminck.merging.impl.mergers.value.object;

import fr.cvlaminck.merging.api.ValueMerger;

/* package */ abstract class AbstractObjectValueMerger
    implements ValueMerger {

    @Override
    public Class<?> getType() {
        return Object.class;
    }

}
