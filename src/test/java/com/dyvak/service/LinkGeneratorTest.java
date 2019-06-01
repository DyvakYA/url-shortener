package com.dyvak.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class LinkGeneratorTest {

    @Autowired
    private LinkGenerator linkGenerator;

    @Test
    public void testLengthOfGeneratedString() {
        String result = linkGenerator.generate();
        assertEquals(result.length(), 6);
    }
}
