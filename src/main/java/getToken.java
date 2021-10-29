import static io.restassured.RestAssured.given;

public class getToken {
    public static String getToken(){
        String corpsecret = "9ghF-Z7OhBnSOTeXa0BZG2o1nPXR7zEYHSAImLmdUbA";
        String corpid = "ww7e8290dae1a5371d";
        String token = given().
                when()
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + corpid + "&corpsecret=" + corpsecret)
                .then()
                .extract().response().path("access_token");
        System.out.println(token);
        return token;
    }
    }

