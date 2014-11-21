package fr.cvlaminck.merging.impl.mergers.value.object;

import fr.cvlaminck.merging.api.MergingStrategies;

public class AlwaysUseLeftObjectValueMerger
    extends AbstractObjectValueMerger {

    @Override
    public String getMergingStrategy() {
        return MergingStrategies.alwaysUseLeft;
    }

    @Override
    public Object merge(Object left, Object right) {
        return left;
    }

}
