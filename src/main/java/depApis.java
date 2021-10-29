import io.restassured.response.Response;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class depApis {

    public static Response createDep(String name, String parentId, String token) {
        //创建部门
//        String body = "{\n" +
//                "   \"name\": \"" + name + "\",\n" +
//                "   \"name_en\": \"RDGZ" + getTimeStamp.getTimeStamp() + "\",\n" +
//                "   \"parentid\": " + parentId + "\n" +
//                "   \"order\": 1\n" +
//                "}";

        String body="{\n" +
                "   \"name\": \""+name+"\",\n" +
                "   \"name_en\": \""+getTimeStamp.getTimeStamp()+"\",\n" +
                "   \"parentid\": 1,\n" +
                "   \"order\": 1\n" +
                "}";
        Response response = given()
                .contentType("application/json")
                .body(body)
                .when()
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=" + token)
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();
        return response;
    }


    public static Response updateDep(String updateDepId, String updateName, String token) {
        //更新部门信息
//        String body = "{\n" +
//                "   \"id\": " + updateDepId + ",\n" +
//                "   \"name\": \"" + updateName + "\",\n" +
//                "   \"parentid\": 1\n" +
//                "}";

        String body="{\n" +
                "   \"id\": "+updateDepId+",\n" +
                "   \"name\": \""+updateName +
                "   \"name_en\": \""+getTimeStamp.getTimeStamp()+"\",\n" +
                "   \"parentid\": 1\n" +
                "}";
        Response response = given()
                .when()
                .contentType("application/json")
                .body(body)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token=" + token)
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();
        return response;
    }

    public static Response listDep(String depId, String token) {
        //查询部门列表
        Response response = given()
                .when()
                .contentType("application/json")
                .param("id", depId)
                .param("access_token", token)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then().log().all()
                .statusCode(200)
                .extract().response();
        return response;
    }

    public static Response deleteDep(String depId, String token) {
        //删除部门
        Response response = given()
                .when()
                .contentType("application/json")
                .param("access_token", token)
                .param("id", depId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then().log().all()
                .statusCode(200)
                .extract().response();
        return response;
    }


}
