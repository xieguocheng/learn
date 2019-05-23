package com.learn.service.Impl;

import com.learn.dto.QuestionDTO;
import com.learn.enums.QuestionStatus;
import com.learn.mapper.CourseTypeMapper;
import com.learn.mapper.QuestionMapper;
import com.learn.pojo.CourseType;
import com.learn.pojo.Question;
import com.learn.service.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/4/1 18:53
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    //可以用于属性复制
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private CourseTypeMapper courseTypeMapper;

    /**
     * 查到所有单选题
     * @param examType
     * @return
     */
    @Override
    public List<QuestionDTO> findAllSingleChoice(Integer examType) {
        //存放具体questionDTO
        List<QuestionDTO> questionDTOList=new ArrayList<QuestionDTO>();
        //查询到所有questionList,类型-单选题，状态1-可用
        Example questionExample=new Example(Question.class);
        questionExample.createCriteria().andEqualTo("questionType", QuestionStatus.QUESTION_TYPE_SINGLE.getValue());//类型
        questionExample.createCriteria().andEqualTo("status",examType);//状态
        List<Question> questionList=questionMapper.selectByExample(questionExample);
        //将所有questionList转换为questionDTOList
        questionList.forEach(question -> {
            QuestionDTO questionDTO=modelMapper.map(question,QuestionDTO.class);
            questionDTO.setCourseType(courseTypeMapper.selectByPrimaryKey(question.getType()));
            questionDTOList.add(questionDTO);
        });
        return questionDTOList;
        //return questionMapper.selectByExample(questionExample);
    }

    @Override
    public List<QuestionDTO> findAllMultipleChoice(Integer examType) {
        //存放具体questionDTO
        List<QuestionDTO> questionDTOList=new ArrayList<QuestionDTO>();
        //查询到所有questionList,类型-单选题，状态1-可用
        Example questionExample=new Example(Question.class);
        questionExample.createCriteria().andEqualTo("questionType",QuestionStatus.QUESTION_TYPE_MULTIPLE.getValue());//类型
        questionExample.createCriteria().andEqualTo("status",examType);//状态
        List<Question> questionList=questionMapper.selectByExample(questionExample);
        //将所有questionList转换为questionDTOList
        questionList.forEach(question -> {
            QuestionDTO questionDTO=modelMapper.map(question,QuestionDTO.class);
            questionDTO.setCourseType(courseTypeMapper.selectByPrimaryKey(question.getType()));
            questionDTOList.add(questionDTO);
        });
        return questionDTOList;
    }

    @Override
    public List<QuestionDTO> findAllfillBlank(Integer examType) {
        //存放具体questionDTO
        List<QuestionDTO> questionDTOList=new ArrayList<QuestionDTO>();
        //查询到所有questionList,类型-单选题，状态1-可用
        Example questionExample=new Example(Question.class);
        questionExample.createCriteria().andEqualTo("questionType",QuestionStatus.QUESTION_TYPE_FILLBLACK.getValue());//类型
        questionExample.createCriteria().andEqualTo("status",examType);//状态
        List<Question> questionList=questionMapper.selectByExample(questionExample);
        //将所有questionList转换为questionDTOList
        questionList.forEach(question -> {
            QuestionDTO questionDTO=modelMapper.map(question,QuestionDTO.class);
            questionDTO.setCourseType(courseTypeMapper.selectByPrimaryKey(question.getType()));
            questionDTOList.add(questionDTO);
        });
        return questionDTOList;
    }

    @Override
    public List<QuestionDTO> findAllEssayQuestion(Integer examType) {
        //存放具体questionDTO
        List<QuestionDTO> questionDTOList=new ArrayList<QuestionDTO>();
        //查询到所有questionList,类型-单选题，状态1-可用
        Example questionExample=new Example(Question.class);
        questionExample.createCriteria().andEqualTo("questionType",QuestionStatus.QUESTION_TYPE_ESSAY.getValue());//类型
        questionExample.createCriteria().andEqualTo("status",examType);//状态
        List<Question> questionList=questionMapper.selectByExample(questionExample);
        //将所有questionList转换为questionDTOList
        questionList.forEach(question -> {
            QuestionDTO questionDTO=modelMapper.map(question,QuestionDTO.class);
            questionDTO.setCourseType(courseTypeMapper.selectByPrimaryKey(question.getType()));
            questionDTOList.add(questionDTO);
        });
        return questionDTOList;
    }

    @Override
    public Question findQuestionById(Integer questionId) {
        return questionMapper.selectByPrimaryKey(questionId);
    }

    @Override
    public void updateQuestion(Question existQuestion) {
        //questionMapper.updateByExampleSelective()
        questionMapper.updateByPrimaryKey(existQuestion);
    }

    @Override
    public void deleteQuestionById(Integer questionId) {
        questionMapper.deleteByPrimaryKey(questionId);
    }

    @Override
    public void saveQuestion(Question question) {
        //qestionListId+1自增
        question.setQuestionListId(questionMapper.queryMaxQuestionListId()+1);
        questionMapper.insertSelective(question);
    }

    @Override
    public List<Question> findQuestionByQuestionListId(String questionListId) {
        String[] str=questionListId.split(",");
        List<Integer> values = new ArrayList<Integer>();
        for(int i=0;i<str.length;i++){
            values.add(Integer.parseInt(str[i]));
        }
        Example questionExample=new Example(Question.class);
        questionExample.createCriteria().andIn("questionListId",values);
        return questionMapper.selectByExample(questionExample);

    }

    @Override
    public List<QuestionDTO> findAllQuestionDTO(Integer examType) {


        return null;
    }

    @Override
    public List<Question> findAllQuestion(Integer examType) {

        return null;
    }


}
