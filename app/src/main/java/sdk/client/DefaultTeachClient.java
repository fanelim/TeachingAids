package sdk.client;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.io.IOException;
import java.util.List;

import sdk.Invoker;
import sdk.Result;
import sdk.TeachApiException;
import sdk.param.ClassCreateParam;
import sdk.param.ClassQueryParam;
import sdk.param.SignInParam;
import sdk.param.SignUpParam;
import sdk.param.StudentAddParam;
import sdk.param.StudentsOfClassQueryParam;
import sdk.param.SubjectCreateParam;
import sdk.param.SubjectSubQueryParam;
import sdk.vo.ClassVO;
import sdk.vo.StudentVO;
import sdk.vo.SubjectVO;
import sdk.vo.UserVO;

import static sdk.ApiPath.ADD_STUDENT;
import static sdk.ApiPath.CREATE_CLASS;
import static sdk.ApiPath.CREATE_SUBJECT;
import static sdk.ApiPath.QUERY_CLASSES;
import static sdk.ApiPath.QUERY_MAIN_SUBJECT;
import static sdk.ApiPath.QUERY_STUDENTS_OF_CLASS;
import static sdk.ApiPath.QUERY_SUB_SUBJECT;
import static sdk.ApiPath.SIGN_IN;
import static sdk.ApiPath.SIGN_UP;


public class DefaultTeachClient implements TeachClient {
    private Invoker invoker;

    public DefaultTeachClient(ClientConfig clientConfig) {
        this.invoker = new Invoker(clientConfig);
    }

    @Override
    public Boolean signUp(SignUpParam param) throws IOException, TeachApiException {
        String response = invoker.post(SIGN_UP, param);
        Result<Boolean> result =  JSON.parseObject(response, new TypeReference<Result<Boolean>>(){});
        return result.getDataV2();
    }

    @Override
    public UserVO signIn(SignInParam param) throws IOException, TeachApiException {
        String response = invoker.post(SIGN_IN, param);
        Result<UserVO> result = JSON.parseObject(response, new TypeReference<Result<UserVO>>(){});
        return result.getDataV2();
    }

    @Override
    public List<SubjectVO> getMainSubject() throws IOException, TeachApiException {
        String response = invoker.get(QUERY_MAIN_SUBJECT);
        Result<List<SubjectVO>> result = JSON.parseObject(response, new TypeReference<Result<List<SubjectVO>>>(){});
        return result.getDataV2();
    }

    @Override
    public List<SubjectVO> getSubSubject(SubjectSubQueryParam param) throws IOException, TeachApiException {
        String response = invoker.post(QUERY_SUB_SUBJECT, param);
        Result<List<SubjectVO>> result = JSON.parseObject(response, new TypeReference<Result<List<SubjectVO>>>(){});
        return result.getDataV2();
    }

    @Override
    public Boolean createSubject(SubjectCreateParam param) throws IOException, TeachApiException {
        String response = invoker.post(CREATE_SUBJECT, param);
        Result<Boolean> result = JSON.parseObject(response, new TypeReference<Result<Boolean>>(){});
        return result.getDataV2();
    }

    @Override
    public Boolean createClass(ClassCreateParam param) throws IOException, TeachApiException {
        String response = invoker.post(CREATE_CLASS, param);
        Result<Boolean> result = JSON.parseObject(response, new TypeReference<Result<Boolean>>(){});
        return result.getDataV2();
    }

    @Override
    public Boolean addStudent(StudentAddParam param) throws IOException, TeachApiException {
        String response = invoker.post(ADD_STUDENT, param);
        Result<Boolean> result = JSON.parseObject(response, new TypeReference<Result<Boolean>>(){});
        return result.getDataV2();
    }

    @Override
    public List<ClassVO> getClasses(ClassQueryParam param) throws IOException, TeachApiException {
        String response = invoker.post(QUERY_CLASSES, param);
        Result<List<ClassVO>> result = JSON.parseObject(response, new TypeReference<Result<List<ClassVO>>>(){});
        return result.getDataV2();
    }

    @Override
    public List<StudentVO> getStudentsOfClass(StudentsOfClassQueryParam param) throws IOException, TeachApiException {
        String response = invoker.post(QUERY_STUDENTS_OF_CLASS, param);
        Result<List<StudentVO>> result = JSON.parseObject(response, new TypeReference<Result<List<StudentVO>>>(){});
        return result.getDataV2();
    }
}
