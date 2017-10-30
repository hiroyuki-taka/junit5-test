import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public interface JUnit4Rule {

    Logger logger = LoggerFactory.getLogger(JUnit4Rule.class);
    // 隠蔽できないのでいまいち
    Map<String, Object> __objectMap = new HashMap<>();

    @BeforeEach
    default void __before() {
        logger.debug("interface - beforeEach");
        __objectMap.put("lib", new Lib());
    }

    default Lib getLib() {
        return (Lib) __objectMap.get("lib");
    }
}
