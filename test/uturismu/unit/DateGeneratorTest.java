package uturismu.unit;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;

import uturismu.bean.util.DateGenerator;

public class DateGeneratorTest {

	@Test
	public void checkDateGenerator() {
		assertThat(DateGenerator.getDays().size(), is(equalTo(31)));
	}
	
}
