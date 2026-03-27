package collections.sorting;

import java.util.Comparator;
import java.util.List;

public class LengthAndElementReverseComparator implements Comparator<List<Character>> {

    @Override
    public int compare(List<Character> a, List<Character> b) {
        if (b.size() != a.size()){
            return b.size() - a.size();
        }


        return  b.getFirst() - a.getFirst();
    }
}
