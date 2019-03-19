package com.cz.feign.service;

import com.alibaba.fastjson.JSONObject;
import feign.Param;
import feign.RequestLine;


/**
 * 手机号查询地区
 */
public interface DetailPhoneClient {
    /**
     * http://mobsec-dianhua.baidu.com/dianhua_api/open/location?tel=13322222222&qq-pf-to=pcqq.c2c
     * response:{"response":{"13322222222":{"detail":{"area":[{"city":"大连"}],"province":"辽宁","type":"domestic","operator":"电信"},"location":"辽宁大连电信"}},"responseHeader":{"status":200,"time":1539141421138,"version":"1.1.0"}}
     * @param tel
     * @return
     */
    @RequestLine("GET /open/location?tel={tel}&qq-pf-to=pcqq.c2c")
    JSONObject getDetailByPhone(@Param(value = "tel") String tel);
}
