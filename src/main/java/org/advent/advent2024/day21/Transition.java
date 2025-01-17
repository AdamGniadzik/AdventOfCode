package org.advent.advent2024.day21;


import java.util.Objects;

public class Transition{

    Character from;
    Character to;

    public Transition(char from, char to) {
        this.from = from;
        this.to = to;
    }
    public Transition(int from, int to) {
        this.from = (char) ('0' + from);
        this.to = (char) ('0' + to);
    }
    public Transition(char from, int to) {
        this.from = from;
        this.to = (char) ('0' + to);
    }
    public Transition(int from, char to) {
        this.from = (char) ('0' + from);
        this.to = to;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transition that = (Transition) o;

        if (!Objects.equals(from, that.from)) return false;
        return Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        int result = from != null ? from.hashCode() : 0;
        result = 31 * result + (to != null ? to.hashCode() : 0);
        return result;
    }

}