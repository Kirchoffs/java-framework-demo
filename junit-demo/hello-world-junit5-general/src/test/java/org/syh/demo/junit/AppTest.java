package org.syh.demo.junit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest {
    @Test
    public void getUniqueIdForTest(TestInfo testInfo) {
        String uniqueId = testInfo.getTestClass().get().getName() + "#" + testInfo.getTestMethod().get().getName();
        System.out.println("Unique ID: " + uniqueId);
        assertTrue(uniqueId != null && !uniqueId.isEmpty());
    }
}
