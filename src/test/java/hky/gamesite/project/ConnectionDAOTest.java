package hky.gamesite.project;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class ConnectionDAOTest {
	private static final Logger logger = LoggerFactory.getLogger(ConnectionDAOTest.class);
	@Autowired
	DataSource dataSource;

	@Test
	public void test() {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			logger.info("" + connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
