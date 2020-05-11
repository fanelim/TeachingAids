package sdk.client;


import java.io.IOException;
import java.util.List;

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

public interface TeachClient {
     Boolean signUp(SignUpParam param) throws IOException, TeachApiException;

     UserVO signIn(SignInParam param) throws IOException, TeachApiException;

     List<SubjectVO> getMainSubject() throws IOException, TeachApiException;

     List<SubjectVO> getSubSubject(SubjectSubQueryParam param) throws IOException, TeachApiException;

     Boolean createSubject(SubjectCreateParam param) throws IOException, TeachApiException;

     Boolean createClass(ClassCreateParam param) throws IOException, TeachApiException;

     Boolean addStudent(StudentAddParam param) throws IOException, TeachApiException;

     List<ClassVO> getClasses(ClassQueryParam param) throws IOException, TeachApiException;

     List<StudentVO> getStudentsOfClass(StudentsOfClassQueryParam param) throws IOException, TeachApiException;
}
