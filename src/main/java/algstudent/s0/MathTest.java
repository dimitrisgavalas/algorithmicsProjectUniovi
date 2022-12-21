package algstudent.s0;

import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MathTest {
	private static Math math;
	private static Logger log = LoggerFactory.getLogger(MathTest.class);
	   
    @BeforeClass
    public static void initialize() {
        math = new Math();
        log.info("Testing student project");
    }

	@Test
	public void test2Plus3Equals5() {
		assertEquals(5, math.sum(2, 3));
	}

}
