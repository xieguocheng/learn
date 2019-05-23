package com.learn.mapper;

import com.learn.pojo.Question;
import com.learn.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper extends MyMapper<Question> {

    //查到questionListId最大值，进行叠加题目
    public Integer queryMaxQuestionListId();

}