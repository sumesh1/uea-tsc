package timeseriesweka.classifiers.AdvancedAbstractClassifier;

import evaluation.storage.ClassifierResults;
import net.sourceforge.sizeof.SizeOf;
import timeseriesweka.classifiers.*;
import utilities.OptionsSetter;
import utilities.Reproducible;
import utilities.TrainAccuracyEstimate;
import utilities.Utilities;
import weka.classifiers.Classifier;
import weka.core.Instances;
import weka.core.Randomizable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.TimeUnit;


 public interface AdvancedAbstractClassifierInterface extends Serializable, Randomizable, SaveParameterInfo, CheckpointClassifier, ContractClassifier, OptionsSetter, TrainAccuracyEstimate, Classifier, SaveParameterInfoOptions, Logable {

     long getMinCheckpointInterval();

     void setMinCheckpointInterval(long nanoseconds);

     long getPredictionContract();

     void setPredictionContract(final long predictionContract);

     long getTrainContract();

     void setTrainContract(final long trainContract);

     long getTestContract();

     void setTestContract(final long testContract);

     String getSavePath();

    @Override
     void setSavePath(String path);

    @Override
     void copyFromSerObject(final Object obj) throws Exception;

     void setResetOnTrain(boolean on);

     boolean resetsOnTrain();

     boolean resetsOnTest();

     void setResetOnTest(boolean on);

     long getTrainTime();

     long getTestTime();

    @Override
     void setTimeLimit(final long nanoseconds);

     boolean isCvTrain();

     void setCvTrain(final boolean cvTrain);

    @Override
     void setFindTrainAccuracyEstimate(final boolean setCV);

    @Override
     boolean findsTrainAccuracyEstimate();

    @Override
     void writeCVTrainToFile(final String train);

    @Override
     ClassifierResults getTrainResults();

     boolean isCheckpointing();

     void setCheckpointing(boolean on);

     boolean setOption(String key, String value);

}
