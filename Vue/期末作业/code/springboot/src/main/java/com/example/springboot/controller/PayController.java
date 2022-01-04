package com.example.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.example.springboot.pay.AliReturnPayBean;
import com.example.springboot.pay.PayConfig;
import com.example.springboot.pay.AliReturnPayBean;
import com.example.springboot.pay.PayConfig;

import com.example.springboot.pay.WebSocket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@Controller
@Slf4j
public class PayController {
    @Autowired
    private AlipayClient alipayClient;
    @Autowired
    private WebSocket webSocket;


    @RequestMapping("/createQR")
    @ResponseBody
    @CrossOrigin
    public String send() throws AlipayApiException {
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest(); //创建API对应的request类
        // 在下面会介绍notifyUrl怎么来的
        request.setNotifyUrl("http://cv95x3.natappfree.cc/call");
        //同步回调地址
//        request.setReturnUrl("");
        request.setBizContent("  {" +
                "    \"primary_industry_name\":\"IT科技/IT软件与服务\"," +
                "    \"primary_industry_code\":\"10001/20102\"," +
                "    \"secondary_industry_code\":\"10001/20102\"," +
                "    \"secondary_industry_name\":\"IT科技/IT软件与服务\"" +
                " }");;
        AlipayTradePrecreateResponse response = alipayClient.execute(request);
        String path = "zhifu.jpg";
        if (response.isSuccess()) {
            System.out.println("调用成功");
            return response.getQrCode();
        } else {
            System.out.println("调用失败");
        }
        return "";
    }


    /**
     * 支付宝回调函数
     * @param request
     * @param response
     * @param returnPay
     * @throws IOException
     */
    @RequestMapping("/call")
    public void call(HttpServletRequest request, HttpServletResponse response, AliReturnPayBean returnPay) throws IOException {
        response.setContentType("type=text/html;charset=UTF-8");
        log.info("支付宝的的回调函数被调用");
        if (!PayConfig.checkSign(request)) {
            log.info("验签失败");
            response.getWriter().write("failture");
            return;
        }
        if (returnPay == null) {
            log.info("支付宝的returnPay返回为空");
            response.getWriter().write("success");
            return;
        }
        log.info("支付宝的returnPay" + returnPay.toString());
        //表示支付成功状态下的操作
        if (returnPay.getTrade_status().equals("TRADE_SUCCESS")) {
            log.info("支付宝的支付状态为TRADE_SUCCESS");
            //业务逻辑处理 ,webSocket在下面会有介绍配置
            webSocket.sendMessage("true");
        }
        response.getWriter().write("success");
    }
}