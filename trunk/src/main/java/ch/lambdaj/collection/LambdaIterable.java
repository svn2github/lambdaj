// Modified or written by Ex Machina SAGL for inclusion with lambdaj.
// Copyright (c) 2009 Mario Fusco.
// Licensed under the Apache License, Version 2.0 (the "License")

package ch.lambdaj.collection;

import ch.lambdaj.*;
import ch.lambdaj.group.*;
import ch.lambdaj.function.convert.*;
import org.hamcrest.*;

import java.util.*;

import static org.hamcrest.Matchers.not;

/**
 * @author Gianfranco Tognana
 * @author Mario Fusco
 */
public class LambdaIterable<T> extends AbstractLambdaCollection<T> implements Iterable<T> {

    protected LambdaIterable(Iterable<? extends T> inner) {
        super(inner);
    }

    /**
     * {@inheritDoc}
     */
    public LambdaIterator<T> iterator() {
        return new LambdaIterator<T>(innerIterable.iterator());
    }

    public T joinFrom() {
        return Lambda.joinFrom(innerIterable);
    }

    public T joinFrom(String separator) {
        return Lambda.joinFrom(innerIterable, separator);
    }

    /**
     * Filters all the objects in this iterable that match the given hamcrest Matcher
     * @param matcher The hamcrest Matcher used to filter this iterable
     * @return A sublist of this containing all the objects that match the given hamcrest Matcher
     */
    public LambdaIterable<T> filter(Matcher<?> matcher) {
        return new LambdaIterable<T>(doFilter(matcher));
    }

    List<T> doFilter(Matcher<?> matcher) {
        return (List<T>)Lambda.select(innerIterable, matcher);
    }

    /**
     * Removes all the objects in this iterable that match the given hamcrest Matcher
     * @param matcher The hamcrest Matcher used to filter this iterable
     * @return A sublist of this containing all the objects that don't match the given hamcrest Matcher
     */
    public LambdaIterable<T> remove(Matcher<?> matcher) {
        return new LambdaIterable<T>(doRemove(matcher));
    }

    List<T> doRemove(Matcher<?> matcher) {
        return (List<T>)Lambda.select(innerIterable, not(matcher));
    }

    /**
     * Sorts all the items in this iterable on the respective values of the given argument.
     * @param argument An argument defined using the {@link Lambda#on(Class)} method
     * @return A List with the same items of this iterable sorted on the respective value of the given argument
     */
    public LambdaIterable<T> sort(Object argument) {
        return new LambdaIterable<T>(doSort(argument));
    }

    List<T> doSort(Object argument) {
        return Lambda.sort(innerIterable, argument);
    }

    /**
     * Converts all the object in this iterable using the given {@link Converter}.
     * @param converter The converter that specifies how each object in the iterable must be converted
     * @return A LambdaIterable containing all the objects in this iterable converted using the given {@link Converter}
     */
    public <V> LambdaIterable<V> convert(Converter<T, V> converter) {
        return new LambdaIterable<V>(doConvert(converter));
    }

    <V> List<V> doConvert(Converter<T, V> converter) {
        return Lambda.convert(innerIterable, converter);
    }

    /**
	 * Converts all the object in this iterable extracting the property defined by the given argument.
	 * @param argument An argument defined using the {@link Lambda#on(Class)} method
	 * @return A LambdaIterable containing the argument's value extracted from the object in this iterable
	 */
    public <V> LambdaIterable<V> extract(V argument) {
        return new LambdaIterable<V>(doExtract(argument));
    }

    <V> List<V> doExtract(V argument) {
        return Lambda.extract(innerIterable, argument);
    }

    /**
     * Replace with the given replacer all the items in this iterable that match the given matcher
     * @param matcher The hamcrest Matcher used to filter this iterable
     * @param replacer The item with which all the items that matches will be replaced
     * @return A LambdaIterable with all the items matching the given matcher replaced by the given replacer
     */
    public LambdaIterable<T> replace(Matcher<?> matcher, T replacer) {
        return new LambdaIterable<T>(doReplace(matcher, replacer));
    }

    List<T> doReplace(Matcher<?> matcher, T replacer) {
        List<T> list = new ArrayList<T>();
        for (T item : innerIterable) { list.add(matcher.matches(item) ? replacer : item); }
        return null;
    }

    /**
     * Returns a Set containing only distinict items in this iterable
     * @return A Set containing only distinict items in this iterable
     */
    public LambdaSet<T> distinct() {
        Set<T> set = new HashSet<T>();
        for (T item : innerIterable) { set.add(item); }
        return new LambdaSet<T>(set);
    }

    public LambdaIterable<T> distinct(Object argument) {
        return new LambdaIterable<T>(doDistinct(argument));
    }

    Collection<T> doDistinct(Object argument) {
        return Lambda.selectDistinctArgument(innerIterable, argument);
    }

    /**
     * Organizes the given list of items in (hierarchy of) groups based on the given grouping conditions
     * @param conditions The conditions that define how the items have to be grouped
     * @return The items grouped by the given conditions
     */
	public LambdaGroup<T> group(GroupCondition<?>... conditions) {
         return new LambdaGroup(Lambda.group(innerIterable, conditions));
    }
}
