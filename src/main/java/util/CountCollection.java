package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CountCollection<T> {

    private HashMap<T, Integer> cardCollection;

    public CountCollection() {
        cardCollection = new HashMap<>();
    }

    public void add(T item, int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Count must be greater than 0");
        }

        int currentCount = 0;
        if (cardCollection.containsKey(item)) {
            currentCount = cardCollection.get(item);
        }
        try {
            int sum = Math.addExact(currentCount, amount);
            cardCollection.put(item, sum);
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
        if (cardCollection.containsKey(item)) {
            currentCount = cardCollection.get(item);
        }
        if (amount > currentCount) {
            throw new IllegalArgumentException("Cannot remove \"" +item + "\" because there is less than " + amount);
        }
        currentCount -= amount;
        cardCollection.put(item, currentCount);
    }
    
    public int getCount(T item) {
        if (cardCollection.containsKey(item)) {
            return cardCollection.get(item);
        } else {
            return 0;
        }
    }

    public List<T> getValuesList(){
        List<T> result = new ArrayList<>();
        for(T key : cardCollection.keySet()){
            for(int i = 0; i < cardCollection.get(key); i++){
                result.add(key);
            }
        }

        return result;
    }

    public int getTotalCount(){
        int totalCount = 0;
        for(T card : cardCollection.keySet()){
            totalCount += cardCollection.get(card);
        }
        return totalCount;
    }

    public Iterator<Tuple<T, Integer>> iterator() {
        // Prune non-positive entries first so they don't sneak into the iterator
        List<T> toRemove = new ArrayList<>();
        for (T t : cardCollection.keySet()) {
            if (cardCollection.get(t) <= 0) {
                toRemove.add(t);
            }
        }
        for (T t : toRemove) {
            cardCollection.remove(t);
        }
        Iterator<Map.Entry<T, Integer>> mapIterator = cardCollection.entrySet().iterator();
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
