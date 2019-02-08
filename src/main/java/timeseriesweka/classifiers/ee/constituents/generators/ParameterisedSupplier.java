package timeseriesweka.classifiers.ee.constituents.generators;

import timeseriesweka.classifiers.NearestNeighbour;
import timeseriesweka.classifiers.ee.constituents.CombinedIndexed;
import timeseriesweka.classifiers.ee.index.IndexedSupplier;
import timeseriesweka.measures.DistanceMeasure;
import timeseriesweka.measures.lcss.Lcss;
import utilities.Box;
import weka.core.Instances;

public abstract class ParameterisedSupplier<A> implements IndexedSupplier<A> {
    private final CombinedIndexed parameters = new CombinedIndexed();
    private final Box<A> box = new Box<>();

    @Override
    public final int size() {
        return getParameters().size();
    }

    protected CombinedIndexed getParameters() {
        return parameters;
    }

    @Override
    public A get(final int index) {
        A subject = supply();
        parameters.setValueAt(index);
        return subject;
    }

    protected final A supply() {
        A subject = get();
        box.setContents(subject);
        return subject;
    }

    protected abstract A get();

    protected Box<A> getBox() {
        return box;
    }

    public abstract void setParameterRanges(Instances instances); // todo strip out into subclass
}