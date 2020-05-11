package sdk.param;

import lombok.Data;

@Data
public class StudentAddParam {
    private String teacherUid;
    private Long classId;
    private String studentUid;
}
