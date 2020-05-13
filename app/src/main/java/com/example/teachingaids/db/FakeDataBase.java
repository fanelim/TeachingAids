package com.example.teachingaids.db;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakeDataBase {
    public static Map<Class, List<Stu>> dataMap = new HashMap<>();
    public static Map<Long, List<Exam>> stuId2ExamList = new HashMap<>();
    public static Map<Long, Stu> stuId2Stu = new HashMap<>();
    public static Map<Long, List<Exam>> classId2ExamList = new HashMap<>();
    public static Map<Long, List<Question>> examId2QuestionList = new HashMap<>();

    static {
        Class class1 = new Class("高等数学", "");
        Class class2 = new Class("数据结构", "");

        Stu stu = new Stu(1L, "张文", "15258478366", 12);
        Stu stu1 = new Stu(2L, "张武", "18068769899", 11);

        Exam exam = new Exam();
        exam.setId(1L);
        exam.setExamTitle("2020_05_01 测验（1）");
        exam.setExamScore(86);
        Exam exam1 = new Exam();
        exam1.setId(2L);
        exam1.setExamTitle("2020_04_25 测验（2）");
        exam1.setExamScore(80);
        Exam exam2 = new Exam();
        exam.setId(3L);
        exam2.setExamTitle("2020_04_25 测验（1）");
        exam2.setExamScore(90);

        Question q1 = new Question("指出f(x)=1/(1-x),x=1 函数在指定点是否间断，如果间断，指出是哪类间断点。", 20);
        Question q2 = new Question("设向量α、β的长度依次为2和3,则向量α+β与α-β的内积(α+β,α-β)=", 20);
        Question q3 = new Question("为什么收敛的数列必有界？", 20);


        dataMap.put(class1, Arrays.asList(stu, stu1));
        dataMap.put(class2, Arrays.asList(stu, stu1));

        stuId2Stu.put(1L, stu);
        stuId2Stu.put(2L, stu1);

        stuId2ExamList.put(1L, Arrays.asList(exam, exam1, exam2));
        stuId2ExamList.put(2L, Arrays.asList(exam, exam1, exam2));

        List<Exam> list = new ArrayList<>();
        list.addAll(Arrays.asList(exam, exam1, exam2));
        classId2ExamList.put(1L, list);

        examId2QuestionList.put(1L, Arrays.asList(q1, q2, q3));
    }
}
