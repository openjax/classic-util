/* Copyright (c) 2017 lib4j
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

package org.lib4j.util;

import java.nio.charset.Charset;

import org.junit.Assert;
import org.junit.Test;
import org.lib4j.util.Diff.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiffTest {
  private static final Logger logger = LoggerFactory.getLogger(DiffTest.class);
  private static final Charset charset = Charset.forName("UTF-8");

  private static void assertDiff(final String source, final String target) {
    final Diff diff = new Diff(target, source);
    final String patched = diff.patch(target);
    Assert.assertEquals(source, patched);

    final byte[] encoded = diff.toBytes();
    final Diff decodedDiff = Diff.decode(encoded);
    final String decodedPatched = decodedDiff.patch(target);
    Assert.assertArrayEquals(source.getBytes(charset), decodedPatched.getBytes(charset));
  }

  @Test
  public void test() {
    assertDiff("http://www.w3.org/2001/XMLSchema", "org.w3._2001.xmlschema");
    assertDiff("http://java.sun.com/xml/ns/j2ee", "com.sun.java.xml.ns.j2ee");
    assertDiff("http://openuri.org/nameworld", "org.openuri.nameworld");
    assertDiff("http://schemas.xmlsoap.org/soap/envelope/", "org.xmlsoap.schemas.soap.envelope");
    assertDiff("http://www.safris.com/schema/test", "com.safris.schema.test");
    assertDiff("http://www.w3.org/1999/xhtml", "org.w3._1999.xhtml");
    assertDiff("http://www.w3.org/1999/xlink", "org.w3._1999.xlink");
    assertDiff("http://www.w3.org/2000/09/xmldsig#", "org.w3._2000._09.xmldsig");
    assertDiff("http://www.w3.org/2001/04/xmlenc#", "org.w3._2001._04.xmlenc");
    assertDiff("http://www.w3.org/2001/10/xml-exc-c14n#", "org.w3._2001._10.xml_exc_c14n");
    assertDiff("http://www.w3.org/XML/1998/namespace", "org.w3.xml._1998.namespace");
    assertDiff("http://xml.safris.org/schema/binding/test/unit/attributes.xsd", "org.safris.xml.schema.binding.test.unit.attributes");
    assertDiff("http://xml.safris.org/schema/binding/test/unit/complexTypes.xsd", "org.safris.xml.schema.binding.test.unit.complextypes");
    assertDiff("http://xml.safris.org/schema/binding/test/unit/everything.xsd", "org.safris.xml.schema.binding.test.unit.everything");
    assertDiff("http://xml.safris.org/schema/binding/test/unit/lists.xsd", "org.safris.xml.schema.binding.test.unit.lists");
    assertDiff("http://xml.safris.org/schema/binding/test/unit/mixed.xsd", "org.safris.xml.schema.binding.test.unit.mixed");
    assertDiff("http://xml.safris.org/schema/binding/test/unit/namespace.xsd", "org.safris.xml.schema.binding.test.unit.namespace");
    assertDiff("http://xml.safris.org/schema/binding/test/unit/simpleTypes.xsd", "org.safris.xml.schema.binding.test.unit.simpletypes");
    assertDiff("http://xml.safris.org/schema/binding/test/unit/types.xsd", "org.safris.xml.schema.binding.test.unit.types");
    assertDiff("http://xml.safris.org/schema/binding/dbb.xsd", "org.safris.xml.schema.binding.dbb");
    assertDiff("http://xml.safris.org/toolkit/binding/manifest.xsd", "org.safris.xml.toolkit.binding.manifest");
    assertDiff("http://xml.safris.org/toolkit/binding/test/maven.xsd", "org.safris.xml.toolkit.binding.test.maven");
    assertDiff("http://xml.safris.org/toolkit/binding/tutorial/invoice.xsd", "org.safris.xml.toolkit.binding.tutorial.invoice");
    assertDiff("http://xml.safris.org/toolkit/sample/binding/any.xsd", "org.safris.xml.toolkit.sample.binding.any");
    assertDiff("http://xml.safris.org/toolkit/sample/binding/any.xsd", "org.safris.xml.toolkit.sample.binding.any");
    assertDiff("http://xml.safris.org/toolkit/sample/binding/enums.xsd", "org.safris.xml.toolkit.sample.binding.enums");
    assertDiff("http://xml.safris.org/toolkit/sample/binding/enums.xsd", "org.safris.xml.toolkit.sample.binding.enums");
    assertDiff("http://xml.safris.org/toolkit/sample/binding/simple.xsd", "org.safris.xml.toolkit.sample.binding.simple");
    assertDiff("http://xml.safris.org/toolkit/sample/binding/simple.xsd", "org.safris.xml.toolkit.sample.binding.simple");
    assertDiff("http://xml.safris.org/toolkit/sample/binding/xsitype.xsd", "org.safris.xml.toolkit.sample.binding.xsitype");
    assertDiff("http://xml.safris.org/toolkit/sample/binding/xsitype.xsd", "org.safris.xml.toolkit.sample.binding.xsitype");
    assertDiff("http://xml.safris.org/toolkit/tutorial/binding/beginner/invoice.xsd", "org.safris.xml.toolkit.tutorial.binding.beginner.invoice");
    assertDiff("test-namespace-targetNamespace", "test_namespace_targetnamespace");
    assertDiff("urn:aol:liberty:config", "aol_liberty_config");
    assertDiff("urn:aol:liberty:config:", "aol_liberty_config");
    assertDiff("urn:berkeley:safris:game:chess", "berkeley_safris_game_chess");
    assertDiff("urn:liberty:ac:2003-08", "liberty_ac_2003_08");
    assertDiff("urn:liberty:ac:2004-12", "liberty_ac_2004_12");
    assertDiff("urn:liberty:disco:2003-08", "liberty_disco_2003_08");
    assertDiff("urn:liberty:id-sis-pp:2003-08", "liberty_id_sis_pp_2003_08");
    assertDiff("urn:liberty:iff:2003-08", "liberty_iff_2003_08");
    assertDiff("urn:liberty:metadata:2003-08", "liberty_metadata_2003_08");
    assertDiff("urn:liberty:sb:2003-08", "liberty_sb_2003_08");
    assertDiff("urn:oasis:names:tc:SAML:1.0:assertion", "oasis_names_tc_saml_1_0_assertion");
    assertDiff("urn:oasis:names:tc:SAML:1.0:protocol", "oasis_names_tc_saml_1_0_protocol");
    assertDiff("http://www.safris.com/schema/testtwo", "com.safris.schema.testtwo");
  }

  @Test
  public void diff() {
    final String a = "org.safris.xml.schema.binding.test.unit.complextypes";
    final String b = "http://org.safris.xml/schema/binding/test/unit/complexTypes.xsd";
    final Diff diff = new Diff(a, b);
    final String t = diff.patch(a);
    logger.info(t.toString());
  }

  @Test
  public void diff2() {
    final String a = "org.safris.xml.schema.binding.test.unit.complextypes";
    final String b = "http://org.safris.xml/schema/binding/test/unit/complexTypes.xsd";
    final Diff diff = new Diff(a, b);
    final int maxLength = Math.max(a.length(), b.length());
    final int bits = (int)(1 + Math.log(maxLength) / Math.log(2));
    logger.info("LengthSize: " + bits);
    for (final Mod mod : diff.getMods())
      logger.info(mod.toString());
  }
}