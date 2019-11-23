/* Copyright (c) 2014 LibJ
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * You should have received a copy of The MIT License (MIT) along with this
 * program. If not, see <http://opensource.org/licenses/MIT/>.
 */

package org.libj.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.DoublePredicate;
import java.util.stream.DoubleStream;

/**
 * The root interface in the <i>{@code double} collection hierarchy</i>. A
 * primitive collection represents a group of {@code double} values. Some
 * collections allow duplicate elements and others do not. Some are ordered and
 * others unordered.
 * <p>
 * The architecture of the <i>{@code double} collection hierarchy</i> is designed
 * to mimic that of the <i>collection hierarchy</i> in {@link Collection}, in
 * order to provide a nearly identical API for ease of use and interoperability.
 *
 * @see DoubleList
 * @see ArrayDoubleList
 * @see DoubleSet
 * @see HashDoubleSet
 */
public interface DoubleCollection extends DoubleIterable, PrimitiveCollection {
  /**
   * Ensures that this collection contains the specified value (optional
   * operation). Returns {@code true} if this collection changed as a result of
   * the call. (Returns {@code false} if this collection does not permit
   * duplicates and already contains the specified value.).
   *
   * @param value Value whose presence in this collection is to be ensured.
   * @return {@code true} if this collection changed as a result of the call.
   */
  boolean add(double value);

  /**
   * Appends all of the values in the specified collection to the end of this
   * collection, in the order that they are returned by the specified
   * collection's {@link DoubleIterator}. The behavior of this operation is
   * undefined if the specified collection is modified while the operation is in
   * progress. (This implies that the behavior of this call is undefined if the
   * specified collection is this collection, and this collection is nonempty.)
   *
   * @param c Collection containing values to be added to this collection.
   * @return {@code true} if this collection changed as a result of the call.
   * @throws NullPointerException If the specified collection is null.
   */
  boolean addAll(DoubleCollection c);

  /**
   * Appends all of the values in the specified collection to the end of this
   * collection, in the order that they are returned by the specified
   * collection's {@link Iterator}. The behavior of this operation is undefined
   * if the specified collection is modified while the operation is in progress.
   * (This implies that the behavior of this call is undefined if the specified
   * collection is this collection, and this collection is nonempty.)
   *
   * @param c Collection containing values to be added to this collection.
   * @return {@code true} if this collection changed as a result of the call.
   * @throws ClassCastException If the class of an element of the specified
   *           collection is not {@link Double}.
   * @throws NullPointerException If the specified collection is null.
   */
  boolean addAll(Collection<Double> c);

  /**
   * Removes the first occurrence of the specified value from this collection,
   * if it is present. If the collection does not contain the value, it is
   * unchanged. More formally, removes the value with the lowest index {@code i}
   * such that {@code Objects.equals(o, get(i))} (if such an value exists).
   * Returns {@code true} if this collection contained the specified value (or
   * equivalently, if this collection changed as a result of the call).
   *
   * @param value Value to be removed from this collection, if present.
   * @return {@code true} if this collection contained the specified value.
   */
  boolean remove(double value);

  /**
   * Removes all of this collection's values that are also contained in the
   * specified collection. After this call returns, this collection will contain
   * no values in common with the specified collection.
   *
   * @param c Collection containing values to be removed from this collection.
   * @return {@code true} if this collection changed as a result of the call.
   * @see #remove(double)
   * @see #contains(double)
   */
  boolean removeAll(DoubleCollection c);

  /**
   * Removes all of this collection's values that are also contained in the
   * specified collection. After this call returns, this collection will contain
   * no values in common with the specified collection.
   *
   * @param c Collection containing values to be removed from this collection.
   * @return {@code true} if this collection changed as a result of the call.
   * @throws ClassCastException If the class of an element of the specified
   *           collection is not an {@link Double}.
   * @see #remove(double)
   * @see #contains(double)
   */
  default boolean removeAll(final Collection<Double> c) {
    final int beforeSize = size();
    for (final Iterator<Double> i = c.iterator(); i.hasNext();) {
      final double value = i.next();
      while (remove(value));
    }

    return beforeSize != size();
  }

  /**
   * Removes all of the values of this collection that satisfy the given
   * predicate. Errors or runtime exceptions thrown during iteration or by the
   * predicate are relayed to the caller.
   * <p>
   * The default implementation traverses all values of the collection using its
   * {@link #iterator()}. Each matching value is removed using
   * {@link IntIterator#remove()}. If the collection's iterator does not support
   * removal then an {@link UnsupportedOperationException} will be thrown on the
   * first matching value.
   *
   * @param filter A predicate which returns {@code true} for values to be
   *          removed.
   * @return {@code true} if any values were removed.
   * @throws NullPointerException If the specified filter is null.
   * @throws UnsupportedOperationException If values cannot be removed from
   *           this collection. Implementations may throw this exception if a
   *           matching value cannot be removed or if, in general, removal is
   *           not supported.
   */
  default boolean removeIf(DoublePredicate filter) {
    Objects.requireNonNull(filter);
    boolean removed = false;
    final DoubleIterator each = iterator();
    while (each.hasNext()) {
      if (filter.test(each.next())) {
        each.remove();
        removed = true;
      }
    }

    return removed;
  }

  /**
   * Retains only the values in this collection that are contained in the
   * specified collection. In other words, removes from this collection all of
   * its values that are not contained in the specified collection.
   *
   * @param c Collection containing values to be retained in this collection.
   * @return {@code true} if this collection changed as a result of the call.
   * @throws ClassCastException If the class of an element of the specified
   *           collection is not {@link Double}.
   * @throws NullPointerException If the specified collection contains a null
   *           value, or if the specified collection is null.
   * @see #remove(double)
   * @see #contains(double)
   */
  boolean retainAll(Collection<Double> c);

  /**
   * Retains only the values in this collection that are contained in the
   * specified collection. In other words, removes from this collection all of
   * its values that are not contained in the specified collection.
   *
   * @param c Collection containing values to be retained in this collection.
   * @return {@code true} if this collection changed as a result of the call.
   * @throws NullPointerException If the specified collection contains a null
   *           value, or if the specified collection is null.
   * @see #remove(double)
   * @see #contains(double)
   */
  boolean retainAll(DoubleCollection c);

  /**
   * Returns {@code true} if this collection contains the specified value. More
   * formally, returns {@code true} if and only if this collection contains at
   * least one value {@code v} such that {@code Objects.equals(o, e)}.
   *
   * @param value Value whose presence in this collection is to be tested
   * @return {@code true} if this collection contains the specified value.
   */
  boolean contains(double value);

  /**
   * Returns {@code true} if this collection contains all of the values in the
   * specified collection.
   *
   * @param c Collection to be checked for containment in this collection.
   * @return {@code true} if this collection contains all of the values in the
   *         specified collection.
   * @see #contains(double)
   */
  default boolean containsAll(final DoubleCollection c) {
    for (final DoubleIterator i = c.iterator(); i.hasNext();)
      if (!contains(i.next()))
        return false;

    return true;
  }

  /**
   * Returns {@code true} if this collection contains all of the values in the
   * specified collection.
   *
   * @param c Collection to be checked for containment in this collection.
   * @return {@code true} if this collection contains all of the values in the
   *         specified collection.
   * @throws ClassCastException If the class of an element of the specified
   *           collection is not an {@link Double}.
   * @see #contains(double)
   */
  default boolean containsAll(final Collection<Double> c) {
    for (final Iterator<Double> i = c.iterator(); i.hasNext();)
      if (!contains(i.next()))
        return false;

    return true;
  }

  /**
   * Creates a {@link java.util.Spliterator.OfDouble} over the values in this collection.
   *
   * @return A {@code Spliterator.OfDouble} over the values in this collection.
   */
  Spliterator.OfDouble spliterator();

  /**
   * Returns a sequential {@link DoubleStream} over the values in this collection.
   *
   * @return A sequential {@link DoubleStream} over the values in this collection.
   */
  DoubleStream stream();

  /**
   * Returns a possibly parallel {@link DoubleStream} over the values in this
   * collection.
   *
   * @return A possibly parallel {@link DoubleStream} over the values in this
   *         collection.
   */
  DoubleStream parallelStream();
}