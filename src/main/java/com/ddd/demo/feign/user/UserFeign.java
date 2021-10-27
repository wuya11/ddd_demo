package com.ddd.demo.feign.user;

import com.ddd.demo.feign.user.dto.UserResponse;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 用户中心接口封装-模拟处理
 *
 * @author wl
 * @date 2019/12/17 13:39
 */
//@FeignClient(name = "order", url = "order/xxxx/xxxx/", fallbackFactory = OrderFeignFallbackFactory.class)
public interface UserFeign {

    /**
     * 根据人员编码获取人员信息-模拟直接返回结果
     *
     * @param acId 人员编码
     * @return 人员信息
     */
//    @RequestMapping(value = "/v1/order/info/{subOid}", method = RequestMethod.GET)
    default UserResponse getUserResponse(@PathVariable("acId") long acId) {
        UserResponse userResponse = new UserResponse();
        userResponse.setAcId(1);
        userResponse.setEmpName("测试1");
        userResponse.setAcType((byte) 1);
        return userResponse;
    }

}









