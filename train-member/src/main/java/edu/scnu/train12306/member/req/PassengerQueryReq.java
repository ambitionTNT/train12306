package edu.scnu.train12306.member.req;

import edu.scnu.train12306.common.req.PageReq;

public class PassengerQueryReq  extends PageReq {




    private Long memberId;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "PassengerQueryReq{" +
                "memberId=" + memberId +
                '}';
    }
}