import com.sun.org.glassfish.gmbal.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class threadsSafeTest {
    private static String token;
    private static String depId;

    //调用token获取接口获取token
    @BeforeAll
    static void beforAll() {
        token = getToken.getToken();
    }


    @RepeatedTest(20)
    @Description("创建部门-深圳")
    @Execution(ExecutionMode.CONCURRENT)
    void createDep() throws InterruptedException {
        //线程安全校验,同时开启5个线程都插入同一条数据,跑20次,应该只有1次成功,其余失败
        Response response = depApis.createDep("深圳研发中心", "1", token);
        Thread.sleep(2000);
        assertEquals("0", response.path("errcode").toString());
    }


}

