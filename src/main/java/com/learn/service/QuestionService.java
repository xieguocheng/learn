package com.learn.service;

import com.learn.dto.QuestionDTO;
import com.learn.pojo.Question;
import com.learn.utils.ApiResponse;

import java.util.List;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/4/1 18:52
 */

public interface QuestionService {

   public List<QuestionDTO> findAllQuestionDTO(Integer examType);

   public List<Question> findAllQuestion(Integer examType);

    //查询所有单选题
    public List<QuestionDTO> findAllSingleChoice(Integer examType);

   //查看所有多选题
   public List<QuestionDTO> findAllMultipleChoice(Integer examType);

   //查看所有填空题
    public List<QuestionDTO> findAllfillBlank(Integer examType);

    //查看所有简答题
    public List<QuestionDTO> findAllEssayQuestion(Integer examType);

    //通过id查询题目
    public Question findQuestionById(Integer questionId);

    //通过question更新题目
    public void updateQuestion(Question existQuestion);

    //通过id删除题目
    public void deleteQuestionById(Integer questionId);

    //添加题目
    public void saveQuestion(Question question);

    //通过questionListId查询所有question
    public List<Question> findQuestionByQuestionListId(String questionListId);

}
