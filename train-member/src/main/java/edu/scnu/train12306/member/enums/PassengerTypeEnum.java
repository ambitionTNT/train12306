package edu.scnu.train12306.member.enums;

/**
 * @author long
 * @version 1.0
 * @ClassName PassagerTypeEnum
 * @description: 乘客类型的枚举变量
 * @date 2023/12/11 21:28
 */
public enum PassengerTypeEnum {
    ADULT("1", "成人"),
    CHILD("2", "儿童"),
    STUDENT("3", "学生");

    private String code;


    private String desc;

    PassengerTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
