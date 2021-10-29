import com.sun.org.glassfish.gmbal.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DBthreadsSafeTest {
    private static String token;
    private static String depId;

    //调用token获取接口获取token
    @BeforeAll
    static void beforAll() {
        token = getToken.getToken();
    }


    @RepeatedTest(10)
    @Description("创建部门-深圳")
    @Execution(CONCURRENT)
    void createDep() throws InterruptedException {
        //线程安全校验,同时开启5个线程都插入同一条数据,跑20次,应该只有1次成功,其余失败
        //同一时刻,多个线程同时获取时间戳有可能取到相同的时间戳导致name相同,采用timestamp+threadId的方式可保证绝对的唯一
        String suffix = Thread.currentThread().getId() + getTimeStamp.getTimeStamp();
        String name = "create" + suffix;
        System.out.println(name);
        Response response = depApis.createDep(name, "1", token);
        assertEquals("0", response.path("errcode").toString());
    }


    @RepeatedTest(10)
    @Description("更新部门深圳-->成都")
    @Execution(CONCURRENT)
    void updateDep() throws InterruptedException {
        //更新前先创建
        //同一时刻,多个线程同时获取时间戳有可能取到相同的时间戳导致name相同,采用timestamp+threadId的方式可保证绝对的唯一
        String suffix = Thread.currentThread().getId() + "u" + getTimeStamp.getTimeStamp();
        String createName = "北京研发中心uc" + suffix;
        String updateName = "成都研发中心u" + suffix;
        Response createResponse = depApis.createDep(createName, "1", token);
        depId = createResponse.path("id") != null ? createResponse.path("id").toString() : null;
        Response response = depApis.updateDep(depId, updateName, token);
        assertEquals("0", response.path("errcode").toString());

    }

}