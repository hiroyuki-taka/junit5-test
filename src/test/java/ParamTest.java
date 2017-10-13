import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParamTest {

    Lib lib;
    static Logger logger;

    @BeforeAll
    static void setup() {
        logger = LoggerFactory.getLogger("ParameterizedTest");
        logger.debug("static initialized");
    }

    @BeforeEach
    void init() {
        lib = new Lib();
        logger.debug("outer initialized");
    }

    static Stream<Arguments> testPlusValues() {
        return Stream.of(
                Arguments.of(1, 3, 4),
                Arguments.of(2, 8, 10)
        );
    }

    /**
     * パラメータを用いたテスト, streamを返すメソッドを使う
     * @param a 引数1
     * @param b 引数2
     * @param ans 答え
     */
    @ParameterizedTest()
    @MethodSource("testPlusValues")
    void testPlus_stream(int a, int b, int ans) {
        assertEquals(ans, lib.plus(a, b));
    }

    /**
     * パラメータを用いたテスト, csv形式テキストを使う
     * @param a 引数1
     * @param b 引数2
     * @param ans 答え
     */
    @ParameterizedTest()
    @CsvSource({"7, 1, 8", "11, 2, 13", "3, 3, 6"})
    void testPlus_csv(int a, int b, int ans) {
        assertEquals(ans, lib.plus(a, b));
    }

    /**
     * パラメータを用いたテスト, csvファイルを使う
     * @param a 引数1
     * @param b 引数2
     * @param ans 答え
     */
    @ParameterizedTest()
    @CsvFileSource(resources = "/paramtest_csvfile.csv")
    void testPlus_csvfile(int a, int b, int ans) {
        assertEquals(ans, lib.plus(a, b));
    }
}
