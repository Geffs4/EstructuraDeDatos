package TdaABB;
import java.util.Comparator;

public class DefaultComparator<K> implements Comparator<K> {
    @Override
    public int compare(K a, K b) {
        return ((Comparable<K>) a).compareTo(b);
    }
}
