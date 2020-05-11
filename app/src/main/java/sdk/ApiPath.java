package sdk;

public class ApiPath {
    private ApiPath() {
    }
    public static final String SIGN_UP = "/user/signUp";
    public static final String SIGN_IN = "/user/signIn";
    public static final String QUERY_MAIN_SUBJECT = "/subject/get/main";
    public static final String QUERY_SUB_SUBJECT = "/subject/get/sub";
    public static final String CREATE_SUBJECT = "/subject/create";
    public static final String CREATE_CLASS = "/class/create";
    public static final String ADD_STUDENT = "/class/addStudent";
    public static final String QUERY_CLASSES = "/class/getClasses";
    public static final String QUERY_STUDENTS_OF_CLASS = "/class/";
}
