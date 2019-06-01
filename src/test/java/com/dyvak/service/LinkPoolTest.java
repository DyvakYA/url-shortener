package com.dyvak.service;

import com.dyvak.entity.DynamicLink;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LinkPoolTest {

    @Autowired
    private DynamicLinkPool linkPool;

    @Test
    public void givenMultiThread_whenAddOnlyThreeElement() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 1000; i++) {
            service.submit(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 3; j++) {
                        DynamicLink dynamicLink = DynamicLink.builder()
                                .kind("test")
                                .id("Test " + j)
                                .longUrl("Test " + j)
                                .build();

                        linkPool.put(dynamicLink);
                    }
                }
            });
        }
        service.awaitTermination(1000, TimeUnit.MILLISECONDS);

        assertEquals(3, linkPool.size());
    }

    @Test
    public void givenDynamicLink_whenPutToDynamicLinkPool() {
        DynamicLink dynamicLink = DynamicLink.builder()
                .kind("test")
                .id("Test")
                .longUrl("Test")
                .build();

        linkPool.put(dynamicLink);

        assertEquals(1, linkPool.size());
    }

    @After
    public void destroy() {
        linkPool.clear();
    }
}
