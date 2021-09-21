package poc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.OracleContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
class OracleTest {

    private static Logger LOG = LogManager.getLogger(OracleTest.class);

    static LocalDateTime start = LocalDateTime.now();
    static  {
        LOG.info("Starting Oracle");
    }

    // Will be shared between test methods
    @Container
    private static final OracleContainer oracle =
            new OracleContainer("wingnut/oracle-database:18.4.0-xe-prebuilt");

    @BeforeAll
    static void start() {
        assertTrue(oracle.isRunning());
        var stop = LocalDateTime.now();
        LOG.info("Oracle started");
        LOG.info("Starting Oracle Took: " + ChronoUnit.SECONDS.between(start, stop) + "s");
    }

    @AfterAll
    static void stop() {
        LOG.info("Finished");
    }

    @BeforeEach
    void testStart() {
        LOG.info("Starting test method");
    }

    @AfterEach
    void testStop() {
        LOG.info("Finished test method");
    }

    @Test
    void testOracle() throws SQLException {
        // Open a connection and run a dummy query
        try(Connection conn = DriverManager.getConnection(oracle.getJdbcUrl(), "system", "oracle");
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM DUAL")) {
            while (rs.next()) {
                // Using Oracle's default Dummy table/column to verify
                var valueOfDummyColumn = rs.getString("DUMMY");
                assertEquals("X", valueOfDummyColumn);
            }
        }
    }

}
