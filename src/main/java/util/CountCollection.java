package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CountCollection<T> {

    private HashMap<T, Integer> counts;

    public CountCollection() {
        counts = new HashMap<>();
    }

    public void add(T item, int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Count must be greater than 0");
        }

        int currentCount = 0;
        if (counts.containsKey(item)) {
            currentCount = counts.get(item);
        }
        try {
            int sum = Math.addExact(currentCount, amount);
            counts.put(item, sum);
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException("Count cannot go over Integer.MAX_VALUE");
        }
    }

    public void remove(T item, int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Count must be greater than 0");
        }
        if (this.getTotalCount() == 0){
            throw new IllegalArgumentException("Cannot remove \""+ item + "\" from empty collection");
        }
        int currentCount = 0;
        if (counts.containsKey(item)) {
            currentCount = counts.get(item);
        }
        if (amount > currentCount) {
            throw new IllegalArgumentException("Cannot remove \"" +item + "\" because there is less than " + amount);
        }
        currentCount -= amount;
        counts.put(item, currentCount);
    }

    public void remove(CountCollection<T> counts) {
        for (T item : counts.counts.keySet()) {
            remove(item, counts.getCount(item));
        }
    }
    
    public int getCount(T item) {
        if (counts.containsKey(item)) {
            return counts.get(item);
        } else {
            return 0;
        }
    }

    public boolean includes(CountCollection<T> other) {
        for (T item : other.counts.keySet()) {
            if (getCount(item) < other.getCount(item)) {
                return false;
            }
        }
        return true;
    }

    public List<T> getValuesList(){
        List<T> result = new ArrayList<>();
        for(T key : counts.keySet()){
            for(int i = 0; i < counts.get(key); i++){
                result.add(key);
            }
        }

        return result;
    }

    public int getTotalCount(){
        int totalCount = 0;
        for(T card : counts.keySet()){
            totalCount += counts.get(card);
        }
        return totalCount;
    }

    public Iterator<Tuple<T, Integer>> iterator() {
        // Prune non-positive entries first so they don't sneak into the iterator
        List<T> toRemove = new ArrayList<>();
        for (T t : counts.keySet()) {
            if (counts.get(t) <= 0) {
                toRemove.add(t);
            }
        }
        for (T t : toRemove) {
            counts.remove(t);
        }
        Iterator<Map.Entry<T, Integer>> mapIterator = counts.entrySet().iterator();
        return new Iterator<Tuple<T, Integer>>() {
            @Override
            public boolean hasNext() {
                return mapIterator.hasNext();
            }

            @Override
            public Tuple<T, Integer> next() {
                Map.Entry<T, Integer> next = mapIterator.next();
                Tuple<T, Integer> tuple = new Tuple<>(next.getKey(), next.getValue());
                return tuple;
            }
        };
    }

}
