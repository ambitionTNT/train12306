package edu.scnu.train12306.member.service;

import edu.scnu.train12306.common.resp.PageResp;
import edu.scnu.train12306.member.req.PassengerQueryReq;
import edu.scnu.train12306.member.req.PassengerSaveReq;
import edu.scnu.train12306.member.resp.PassengerQueryResp;

/**
 * @author long
 * @version 1.0
 * @ClassName PassagerService
 * @description: TODO
 * @date 2023/12/11 21:34
 */
public interface PassengerService {
     void save(PassengerSaveReq req);

     PageResp<PassengerQueryResp> queryList(PassengerQueryReq req);

    int delete(Long id);
}
