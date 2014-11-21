package fr.cvlaminck.merging.impl.mergers.value.collection;

import java.util.Collection;

import fr.cvlaminck.merging.api.ValueMerger;

/* package */ abstract class AbstractCollectionValueMerger
    implements ValueMerger {

    @Override
    public Class<?> getType() {
        return Collection.class;
    }

    @Override
    public Object merge(Object left, Object right) {
        return merge((Collection)left, (Collection)right);
    }

    protected abstract Collection merge(Collection left, Collection right);

}
