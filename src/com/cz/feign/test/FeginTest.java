package com.cz.feign.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cz.feign.service.DetailPhoneClient;
import feign.Feign;
import feign.gson.GsonDecoder;

public class FeginTest {

    public static void main(String[] args) {
        String city = getCityByPhone("18600502595");
        System.out.println(city);
    }
    /**
     * 用手机号查询所属地域
     *
     * @param phone 手机号
     * @return 所属地域
     */
    private static String getCityByPhone(String phone) {
        DetailPhoneClient detailPhoneClient = Feign.builder()
                .decoder(new GsonDecoder())
                .target(DetailPhoneClient.class, "http://mobsec-dianhua.baidu.com/dianhua_api");
        JSONObject detailByPhone = detailPhoneClient.getDetailByPhone(phone);
//        logger.info("查询手机号所属地区：" + detailByPhone);
        // 获取手机号信息的数据
        if (!detailByPhone.containsKey("response")) {
            return null;
        }
        JSONObject response = detailByPhone.getJSONObject("response");
        if (!response.containsKey(phone)) {
            return null;
        }
        JSONObject phoneDetail = response.getJSONObject(phone);
        if (!phoneDetail.containsKey("detail")) {
            return null;
        }
        JSONObject detail = phoneDetail.getJSONObject("detail");
        if (!detail.containsKey("area")) {
            return null;
        }
        JSONArray area = detail.getJSONArray("area");
        if (area.size() == 0) {
            return null;
        }
        JSONObject cityJsonObject = area.getJSONObject(0);
        if (!cityJsonObject.containsKey("city")) {
            return null;
        }
        return cityJsonObject.getString("city");
    }
}
