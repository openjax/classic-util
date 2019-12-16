/* Copyright (c) 2019 LibJ
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

package org.libj.util.primitive;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

import org.junit.Assert;
import org.junit.Test;
import org.libj.util.CollectionUtil;

@SuppressWarnings("cast")
public class Array<S>ListTest extends PrimitiveCollectionTest {
  @Test
  public void testModCound() {
    final Array<S>List list = new Array<S>List((<t>)0, (<t>)1, (<t>)2, (<t>)3, (<t>)4, (<t>)5, (<t>)6, (<t>)7, (<t>)8, (<t>)9);

    final Array<S>List sl1 = list.subList(1, 4);
    final Array<S>List sl2 = list.subList(5, 9);

    final Array<S>List sl11 = sl1.subList(1, 2);
    final Array<S>List sl12 = sl1.subList(2, 3);

    final Array<S>List sl111 = sl11.subList(0, 1);
    final Array<S>List sl121 = sl11.subList(0, 1);

    final Array<S>List sl21 = sl2.subList(1, 2);
    final Array<S>List sl22 = sl2.subList(2, 3);

    final Array<S>List sl211 = sl21.subList(0, 1);
    final Array<S>List sl221 = sl21.subList(0, 1);

    final Array<S>List slx1 = list.subList(2, 8);
    final Array<S>List slx2 = slx1.subList(1, 3);
    final Array<S>List slx3 = slx1.subList(3, 5);

    final Runnable test = () -> {
      assertEquals(list.modCount, sl1.modCount);
      assertEquals(list.modCount, sl2.modCount);
      assertEquals(list.modCount, sl11.modCount);
      assertEquals(list.modCount, sl12.modCount);
      assertEquals(list.modCount, sl111.modCount);
      assertEquals(list.modCount, sl121.modCount);
      assertEquals(list.modCount, sl21.modCount);
      assertEquals(list.modCount, sl22.modCount);
      assertEquals(list.modCount, sl211.modCount);
      assertEquals(list.modCount, sl221.modCount);
      assertEquals(list.modCount, slx1.modCount);
      assertEquals(list.modCount, slx2.modCount);
      assertEquals(list.modCount, slx3.modCount);
    };

    final Consumer<Array<S>List> consumer = list1 -> {
      list1.add((<t>)10);
      test.run();

      list1.removeIndex(0);
      test.run();

      final <S>ListIterator i = list1.listIterator();
      i.add((<t>)10);
      test.run();

      i.previous();
      i.remove();
      test.run();
    };

    consumer.accept(list);
    consumer.accept(sl1);
    consumer.accept(sl2);
    consumer.accept(sl11);
    consumer.accept(sl12);
    consumer.accept(sl111);
    consumer.accept(sl121);
    consumer.accept(sl21);
    consumer.accept(sl22);
    consumer.accept(sl211);
    consumer.accept(sl221);
    consumer.accept(slx1);
    consumer.accept(slx2);
    consumer.accept(slx3);
  }

  private static Array<S>List[] newSubLists() {
    final Array<S>List[] l = new Array<S>List[30];
    l[0] = new Array<S>List((<t>)0, (<t>)1, (<t>)2, (<t>)3, (<t>)4, (<t>)5, (<t>)6, (<t>)7, (<t>)8, (<t>)9, (<t>)10, (<t>)11, (<t>)12, (<t>)13, (<t>)14, (<t>)15, (<t>)16, (<t>)17, (<t>)18, (<t>)19);

    l[1] = l[0].subList(2, 11);
    l[2] = l[0].subList(11, 16);
    l[3] = l[0].subList(16, 18);
    l[4] = l[0].subList(18, 20);

    l[5] = l[0].subList(0, 5);
    l[6] = l[0].subList(5, 10);
    l[7] = l[0].subList(10, 15);
    l[8] = l[0].subList(15, 20);

    l[9] = l[5].subList(0, 2);
    l[10] = l[5].subList(2, 4);
    l[11] = l[6].subList(0, 5);
    l[12] = l[7].subList(1, 5);
    l[13] = l[8].subList(0, 4);
    l[14] = l[8].subList(5, 5);

    l[15] = l[9].subList(0, 1);
    l[16] = l[10].subList(1, 2);
    l[17] = l[11].subList(0, 0);
    l[18] = l[11].subList(1, 4);
    l[19] = l[12].subList(0, 4);
    l[20] = l[13].subList(1, 3);
    l[21] = l[13].subList(4, 4);

    l[22] = l[15].subList(0, 0);
    l[23] = l[16].subList(1, 1);
    l[24] = l[17].subList(0, 0);
    l[25] = l[18].subList(2, 3);
    l[26] = l[19].subList(0, 1);
    l[27] = l[19].subList(2, 2);
    l[28] = l[19].subList(3, 4);
    l[29] = l[20].subList(1, 2);

    return l;
  }

  private static void assertSubListsEaual(final Array<S>List[] ... lists) {
    for (int i = 1; i < lists.length; ++i) {
      final Array<S>List[] a = lists[i - 1];
      final Array<S>List[] b = lists[i];
      for (int j = 0; j < a.length; ++j)
        Assert.assertEquals((char)('a' + i - 1) + "[" + j + "] != " + (char)('a' + i) + "[" + j + "]: ", a[j], b[j]);
    }
  }

  @Test
  public void testSubLists() {
    final Array<S>List[] baseline = newSubLists();
    final Array<S>List[] a = newSubLists();
    final Array<S>List[] b = newSubLists();
    final Array<S>List[] c = newSubLists();

    assertSubListsEaual(a, b, c);

    a[0].add(0, (<t>)-1);
    b[5].add(0, (<t>)-1);
    c[9].add(0, (<t>)-1);
    assertSubListsEaual(a, b, c);

    a[0].removeIndex(0);
    b[5].removeIndex(0);
    c[9].removeIndex(0);
    assertSubListsEaual(a, b, c);
    assertSubListsEaual(baseline, a);

    a[0].addAll(2, new <t>[] {2, 2});
    b[1].addAll(0, new <t>[] {2, 2});
    c[10].addAll(0, new <t>[] {2, 2});
    assertSubListsEaual(a, b, c);

    a[0].removeIndex(2);
    a[0].removeIndex(2);
    b[1].removeIndex(0);
    b[1].removeIndex(0);
    c[5].removeIndex(2);
    c[5].removeIndex(2);
    assertSubListsEaual(a, b, c);
    assertSubListsEaual(baseline, a);

    a[0].addAll(8, Arrays.asList((<t>)8, (<t>)8));
    b[11].addAll(3, Arrays.asList((<t>)8, (<t>)8));
    c[25].addAll(0, Arrays.asList((<t>)8, (<t>)8));
    assertSubListsEaual(a, b, c);

    a[0].remove((<t>)8);
    a[0].remove((<t>)8);
    b[11].remove((<t>)8);
    b[11].remove((<t>)8);
    c[25].remove((<t>)8);
    c[25].remove((<t>)8);
    assertSubListsEaual(a, b, c);
    assertSubListsEaual(baseline, a);

    a[0].addAll(8, (<S>Collection)new Array<S>List((<t>)-8, (<t>)-8));
    b[11].addAll(3, (<S>Collection)new Array<S>List((<t>)-8, (<t>)-8));
    c[25].addAll(0, (<S>Collection)new Array<S>List((<t>)-8, (<t>)-8));
    assertSubListsEaual(a, b, c);

    a[0].removeAll((<t>)-8);
    b[11].removeAll((<t>)-8);
    c[25].removeAll((<t>)-8);
    assertSubListsEaual(a, b, c);
    assertSubListsEaual(baseline, a);

    a[21].add((<t>)99);
    assertEquals(99, a[0].get(19));
    assertEquals(99, a[8].get(4));
    assertEquals(5, a[13].size());

    a[21].remove((<t>)99);
    assertEquals(19, a[0].get(19));
    assertEquals(19, a[8].get(4));
    assertEquals(4, a[13].size());
    assertSubListsEaual(a, b, c);
    assertSubListsEaual(baseline, a);

    a[21].addAll(Arrays.asList((<t>)99, (<t>)99));
    assertEquals(99, a[0].get(19));
    assertEquals(99, a[0].get(20));
    assertEquals(99, a[8].get(4));
    assertEquals(99, a[8].get(5));
    assertEquals(6, a[13].size());

    a[21].removeAll((<t>)99);
    assertEquals(19, a[0].get(19));
    assertEquals(19, a[8].get(4));
    assertEquals(4, a[13].size());
    assertSubListsEaual(a, b, c);
    assertSubListsEaual(baseline, a);

    a[17].add((<t>)99);
    assertEquals(99, a[0].get(5));
    assertEquals(99, a[6].get(0));
    assertEquals(99, a[11].get(0));
    assertEquals(0, a[24].size());

    a[17].remove((<t>)99);
    assertEquals(5, a[0].get(5));
    assertEquals(5, a[6].get(0));
    assertEquals(5, a[11].get(0));
    assertEquals(0, a[24].size());
    assertSubListsEaual(a, b, c);
    assertSubListsEaual(baseline, a);

    final <S>ListIterator ia = a[0].listIterator(8);
    ia.add((<t>)99);
    final <S>ListIterator ib = b[6].listIterator(3);
    ib.add((<t>)99);
    final <S>ListIterator ic = c[25].listIterator(0);
    ic.add((<t>)99);
    assertSubListsEaual(a, b, c);

    ia.previous();
    ia.remove();
    ib.previous();
    ib.remove();
    ic.previous();
    ic.remove();
    assertSubListsEaual(a, b, c);
    assertSubListsEaual(baseline, a);

    a[0].clear();
    for (int i = 1; i < a.length; ++i)
      Assert.assertEquals("a[" + (i - 1) + "] != " + "a[" + i + "]: ", a[i - 1].size(), a[i].size());

    a[0].add((<t>)100);
    for (int i = 2; i < a.length; ++i)
      Assert.assertEquals("a[" + (i - 1) + "] != " + "a[" + i + "]: ", a[i - 1].size(), a[i].size());

    a[0].removeIndex(0);
    a[1].add((<t>)100);
    assertEquals(100, a[0].get(0));
    a[0].removeIndex(0);
    assertTrue(a[1].isEmpty());

    a[26].add((<t>)100);
    assertEquals(100, a[19].get(0));
    assertEquals(100, a[12].get(0));
    assertEquals(100, a[7].get(0));
    assertEquals(100, a[0].get(0));
    assertTrue(a[1].isEmpty());
    assertTrue(a[2].isEmpty());

    //  ----------------------------------------------------------------------------------------------------------------------------------
    // |  0     1     2     3     4     5     6     7     8     9     10     11     12     13     14     15     16     17     18     19  |
    //  ----------------------------------------------------------------------------------------------------------------------------------
    //             |                            1                         |                  2               |      3      |      4      |
    //              ------------------------------------------------------ ---------------------------------- ------------- -------------
    //
    //  ----------------------------- ----------------------------- ---------------------------------- ----------------------------------
    // |               5             |               6             |                 7                |                8                 |
    //  ----------------------------- ----------------------------- ---------------------------------- ----------------------------------
    // |     9     |     10    |     |              11             |      |            12             |            13             |      || 14
    //  ----------- -----------       -----------------------------        --------------------------- ---------------------------
    // |  15 |           |  16 |     || 17 |        18        |           |            19             |      |     20      |      || 21
    //  -----             -----             ------------------             ---------------------------        -------------
    // || 22                   || 23 || 24             |  25  |           |  26  |      || 27  |  28  |             |  29  |
    //                                                  ------             ------               ------               ------
  }

  @Test
  public void testList() {
    final Array<S>List list = new Array<S>List();
    testList(list);
  }

  @Test
  public void testSubList() {
    final Array<S>List list = new Array<S>List((<t>)-7, (<t>)-6, (<t>)-5, (<t>)-4, (<t>)-3, (<t>)-2, (<t>)-1);
    final Array<S>List subList = list.subList(3, 3);
    testList(subList);
    assertArrayEquals(new <t>[] {(<t>)-7, (<t>)-6, (<t>)-5, (<t>)4, (<t>)6, (<t>)7, (<t>)7, (<t>)10, (<t>)11, (<t>)99, (<t>)99, (<t>)-4, (<t>)-3, (<t>)-2, (<t>)-1}, list.toArray());

//    list.removeIndex(1);
//    assertArrayEquals(new <t>[] {4, 6, 7, 7, 10, 11, 99, 99}, subList.toArray());
//
//    list.removeIndex(4);
//    assertArrayEquals(new <t>[] {4, 7, 7, 10, 11, 99, 99}, subList.toArray());
//
//    list.removeIndex(10);
//    assertArrayEquals(new <t>[] {4, 7, 7, 10, 11, 99}, subList.toArray());
  }

  @Test
  public void testSubSubList() {
    final Array<S>List list = new Array<S>List((<t>)-7, (<t>)-6, (<t>)-5, (<t>)-4, (<t>)-3, (<t>)-2, (<t>)-1);
    final Array<S>List subList = list.subList(2, 5);
    try {
      subList.subList(4, 4);
      fail("Expected IndexOutOfBoundsException");
    }
    catch (final IndexOutOfBoundsException e) {
    }

    final Array<S>List subSubList = subList.subList(2, 2);
    testList(subSubList);
    assertArrayEquals(new <t>[] {(<t>)-5, (<t>)-4, (<t>)4, (<t>)6, (<t>)7, (<t>)7, (<t>)10, (<t>)11, (<t>)99, (<t>)99, (<t>)-3}, subList.toArray());
    assertArrayEquals(new <t>[] {(<t>)-7, (<t>)-6, (<t>)-5, (<t>)-4, (<t>)4, (<t>)6, (<t>)7, (<t>)7, (<t>)10, (<t>)11, (<t>)99, (<t>)99, (<t>)-3, (<t>)-2, (<t>)-1}, list.toArray());

    list.removeIndex(1);
    assertArrayEquals(new <t>[] {(<t>)-5, (<t>)-4, (<t>)4, (<t>)6, (<t>)7, (<t>)7, (<t>)10, (<t>)11, (<t>)99, (<t>)99, (<t>)-3}, subList.toArray());
    assertArrayEquals(new <t>[] {(<t>)4, (<t>)6, (<t>)7, (<t>)7, (<t>)10, (<t>)11, (<t>)99, (<t>)99}, subSubList.toArray());

    list.removeIndex(4);
    assertArrayEquals(new <t>[] {(<t>)-5, (<t>)-4, (<t>)4, (<t>)7, (<t>)7, (<t>)10, (<t>)11, (<t>)99, (<t>)99, (<t>)-3}, subList.toArray());
    assertArrayEquals(new <t>[] {(<t>)4, (<t>)7, (<t>)7, (<t>)10, (<t>)11, (<t>)99, (<t>)99}, subSubList.toArray());

    list.removeIndex(10);
    assertArrayEquals(new <t>[] {(<t>)-5, (<t>)-4, (<t>)4, (<t>)7, (<t>)7, (<t>)10, (<t>)11, (<t>)99, (<t>)99}, subList.toArray());
    assertArrayEquals(new <t>[] {(<t>)4, (<t>)7, (<t>)7, (<t>)10, (<t>)11, (<t>)99, (<t>)99}, subSubList.toArray());

    subList.removeIndex(4);
    assertArrayEquals(new <t>[] {(<t>)-7, (<t>)-5, (<t>)-4, (<t>)4, (<t>)7, (<t>)10, (<t>)11, (<t>)99, (<t>)99, (<t>)-2, (<t>)-1}, list.toArray());
    assertArrayEquals(new <t>[] {(<t>)-5, (<t>)-4, (<t>)4, (<t>)7, (<t>)10, (<t>)11, (<t>)99, (<t>)99}, subList.toArray());
    assertArrayEquals(new <t>[] {(<t>)4, (<t>)7, (<t>)10, (<t>)11, (<t>)99, (<t>)99}, subSubList.toArray());

    subList.removeIndex(7);
    assertArrayEquals(new <t>[] {(<t>)-7, (<t>)-5, (<t>)-4, (<t>)4, (<t>)7, (<t>)10, (<t>)11, (<t>)99, (<t>)-2, (<t>)-1}, list.toArray());
    assertArrayEquals(new <t>[] {(<t>)-5, (<t>)-4, (<t>)4, (<t>)7, (<t>)10, (<t>)11, (<t>)99}, subList.toArray());
    assertArrayEquals(new <t>[] {(<t>)4, (<t>)7, (<t>)10, (<t>)11, (<t>)99}, subSubList.toArray());

    subList.removeIndex(1);
    assertArrayEquals(new <t>[] {(<t>)-7, (<t>)-5, (<t>)4, (<t>)7, (<t>)10, (<t>)11, (<t>)99, (<t>)-2, (<t>)-1}, list.toArray());
    assertArrayEquals(new <t>[] {(<t>)-5, (<t>)4, (<t>)7, (<t>)10, (<t>)11, (<t>)99}, subList.toArray());
    assertArrayEquals(new <t>[] {(<t>)4, (<t>)7, (<t>)10, (<t>)11, (<t>)99}, subSubList.toArray());

    subSubList.removeIndex(2);
    assertArrayEquals(new <t>[] {(<t>)-7, (<t>)-5, (<t>)4, (<t>)7, (<t>)11, (<t>)99, (<t>)-2, (<t>)-1}, list.toArray());
    assertArrayEquals(new <t>[] {(<t>)-5, (<t>)4, (<t>)7, (<t>)11, (<t>)99}, subList.toArray());
    assertArrayEquals(new <t>[] {(<t>)4, (<t>)7, (<t>)11, (<t>)99}, subSubList.toArray());

    Assert.assertEquals(list, list.clone());
    Assert.assertEquals(subList, subList.clone());
    Assert.assertEquals(subSubList, subSubList.clone());
  }

  public void testList(final Array<S>List list) {
    assertTrue(list.isEmpty());
    list.add((<t>)1);
    list.add((<t>)2);
    list.add((<t>)3);
    list.add((<t>)4);
    list.add((<t>)5);
    assertEquals(5, list.size());
    assertEquals(1, list.get(0));
    assertEquals(2, list.get(1));
    assertEquals(3, list.get(2));
    assertEquals(4, list.get(3));
    assertEquals(5, list.get(4));

    try {
      list.get(5);
      fail("Expected IndexOutOfBoundsException");
    }
    catch (final IndexOutOfBoundsException e) {
    }

    list.addAll(new <t>[] {6, 7, 8, 9, 10});
    assertEquals(10, list.size());
    assertEquals(6, list.get(5));
    assertEquals(7, list.get(6));
    assertEquals(8, list.get(7));
    assertEquals(9, list.get(8));
    assertEquals(10, list.get(9));

    list.addAll(3, new <t>[] {11, 12, 13});
    assertEquals(13, list.size());
    assertEquals(2, list.get(1));
    assertEquals(3, list.get(2));
    assertEquals(11, list.get(3));
    assertEquals(12, list.get(4));
    assertEquals(13, list.get(5));
    assertEquals(4, list.get(6));

    list.set(1, (<t>)7);
    assertEquals(13, list.size());
    assertEquals(7, list.get(1));

    assertEquals(1, list.indexOf((<t>)7));
    assertEquals(9, list.lastIndexOf((<t>)7));

    list.add(2, (<t>)99);
    list.add(7, (<t>)99);
    list.add(13, (<t>)99);
    assertEquals(16, list.size());
    assertArrayEquals(new <t>[] {1, 7, 99, 3, 11, 12, 13, 99, 4, 5, 6, 7, 8, 99, 9, 10}, list.toArray(new <t>[list.size()]));
    Assert.assertEquals(list, list.clone());

    list.removeIndex(3);
    assertArrayEquals(new <t>[] {1, 7, 99, 11, 12, 13, 99, 4, 5, 6, 7, 8, 99, 9, 10}, list.toArray(new <t>[0]));

    list.remove((<t>)99);
    assertArrayEquals(new <t>[] {1, 7, 11, 12, 13, 99, 4, 5, 6, 7, 8, 99, 9, 10}, list.toArray(new <t>[list.size()]));

    assertFalse(list.contains((<t>)0));
    list.sort();
    Assert.assertArrayEquals(new <T>[] {(<t>)1, (<t>)4, (<t>)5, (<t>)6, (<t>)7, (<t>)7, (<t>)8, (<t>)9, (<t>)10, (<t>)11, (<t>)12, (<t>)13, (<t>)99, (<t>)99}, list.toArray(new <T>[list.size()]));

    assertTrue(list.removeAll(new <t>[] {5, 9, 12}));
    assertArrayEquals(new <t>[] {1, 4, 6, 7, 7, 8, 10, 11, 13, 99, 99}, list.toArray(new <t>[list.size()]));

    assertTrue(list.removeAll(Arrays.asList((<t>)1, (<t>)8, (<t>)13)));
    assertArrayEquals(new <t>[] {4, 6, 7, 7, 10, 11, 99, 99}, list.toArray(new <t>[0]));

    assertTrue(list.containsAll((<t>)4, (<t>)10, (<t>)99));
    assertTrue(list.containsAll(Arrays.asList((<t>)4, (<t>)10, (<t>)99)));
    assertTrue(list.containsAll(new Array<S>List((<t>)4, (<t>)10, (<t>)99)));
  }

  @Test
  public void testIterator() {
    final <t>[] values = {7, 3, 5, 4, 6, 9, 1, 8, 0};
    final Array<S>List list = new Array<S>List(values);
    testIterator(values, list);
  }

  @Test
  public void testIteratorSubList() {
    final <t>[] values = {7, 3, 5, 4, 6, 9, 1, 8, 0};
    final Array<S>List list = new Array<S>List((<t>)1, (<t>)2, (<t>)3);
    list.addAll(values);
    list.addAll(new <t>[] {4, 5, 6});
    testIterator(values, list.subList(3, list.size() - 3));
  }

  @Test
  public void testIteratorSubListSubList() {
    final <t>[] values = {7, 3, 5, 4, 6, 9, 1, 8, 0};
    final Array<S>List list = new Array<S>List((<t>)1, (<t>)2, (<t>)3, (<t>)4, (<t>)5, (<t>)6);
    list.addAll(values);
    list.addAll(new <t>[] {9, 10, 11, 12, 13, 14});
    final Array<S>List subList = list.subList(3, list.size() - 3);
    testIterator(values, subList.subList(3, subList.size() - 3));
  }

  public void testIterator(final <t>[] values, final Array<S>List list) {
    assertEquals(values.length, list.size());
    <S>Iterator iterator = list.iterator();
    for (int i = 0; i < values.length; ++i)
      assertEquals(values[i], iterator.next());

    assertFalse(iterator.hasNext());
    try {
      iterator.next();
      fail("Expected NoSuchElementException");
    }
    catch (final NoSuchElementException e) {
    }

    iterator = list.iterator();
    assertEquals(7, iterator.next());
    assertEquals(3, iterator.next());
    iterator.remove();
    assertEquals(5, iterator.next());
    assertEquals(4, iterator.next());
    iterator.remove();
    assertEquals(6, iterator.next());
    assertEquals(9, iterator.next());
    iterator.remove();
    assertEquals(1, iterator.next());
    assertEquals(8, iterator.next());
    iterator.remove();
    assertEquals(5, list.size());
    assertArrayEquals(new <t>[] {7, 5, 6, 1, 0}, list.toArray(new <t>[0]));
  }

  @Test
  public void testListIterator() {
    final <t>[] values = {7, 3, 5, 4, 6, 9, 1, 8, 0};
    final Array<S>List list = new Array<S>List(values);
    testListIterator(values, list);
  }

  @Test
  public void testListIteratorSubList() {
    final <t>[] values = {7, 3, 5, 4, 6, 9, 1, 8, 0};
    final Array<S>List list = new Array<S>List((<t>)1, (<t>)2, (<t>)3);
    list.addAll(values);
    list.addAll(new <t>[] {4, 5, 6});
    testListIterator(values, list.subList(3, list.size() - 3));
  }

  @Test
  public void testListIteratorSubListSubList() {
    final <t>[] values = {7, 3, 5, 4, 6, 9, 1, 8, 0};
    final Array<S>List list = new Array<S>List((<t>)1, (<t>)2, (<t>)3, (<t>)4, (<t>)5, (<t>)6);
    list.addAll(values);
    list.addAll(new <t>[] {9, 10, 11, 12, 13, 14});
    final Array<S>List subList = list.subList(3, list.size() - 3);
    testListIterator(values, subList.subList(3, subList.size() - 3));
  }

  public void testListIterator(final <t>[] values, final Array<S>List list) {
    <S>ListIterator iterator = list.listIterator();
    for (int i = 0; i < values.length; ++i) {
      assertEquals(i, iterator.nextIndex());
      assertEquals(values[i], iterator.next());
    }

    assertFalse(iterator.hasNext());
    try {
      assertEquals(0, iterator.next());
      fail("Expected NoSuchElementException");
    }
    catch (final NoSuchElementException e) {
    }

    iterator = list.listIterator(values.length);
    for (int i = values.length - 1; i >= 0; --i) {
      assertEquals(i, iterator.previousIndex());
      assertEquals(values[i], iterator.previous());
    }

    assertFalse(iterator.hasPrevious());
    iterator = list.listIterator(4);
    assertEquals(6, iterator.next());
    iterator.remove();
    try {
      iterator.remove();
      fail("Expected IllegalStateException");
    }
    catch (final IllegalStateException e) {
    }
    assertEquals(4, iterator.previous());
    iterator.remove();
    try {
      iterator.remove();
      fail("Expected IllegalStateException");
    }
    catch (final IllegalStateException e) {
    }
    assertEquals(5, iterator.previous());
    assertEquals(3, iterator.previous());
    iterator.remove();
    assertEquals(5, iterator.next());
    assertEquals(9, iterator.next());
    assertEquals(1, iterator.next());
    assertEquals(8, iterator.next());
    iterator.remove();
    assertTrue(iterator.hasNext());
    assertArrayEquals(new <t>[] {7, 5, 9, 1, 0}, list.toArray(new <t>[0]));

    iterator.add((<t>)3);
    assertArrayEquals(new <t>[] {7, 5, 9, 1, 3, 0}, list.toArray(new <t>[0]));
    try {
      iterator.remove();
      fail("Expected IllegalStateException");
    }
    catch (final IllegalStateException e) {
    }
    assertEquals(0, iterator.next());
    assertFalse(iterator.hasNext());
    iterator.remove();
    assertArrayEquals(new <t>[] {7, 5, 9, 1, 3}, list.toArray(new <t>[0]));
  }

  private final Array<S>List list = new Array<S>List();

  @Test
  public void shouldReportEmpty() {
    assertEquals(0, list.size());
    assertTrue(list.isEmpty());
  }

  @Test
  public void shouldAdd() {
    list.add((<t>)7);

    assertEquals(1, list.size());
    assertEquals(7, list.get(0));
  }

  @Test
  public void shouldAddAtIndex() {
    final int count = 20;
    for (<t> i = 0; i < count; ++i)
      list.add(i);

    list.add(10, (<t>)111);

    assertEquals(count + 1, list.size());
    assertEquals(111, list.get(10));
    assertEquals(count - 1, list.get(count));
  }

  @Test
  public void shouldAddValueAtIndexWithNearlyFullCapacity() {
    final int count = Array<S>List.DEFAULT_INITIAL_CAPACITY - 1;
    final <t> value = count + 1;
    for (<t> i = 0; i < count; ++i)
      list.add(i);

    list.add(0, value);

    assertEquals(count + 1, list.size());
    assertEquals(value, list.get(0));
    assertEquals(count - 1, list.get(count));
  }

  @Test
  public void shouldSetInt() {
    list.add((<t>)7);
    list.set(0, (<t>)8);

    assertEquals(1, list.size());
    assertEquals(8, list.get(0));
  }

  @Test
  public void shouldContainCorrectValues() {
    final int count = 20;
    for (<t> i = 0; i < count; ++i)
      list.add(i);

    for (<t> i = 0; i < count; ++i)
      assertTrue(list.contains(i));

    assertFalse(list.contains((<t>)-1));
    assertFalse(list.contains((<t>)20));
  }

  @Test
  public void shouldRemoveAtIndexForListLengthOne() {
    list.add((<t>)1);

    assertEquals(1, list.removeIndex(0));
    assertTrue(list.isEmpty());
  }

  @Test
  public void shouldRemoveAtIndex() {
    final int count = 20;
    for (int i = 0; i < count; ++i)
      list.add((<t>)i);

    assertEquals(10, list.removeIndex(10));

    assertEquals(count - 1, list.size());
    assertEquals(11, list.get(10));
  }

  @Test
  public void shouldRemoveByValue() {
    final int count = 20;
    for (<t> i = 0; i < count; ++i)
      list.add((<t>)(i * 10));

    assertTrue(list.remove((<t>)10));

    assertEquals(count - 1, list.size());
    assertEquals(20, list.get(1));
  }

//  @Test
//  public void shouldForEachOrderedInt() {
//    final List<<T>> expected = new ArrayList<>();
//    for (int i = 0; i < 20; ++i)
//      expected.add((<t>)i);
//
//    list.addAll(expected);
//
//    final List<<T>> actual = new ArrayList<>();
//    list.stream().forEach(actual::add);
//
//    Assert.assertEquals(expected, actual);
//  }
//
//  @Test
//  public void shouldCreateObjectRefArray() {
//    final int count = 20;
//    final List<<T>> expected = new ArrayList<>();
//    for (int i = 0; i < count; ++i)
//      expected.add((<t>)i);
//
//    list.addAll(expected);
//
//    Assert.assertArrayEquals(expected.toArray(), list.toArray(new <T>[count]));
//    Assert.assertArrayEquals(expected.toArray(), list.toArray(new <T>[0]));
//    assertArrayEquals(expected.stream().mapTo<T>(i -> i).toArray(), list.toArray());
//  }

  @Test
  public void shouldCreateIntArray() {
    final int count = 20;
    final <t>[] expected = new <t>[count];
    for (int i = 0; i < count; ++i) {
      list.add((<t>)i);
      expected[i] = (<t>)i;
    }

    assertArrayEquals(expected, list.toArray());

    final <t>[] copy = new <t>[count];
    final <t>[] result = list.toArray(copy);

    assertSame(copy, result);
    assertArrayEquals(expected, result);
  }

  @Test
  public void shouldCreate<T>Array() {
    final int count = 20;
    final <T>[] expected = new <T>[count];
    for (int i = 0; i < count; ++i) {
      list.add((<t>)i);
      expected[i] = (<t>)i;
    }

    final <T>[] integers = list.toArray(new <T>[0]);
    Assert.assertEquals(expected.getClass(), integers.getClass());
    Assert.assertArrayEquals(expected, integers);
  }

  @Test
  public void shouldPushAndThenPopInOrder() {
    final int count = 7;
    for (<t> i = 0; i < count; ++i)
      list.push(i);

    for (int i = count - 1; i >= 0; --i)
      assertEquals(i, list.pop());
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void shouldThrowExceptionWhenPoppingEmptyList() {
    list.pop();
  }

  @Test
  public void shouldGenerateStringRepresentation() {
    final <t>[] testEntries = new <t>[] {(<t>)65, (<t>)68, (<t>)83, (<t>)104, (<t>)111, (<t>)75, (<t>)83, (<t>)97};
    list.addAll(testEntries);
    final ArrayList<<T>> expected = new ArrayList<>(testEntries.length);
    for (final <t> testEntry : testEntries)
      expected.add(testEntry);

    Assert.assertEquals(expected.toString(), list.toString());
  }
}