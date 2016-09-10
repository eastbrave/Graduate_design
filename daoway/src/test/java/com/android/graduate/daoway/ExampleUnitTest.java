package com.android.graduate.daoway;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void print() throws UnsupportedEncodingException {
        String encode = URLEncoder.encode("小时工", "GBK");
        System.out.print(encode);
    }
}