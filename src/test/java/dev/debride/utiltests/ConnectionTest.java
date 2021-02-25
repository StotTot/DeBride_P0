package dev.debride.utiltests;

import dev.debride.utils.ConnectionUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.Map;

public class ConnectionTest {

    @Test
    void generates_connection(){

        Connection conn = ConnectionUtil.createConnection();
        System.out.println(conn);
        Assertions.assertNotNull(conn);
    }

    @Test
    void get_envrionmentvariable(){
        String env = System.getenv("CONN_DETAILS");
        System.out.println(env);
    }

}
