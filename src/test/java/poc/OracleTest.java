package poc;

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
public class OracleTest {

    static LocalDateTime start = LocalDateTime.now();
    static  {
        System.out.println("Starting Oracle: " + start);
    }

    // Will be shared between test methods
    @Container
    private static final OracleContainer oracle =
            new OracleContainer("wingnut/oracle-database:18.4.0-xe-prebuilt");

    @BeforeAll
    static void start() {
        assertTrue(oracle.isRunning());
        var stop = LocalDateTime.now();
        System.out.println("Oracle started: " + stop);
        System.out.println("Took: " + ChronoUnit.SECONDS.between(start, stop) + "s");
    }

    @AfterAll
    static void stop() {
        System.out.println("Finished: " + LocalDateTime.now());
    }

    @BeforeEach
    void testStart() {
        System.out.println("Starting test method: " + LocalDateTime.now());
    }

    @AfterEach
    void testStop() {
        System.out.println("Finished test method: " + LocalDateTime.now());
    }

    @Test
    public void testOracle() throws SQLException {
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
