package util;

public class Tuple<T, R> {
    public T first;
    public R second;

    public Tuple(T t, R r) {
        this.first = t;
        this.second = r;
    }
}
