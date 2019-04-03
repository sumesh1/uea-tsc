package timeseriesweka.classifiers.Nn.Specialised.Ddtw;

import evaluation.tuning.ParameterSpace;
import timeseriesweka.measures.ddtw.Ddtw;
import timeseriesweka.measures.dtw.Dtw;
import utilities.Utilities;
import weka.core.Instances;

import java.util.Collections;

public class FullWindowTunedDdtwNn extends TunedDdtwNn {

    @Override
    public void useTrainInstances(final Instances trainInstances) {
        ParameterSpace parameterSpace = getParameterSpace();
        parameterSpace.addParameter(Ddtw.WARPING_WINDOW_KEY, Collections.singletonList(1d));
    }
}
