import java.util.ArrayList;

public class envClear {
    public static void envClear(String depId, String token) {
        //清理环境
        ArrayList<Integer> depIdList = depApis.listDep(depId, token).path("department.id");
        for (int id : depIdList) {
            if (id == 1) {
                continue;
            }
            depApis.deleteDep(id + "", token);
        }
    }
}
