package timeseriesweka.classifiers.ee.abcdef.generators;

import timeseriesweka.classifiers.NearestNeighbour;
import timeseriesweka.classifiers.ee.abcdef.CombinedIndexed;
import timeseriesweka.classifiers.ee.abcdef.Indexed;
import timeseriesweka.classifiers.ee.abcdef.IndexedMutator;
import timeseriesweka.classifiers.ee.abcdef.TargetedMutator;
import timeseriesweka.classifiers.ee.index.IndexedSupplier;
import timeseriesweka.measures.DistanceMeasure;
import timeseriesweka.measures.ddtw.Ddtw;
import timeseriesweka.measures.dtw.Dtw;
import utilities.Box;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DtwGenerator extends NnGenerator {

    protected final Box<Dtw> distanceMeasureBox = new Box<>();
    private final IndexedMutator<Dtw, Double> warpingWindowParameter = new IndexedMutator<>(Dtw.WARPING_WINDOW_MUTABLE);
    private final TargetedMutator<Dtw> warpingWindowMutator = new TargetedMutator<>(warpingWindowParameter, distanceMeasureBox);

    public DtwGenerator() {
        List<Indexed> parameters = getParameters().getIndexeds();
        parameters.add(warpingWindowMutator);
    }


    @Override
    protected DistanceMeasure getDistanceMeasure() {
        distanceMeasureBox.setContents(new Dtw());
        return distanceMeasureBox.getContents();
    }

}