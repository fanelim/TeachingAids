package sdk.vo;

import lombok.Data;

@Data
public class UserVO {
    private String uid;
    private String phone;
    private Integer subjectId;
    private String subjectName;
    private String password;
    private String name;
    private Integer isTeacher;
}
