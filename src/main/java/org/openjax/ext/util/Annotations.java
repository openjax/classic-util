/* Copyright (c) 2018 OpenJAX
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

package org.openjax.ext.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Utility functions for operations pertaining to {@link Annotation}.
 */
public final class Annotations {
  /**
   * Returns a map of name-value pairs representing the attributes in the
   * specified annotation.
   *
   * @param annotation The {@code Annotation} whose attributes to get.
   * @return A map of name-value pairs representing the attributes in the
   *         specified annotation.
   * @throws NullPointerException If the specified annotation is null.
   */
  public static Map<String,Object> getAttributes(final Annotation annotation) {
    final Class<? extends Annotation> annotationType = annotation.annotationType();
    final Map<String,Object> attributes = new HashMap<>();
    try {
      for (final Method method : annotationType.getDeclaredMethods()) {
        attributes.put(method.getName(), method.invoke(annotation));
      }

      return attributes;
    }
    catch (final IllegalAccessException | InvocationTargetException e) {
      throw new IllegalStateException(e);
    }
  }

  /**
   * Returns a {@code toString()} representation of {@code annotation}, with its
   * property names sorted alphabetically.
   * <p>
   * This method is equivalent to calling
   * {@code toSortedString(annotation, null)}
   *
   * @param annotation The {@link Annotation}.
   * @return A {@code toString()} representation of {@code annotation}, with its
   *         property names sorted by {@code comparator}.
   * @throws NullPointerException If the specified annotation is null.
   */
  public static String toSortedString(final Annotation annotation) {
    return toSortedString(annotation, null);
  }

  /**
   * Returns a {@code toString()} representation of {@code annotation}, with its
   * property names sorted by {@code comparator}.
   *
   * @param annotation The {@link Annotation}.
   * @param comparator The {@link Comparator}.
   * @return A {@code toString()} representation of {@code annotation}, with its
   *         property names sorted by {@code comparator}.
   * @throws NullPointerException If the specified annotation is null.
   */
  public static String toSortedString(final Annotation annotation, final Comparator<String> comparator) {
    final String str = annotation.toString();
    if (str.indexOf('(') == -1)
      return str;

    final TreeMap<String,Object> attributes = comparator != null ? new TreeMap<>(comparator) : new TreeMap<>();
    attributes.putAll(getAttributes(annotation));

    final StringBuilder builder = new StringBuilder("@").append(annotation.annotationType().getName()).append('(');
    final Iterator<Map.Entry<String,Object>> iterator = attributes.entrySet().iterator();
    for (int i = 0; iterator.hasNext(); ++i) {
      if (i > 0)
        builder.append(", ");

      final Map.Entry<String,Object> entry = iterator.next();
      builder.append(entry.getKey()).append('=');
      appendValue(builder, comparator, entry.getValue());
    }

    return builder.append(')').toString();
  }

  private static void appendValue(final StringBuilder builder, final Comparator<String> comparator, final Object value) {
    if (value instanceof String) {
      builder.append('"').append(value).append('"');
    }
    else if (value instanceof Class) {
      builder.append(((Class<?>)value).getName()).append(".class");
    }
    else if (value.getClass().isArray()) {
      builder.append('{');
      if (value.getClass().getComponentType() == byte.class) {
        final byte[] array = (byte[])value;
        for (int i = 0; i < array.length; ++i) {
          if (i > 0)
            builder.append(", ");

          builder.append(array[i]);
        }
      }
      else if (value.getClass().getComponentType() == char.class) {
        final char[] array = (char[])value;
        for (int i = 0; i < array.length; ++i) {
          if (i > 0)
            builder.append(", ");

          builder.append(array[i]);
        }
      }
      else if (value.getClass().getComponentType() == short.class) {
        final short[] array = (short[])value;
        for (int i = 0; i < array.length; ++i) {
          if (i > 0)
            builder.append(", ");

          builder.append(array[i]);
        }
      }
      else if (value.getClass().getComponentType() == int.class) {
        final int[] array = (int[])value;
        for (int i = 0; i < array.length; ++i) {
          if (i > 0)
            builder.append(", ");

          builder.append(array[i]);
        }
      }
      else if (value.getClass().getComponentType() == long.class) {
        final long[] array = (long[])value;
        for (int i = 0; i < array.length; ++i) {
          if (i > 0)
            builder.append(", ");

          builder.append(array[i]);
        }
      }
      else if (value.getClass().getComponentType() == double.class) {
        final double[] array = (double[])value;
        for (int i = 0; i < array.length; ++i) {
          if (i > 0)
            builder.append(", ");

          builder.append(array[i]);
        }
      }
      else if (value.getClass().getComponentType() == float.class) {
        final float[] array = (float[])value;
        for (int i = 0; i < array.length; ++i) {
          if (i > 0)
            builder.append(", ");

          builder.append(array[i]);
        }
      }
      else if (value.getClass().getComponentType() == boolean.class) {
        final boolean[] array = (boolean[])value;
        for (int i = 0; i < array.length; ++i) {
          if (i > 0)
            builder.append(", ");

          builder.append(array[i]);
        }
      }
      else {
        final Object[] array = (Object[])value;
        for (int i = 0; i < array.length; ++i) {
          if (i > 0)
            builder.append(", ");

          appendValue(builder, comparator, array[i]);
        }
      }

      builder.append('}');
    }
    else if (value instanceof Annotation) {
      builder.append(toSortedString((Annotation)value, comparator));
    }
    else {
      builder.append(value);
    }
  }

  private Annotations() {
  }
}