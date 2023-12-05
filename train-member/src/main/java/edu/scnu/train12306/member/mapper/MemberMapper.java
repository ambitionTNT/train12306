package edu.scnu.train12306.member.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @version 1.0
 * @Description: 会员模块的mapper
 * @Author: long
 * @Date: 2023/12/5 11:33
 */

@Mapper
public interface MemberMapper {
    int count();
}
