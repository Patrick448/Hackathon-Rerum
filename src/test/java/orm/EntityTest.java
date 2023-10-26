package orm;

import com.javax0.sourcebuddy.Fluent;
import odlAst.Node;
import odlLoader.ODLLoader;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import visitors.JavaGenODLVisitor;
import visitors.SQLGenODLVisitor;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import com.javax0.sourcebuddy.Compiler;
import com.javax0.sourcebuddy.Fluent;

import static org.junit.jupiter.api.Assertions.*;

class EntityTest {

    static Connection conn;

    @BeforeAll
    static void beforeAll() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/test";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "1234");
        Connection baseConnection = DriverManager.getConnection(url, props);
        conn = baseConnection;
    }

    void createTable(String className){
        Compiler.Loaded loaded;
        try {
            loaded = ODLLoader.loadClasses("teste_class.odl");
            Class<?> objClass = loaded.get("generatedodl." + className);
            Entity obj = (Entity)loaded.newInstance("generatedodl.C" + className);
            obj.create(conn);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @AfterAll
    static void afterAll() throws SQLException {
        //conn.close();
    }

    @BeforeEach
    void beforeEach() throws SQLException {

        Statement stmt = conn.createStatement();
        String create = "CREATE SCHEMA test;";
        String drop = "DROP SCHEMA IF EXISTS test;";
        stmt.executeUpdate(drop);
        stmt.executeUpdate(create);
    }

    @Test
    void shouldCreateTable(){
        Compiler.Loaded loaded;
        try {
            loaded = ODLLoader.loadClasses("teste_class.odl");
            Class<?> objClass = loaded.get("generatedodl." + "C");
            Entity obj = (Entity)loaded.newInstance("generatedodl." + "C");
            obj.create(conn);

            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM c;";
            stmt.execute(sql);
            return;

        } catch (Exception e) {
            fail(e);
            throw new RuntimeException(e);
        }



    }

    @Test
    void shouldInsert(){

    }

    @Test
    void shouldDelete(){

    }

    @Test
    void shouldUpdate(){

    }

    @Test
    void shouldSelectAll(){

    }

    @Test
    void shouldSelect(){

    }

}