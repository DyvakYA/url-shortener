package com.dyvak.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LongUrlValidatorTest {

    private final static Logger log = LoggerFactory.getLogger(LongUrlValidatorTest.class);

    @Autowired
    private LongUrlValidator longUrlValidator;

    @Test
    public void testUrlIsValid_wehnUrlIsCorrect() {
        boolean result = longUrlValidator.isValid("http://google.com.ua");
        assertTrue(result);
    }

    @Test
    public void testUrlIsValid_whenUrlIsIncorrect() {
        boolean result = longUrlValidator.isValid("qweqweq");
        assertFalse(result);
    }

    @Test
    public void testUrlIsValid_whenUrlIsMoreIncorrect() {
        boolean result = longUrlValidator.isValid("htttp:/111");
        log.info(String.valueOf(result));
        assertFalse(result);
    }
}
