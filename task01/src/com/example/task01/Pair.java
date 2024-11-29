package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<F extends Object, S extends Object> {
    private final F first;
    private final S second;

    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public F getFirst() {
        return this.first;
    }

    public S getSecond() {
        return this.second;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.first, this.second);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        else if (obj == null || this.getClass() != obj.getClass()) return false;

        Pair<F, S> other = (Pair<F, S>) obj;

        return (
                this.first == other.first &&
                this.second == other.second &&
                Objects.equals(this.first, other.first) &&
                Objects.equals(this.second, other.second)
        );
    }
    
    
    public static <F extends Object, S extends Object> 
    Pair<F, S> of(F first, S second) {
        return new Pair<F, S>(first, second);
    } 

    public void ifPresent(BiConsumer<? super F, ? super S> action) {
        if (this.first == null || this.second == null) {
            return;
        }

        action.accept(this.first, this.second);
    } 
}
