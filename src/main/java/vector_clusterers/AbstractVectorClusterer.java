/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vector_clusterers;

import weka.clusterers.AbstractClusterer;
import weka.core.DistanceFunction;
import weka.core.EuclideanDistance;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author pfm15hbu
 */
public abstract class AbstractVectorClusterer extends AbstractClusterer{
    
    protected DistanceFunction distFunc = new EuclideanDistance();
    protected boolean normaliseData = true;
    protected boolean changeOriginalInstances = true;
    
    //mean and stdev of each attribute for normalisation.
    protected double[] attributeMeans;
    protected double[] attributeStdDevs;
    
    public void setDistanceFunction(DistanceFunction distFunc){
        this.distFunc = distFunc;
    }
    
    public void setNormaliseData(boolean b){
        this.normaliseData = b;
    }
    
    public void setChangeOriginalInstances(boolean b){
        changeOriginalInstances = b;
    }

    //Create lower half distance matrix.
    protected double[][] createDistanceMatrix(Instances data){
        double[][] distMatrix = new double[data.numInstances()][];
        
        for (int i = 0; i < data.numInstances(); i++){
            distMatrix[i] = new double[i];
            Instance first = data.get(i);
            
            for (int n = 0; n < i; n++){
                distMatrix[i][n] = distFunc.distance(first, data.get(n));
            }
        }
        
        return distMatrix;
    }
    
    //Normalise instances and save the means and standard deviations.
    protected void normaliseData(Instances data) throws Exception{
        if (data.classIndex() >= 0 && data.classIndex() != data.numAttributes()-1){
            throw new Exception("Class attribute is available and not the final attribute.");
        }
        
        attributeMeans = new double[data.numAttributes()-1];
        attributeStdDevs = new double[data.numAttributes()-1];
        
        for (int i = 0; i < data.numAttributes()-1; i++){
            attributeMeans[i] = data.attributeStats(i).numericStats.mean;
            attributeStdDevs[i] = data.attributeStats(i).numericStats
                    .stdDev;
            
            for (int n = 0; n < data.size(); n++){
                Instance instance = data.get(n);
                instance.setValue(i, (instance.value(i) - attributeMeans[i])
                        /attributeStdDevs[i]);
            }
        }
    }
}