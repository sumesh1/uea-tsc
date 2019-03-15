package development.go.Ee;

import java.util.*;

public class MeanBestPerType<A, B> implements Selector<A, B> {

    @Override
    public void consider(final A candidate, final B type) {
        List<A> bestList = bestMap.computeIfAbsent(type, key -> new ArrayList<>());
        if(bestList.isEmpty()) {
            bestList.add(candidate);
        } else {
            int comparison = comparator.compare(candidate, bestList.get(0));
            if(comparison >= 0) {
                if(comparison > 0) {
                    bestList.clear();
                }
                bestList.add(candidate);
            }
        }
    }

    @Override
    public List<A> getSelected() {
        List<A> list = new ArrayList<>();
        for(B key : bestMap.keySet()) {
            List<A> values = bestMap.get(key);
            list.add(values.get(random.nextInt(values.size())));
        }
        return list;
    }

    public MeanBestPerType(final Comparator<A> comparator) {
        this.comparator = comparator;
    }

    private Random random = new Random(); // todo set seed / random
    private Map<B, List<A>> bestMap = new HashMap<>();
    private final Comparator<A> comparator; // todo getters setters
}
