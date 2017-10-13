import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NestedTest {

    private Lib lib;
    private static Logger logger;

    @BeforeAll
    static void setup() {
        logger = LoggerFactory.getLogger("NestedTest");
        logger.debug("static initialized");
    }

    @BeforeEach
    void init() {
        lib = new Lib();
        logger.debug("outer initialized");
    }

    /**
     * junit4まで
     */
    @Test
    void testPlus_int() {
        assertEquals(4, lib.plus(3, 1));
    }

    @Test
    void testPlus_double() {
        assertEquals(4.3, lib.plus(3.2, 1.1), 0.000001);
    }

    // ------------------------------------------------------------------------------------------
    /**
     * Nestedを使う
     */
    @Nested
    class Integer {
        @BeforeEach
        void init() {
            logger.debug("inner(int) initialzed");
        }

        @Test
        void testPlus() {
            assertEquals(4, lib.plus(3, 1));
        }
    }

    @Nested
    class Double {
        @BeforeEach
        void init() {
            logger.debug("inner(double) initialzed");
        }

        @Test
        void testPlus() {
            assertEquals(4.3, lib.plus(3.2, 1.1), 0.000001);
        }
    }



}