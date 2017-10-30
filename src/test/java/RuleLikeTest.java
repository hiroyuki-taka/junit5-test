import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * junit4のruleっぽいことをやってみる
 */
class RuleLikeTest implements JUnit4Rule {

    @Test
    void testRule() {
        Lib lib = getLib();
        assertEquals(4L, lib.plus(3, 1));
    }
}
