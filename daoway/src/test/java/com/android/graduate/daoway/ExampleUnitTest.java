package com.android.graduate.daoway;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
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

    @Test
    public void returnDate()  {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
      //  String now = sdf.format(new Date(1454428800));
        String now = sdf.format(new Date(1467353517000L));
        System.out.print(now);
    }
}