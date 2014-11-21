package fr.cvlaminck.merging.impl.mergers.value.object;

import fr.cvlaminck.merging.api.MergingStrategies;

public class UseRightIfLeftIsNullObjectValueMerger
    extends AbstractObjectValueMerger {
    @Override
    public String getMergingStrategy() {
        return MergingStrategies.useRightIfLeftIsNull;
    }

    @Override
    public Object merge(Object left, Object right) {
        return (left != null) ? left : right;
    }
}
