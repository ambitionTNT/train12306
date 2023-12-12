package edu.scnu.train12306.common.resp;

import java.io.Serializable;
import java.util.List;

/**
 * @author long
 * @version 1.0
 * @ClassName PageReq
 * @description: 分页查询条件的额封装
 * @date 2023/12/12 15:13
 */
public class PageResp <T> implements Serializable {

    /**
     * 总条数
     */
    private Long total;

    /**
     * 当前也列表
     */
    private List<T> list;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageResp{" +
                "total=" + total +
                ", list=" + list +
                '}';
    }
}
