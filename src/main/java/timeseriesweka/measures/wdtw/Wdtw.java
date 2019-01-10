package timeseriesweka.measures.wdtw;

import timeseriesweka.measures.dtw.Dtw;

public class Wdtw extends Dtw {

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Wdtw(double warp, double weight) {
        super(warp);
        this.weight = weight;
    }

    public Wdtw() {
        this(1, 0.05);
    }

    private double weight; // AKA g // 0.05 to 3 perhaps

    @Override
    protected double cost(double[] timeSeriesA, int indexA, double[] timeSeriesB, int indexB) {
        return super.cost(timeSeriesA, indexA, timeSeriesB, indexB) +
                1 / (1 + Math.exp(-weight * (Math.abs(indexA - indexB) - (double)timeSeriesA.length / 2)));
    }

    @Override
    public String getRevision() {
        return null;
    }

    @Override
    public String getParameters() {
        return super.getParameters() + ",weight=" + weight + ",";
    }

    @Override
    public String toString() {
        return "wdtw";
    }

}
