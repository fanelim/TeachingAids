package sdk.param;

import lombok.Data;

@Data
public class SignUpParam {
    private String phone;
    private Integer subjectId;
    private String subjectName;
    private String password;
    private String name;
    private Integer isTeacher;
}
