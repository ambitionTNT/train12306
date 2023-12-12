package edu.scnu.train12306.member.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import edu.scnu.train12306.common.context.LoginMemberContext;
import edu.scnu.train12306.common.utils.SnowUtil;
import edu.scnu.train12306.member.domain.Passenger;
import edu.scnu.train12306.member.domain.PassengerExample;
import edu.scnu.train12306.member.mapper.PassengerMapper;
import edu.scnu.train12306.member.req.PassengerQueryReq;
import edu.scnu.train12306.member.req.PassengerSaveReq;
import edu.scnu.train12306.member.resp.PassengerQueryResp;
import edu.scnu.train12306.member.service.PassengerService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author long
 * @version 1.0
 * @ClassName PassengerServiceImpl
 * @description: TODO
 * @date 2023/12/11 21:36
 */
@Service
public class PassengerServiceImpl implements PassengerService {

    @Resource
    private PassengerMapper passengerMapper;
    /**
     * 后续界面操作时，保存后，界面会刷新列表，不需要返回保存成果后的数据
     * @param req
     */
    @Override
    public void save(PassengerSaveReq req) {
        DateTime now = DateTime.now();
        Passenger passenger = BeanUtil.copyProperties(req, Passenger.class);
        passenger.setMemberId(LoginMemberContext.getId());
        passenger.setId(SnowUtil.getSnowflakeNextId());
        passenger.setCreateTime(now);
        passenger.setUpdateTime(now);
        passengerMapper.insert(passenger);
    }

    /**
     * 列表查询乘车人信息
     * @param req
     * @return
     */
    @Override
    public List<PassengerQueryResp> queryList(PassengerQueryReq req) {

        PassengerExample passengerExample = new PassengerExample();
        PassengerExample.Criteria criteria = passengerExample.createCriteria();
        //封装查询条件
        if (ObjectUtil.isNotNull(req.getMemberId())){
            criteria.andMemberIdEqualTo(req.getMemberId());
        }
        PageHelper.startPage(1,2);
        List<Passenger> passengerList = passengerMapper.selectByExample(passengerExample);
        List<PassengerQueryResp> passengerQueryResps = BeanUtil.copyToList(passengerList, PassengerQueryResp.class);
        return passengerQueryResps;
    }
}
