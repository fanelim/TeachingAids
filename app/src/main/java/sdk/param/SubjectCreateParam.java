package sdk.param;

import lombok.Data;

@Data
public class SubjectCreateParam {
    private String uid;
    private Long parentId;
    private Integer subjectId;
    private String name;
}
