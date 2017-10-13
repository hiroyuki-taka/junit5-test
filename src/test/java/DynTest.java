import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DynTest {

    private Lib lib;
    private static Logger logger;

    @BeforeAll
    static void setup() {
        logger = LoggerFactory.getLogger("DynamicTest");
        logger.debug("static initialized");
    }

    @BeforeEach
    void init() {
        lib = new Lib();
        logger.debug("outer initialized");
    }


    @TestFactory
    Stream<DynamicTest> testFactory() {
        Random r = new Random();

        return IntStream.range(1, 10).mapToObj(i -> {
            double a = r.nextDouble() * 10.0,
                    b = r.nextDouble() * 10.0,
                    answer = a + b;

            return DynamicTest.dynamicTest(String.format("[%d] plusTest - %f + %f", i, a, b), () -> {
                assertEquals(answer, lib.plus(a, b), 0.00001);
            });
        });
    }
}
