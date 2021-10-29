import com.sun.org.glassfish.gmbal.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class serviceTest {
    private static String token;
    private static String depId;

    //调用token获取接口获取token
    @BeforeAll
    static void beforAll() {
        token = getToken.getToken();
    }


    @BeforeEach
    @AfterEach
    void envClear() {
        //清理环境
        envClear.envClear("1", token);
    }


    @Test
    @Order(1)
    @Description("创建部门-深圳")
    void createDep() throws InterruptedException {
        Response response = depApis.createDep("深圳研发中心", "1", token);
        Thread.sleep(2000);
        assertEquals("0", response.path("errcode").toString());
    }

    @Test
    @Order(2)
    @Description("更新部门深圳-->成都")
    void updateDep() throws InterruptedException {
        //更新前先创建
        Response createResponse = depApis.createDep("深圳研发中心", "1", token);
        depId = createResponse.path("id") != null ? createResponse.path("id").toString() : null;
        Response response = depApis.updateDep(depId, "成都研发中心", token);
        Thread.sleep(2000);
        assertEquals("0", response.path("errcode").toString());
        //清理环境
//        depApis.envClear("1",token);

    }

    @Test
    @Order(3)
    @Description("查询部门列表")
    void listDeps() throws InterruptedException {
        depApis.createDep("深圳研发中心", "1", token);
        Response response = depApis.listDep("1", token);
        Thread.sleep(2000);
        assertEquals("0", response.path("errcode").toString());
    }

    @Test
    @Order(4)
    @Description("删除部门")
    void deleteDep() throws InterruptedException {
        Response createResponse = depApis.createDep("深圳研发中心", "1", token);
        depId = createResponse.path("id") != null ? createResponse.path("id").toString() : null;
        Response response = depApis.deleteDep(depId, token);
        Thread.sleep(2000);
        assertEquals("0", response.path("errcode").toString());
    }
}

