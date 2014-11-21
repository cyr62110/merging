package fr.cvlaminck.merging.impl.mergers.value.collection;

import java.util.Collection;

import fr.cvlaminck.merging.api.MergingStrategies;

public class AddInRightCollectionValueMerger
    extends AbstractCollectionValueMerger {

    @Override
    protected Collection merge(Collection left, Collection right) {
        if(left != null && right != null)
            left.addAll(right);
        return left;
    }

    @Override
    public String getMergingStrategy() {
        return MergingStrategies.addInRightCollection;
    }

}
