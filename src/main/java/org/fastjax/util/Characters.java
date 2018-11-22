/* Copyright (c) 2018 FastJAX
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

package org.fastjax.util;

import java.util.Arrays;

/**
 * Utility functions for operations pertaining to {@code char} and {@link Character}.
 */
public final class Characters {
  /**
   * Tests whether the specified {@code int} is an ANSI whitespace char, which
   * is one of:
   *
   * <pre>
   * {@code ' '}, {@code '\n'}, {@code '\r'}, or {@code '\t'}
   * </pre>
   *
   * @param ch The {@code int} to test.
   * @return {@code true} if the specified {@code int} is an ANSI whitespace
   *         char; otherwise {@code false}.
   */
  public static boolean isWhiteSpace(final int ch) {
    return ch == ' ' || ch == '\n' || ch == '\r' || ch == '\t';
  }

  private static final char[] escapableChars = {'"', '\'', '0', '1', '2', '3', '4', '5', '6', '7', '\\', 'b', 'f', 'n', 'r', 't'};
  private static final char[] escapedChars = {'\"', '\'', '\0', '\1', '\2', '\3', '\4', '\5', '\6', '\7', '\\', '\b', '\f', '\n', '\r', '\t'};

  /**
   * Returns an escaped representation of the specified character. The following characters
   * are escapable:
   * <blockquote>
   * <table>
   * <caption>Escape Sequences</caption>
   * <tr><td><b>Escape Sequence</b></td><td><b>Description</b></td></tr>
   * <tr><td><code>\<b>t</b></code></td><td>Tab</td></tr>
   * <tr><td><code>\<b>b</b></code></td><td>Backspace</td></tr>
   * <tr><td><code>\<b>n</b></code></td><td>Newline</td></tr>
   * <tr><td><code>\<b>r</b></code></td><td>Carriage return</td></tr>
   * <tr><td><code>\<b>f</b></code></td><td>Formfeed</td></tr>
   * <tr><td><code>\<b>u</b></code></td><td>Unicode</td></tr>
   * <tr><td><code>\<b>'</b></code></td><td>Single quote</td></tr>
   * <tr><td><code>\<b>"</b></code></td><td>Double quote</td></tr>
   * <tr><td><code>\<b>\</b></code></td><td>Backslash</td></tr>
   * <tr><td><code>\<b>0</b></code></td><td>0</td></tr>
   * <tr><td><code>\<b>1</b></code></td><td>1</td></tr>
   * <tr><td><code>\<b>2</b></code></td><td>2</td></tr>
   * <tr><td><code>\<b>3</b></code></td><td>3</td></tr>
   * <tr><td><code>\<b>4</b></code></td><td>4</td></tr>
   * <tr><td><code>\<b>5</b></code></td><td>5</td></tr>
   * <tr><td><code>\<b>6</b></code></td><td>6</td></tr>
   * <tr><td><code>\<b>7</b></code></td><td>7</td></tr>
   * </table>
   * </blockquote>
   *
   * @param ch The character to escape.
   * @return The escaped character.
   * @throws IllegalArgumentException If the specified character is not escapable.
   * @see <a href=
   *      "https://docs.oracle.com/javase/specs/jls/se7/html/jls-3.html#jls-3.10.6">Escape
   *      Sequences for Character and String Literals</a>
   */
  public static char escape(final char ch) {
    final int i = Arrays.binarySearch(escapableChars, ch);
    if (i < 0)
      throw new IllegalArgumentException(ch + " is not an escapable character.");

    return escapedChars[i];
  }

  /**
   * Tests whether the specified character is escapable as per <a href=
   * "https://docs.oracle.com/javase/specs/jls/se7/html/jls-3.html#jls-3.10.6">Escape
   * Sequences for Character and String Literals</a>.
   *
   * @param ch The character to test.
   * @return {@code true} if the specified character is escapable.
   */
  public static boolean isEscapable(final char ch) {
    return Arrays.binarySearch(escapableChars, ch) > -1;
  }

  private Characters() {
  }
}