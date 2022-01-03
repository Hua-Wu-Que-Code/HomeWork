package com.example.springboot.pay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 支付宝接口配置类
 */
@Configuration
public class PayConfig {
    // 请填写您的AppId，例如：2019091767145019（必填）
    private static final String appID = "2021000118671231";
    //应用私钥，这里修改生成的私钥即可（必填）
    private static final String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC2JA901F63r5QHV4d8MZPkzAz6ypogZu+Omk61Vb8j1+sM35sN/wKaMT/Am3iW9FR+sO6pmSosE0oR/86bWXRa/HEYpfnTmNO/DGxQgN4124lzZSW546++SnBWbDkDciyug0fbMRbdk4sMB043QrTt0Sbha2KzbT0bP0qDR16LLOL7G8f8HkOP0FSqeK2j1n6H7561via30Kf3YWtGmq/VdVpLHsZxisvdi6euVSwiU8dSrnJg59xkaaW3Gb/h2fVoUWpGPmjVa6CIjhEK6Rok2Jd8hxPsszmaDd0a7jO+aSoX3QOZv9JSA2sp0hhLvHVkdvdCzPfJn8duhq4KDTnPAgMBAAECggEAAodxp3+jaQ2JEqkGepT9prmmnHzfsv966BIAccglBUxkOSOJtaIK2TVB21zNSdtOqQuv5K75xO7nomRY7BNL6E+FCoCTfXqrxgVYI64GZ90KhY2r/m7I6+2ArdKG4CNft3SfNM3zsVPxJFOdpGrKtFqpHYwuLKuho5U3ZadCCtCoqeC2eHb5fuxbOD4YamDCFzl2e3BKFqR+/72VVKK8HAIvfJRySrF2/07/nCx1S5jjb6HNr7Ksrzda129qEiCVLcq4XUsqJZdPwQh5mk5Js5oIVBn/N61slIAwK6DqIurKQHB4q3sQwMgERRj7sbNIvwFJDplKwe5p3QH2SxlxYQKBgQD+3zRZkra0u6e6ZOxvb5uhjQ6Gxln63SMiTM2sZTQ0Qmao1QRCKwnCed7SG8zmIVodXcosn9gASwa+dVDwCgzu+AOkmrl0hSbdXQwbMlAa6j3gxaulTv06RPNth1zkk+KLypxUdfHxORRUXq93Vo4w72f4KF3zI+QJCSlKjZZW+QKBgQC28nG9+ZiEuuGri4HuLSXp7PBeAt3Vgi3nf6ci9+MtuKvPoOIB/E3sei6LiYLL5Vq1oCW67EiUHMBI7wE27z9vi+bD+mvLB0cyeaZaVqi/rpUbY37J2tufJIAVmyOpPEUGCzh+xDr3FVdxoJW/JdrfGecII1ZxdHRYuK8q7IDhBwKBgQDlzLZUtPXLSw9V/j031HeMV7RrTFhA2jYdbQZck/tXyy7otvfZ+RgZouyGoM5ECHpJ1BC/33dP/FWeUASkCUvUAwa12VOxfYSnPof11Y1oVx9nlJLIv8mOSjD55nyZE8XFqO7/MEDu8DomQCERBpe6A4HIvAzJdujaRMpYEAUncQKBgGkUqqZO1f7vBK4uzGuMvSk28f1dsiNvyYQdu1eHgJKDbxQsOYSgcfWxXEjDrEAHNen62ZEbJLkGJp/9v/nYw9wDRIIutXmBbbCdVSKwge7PjU+U/39b2Sf4pCVE3d/YAWFuUn3lomTHelmGvlxzltrsmtn+yikSlb1cHykNfR1DAoGAFXPwxMxoh/BVwA1Sth0EARtAb4G0kDW4n9DHwoJc2nvzjd8T8Q7E5jvV4dcccG9iPEUN0fhX8CXxAfxC1MN8XK4SM+Ki1SY4MdoqhnHoXAJSDuQccS+qDxJHvDLH2b3PUVqhudU+SoePeKHyo39sRdVe4LfWUwjuIY5XemN5ezs=";
    //支付宝公钥，而非应用公钥（必填）
    public static final String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlAMOHHPkl/AjkGMWBO2PYfU07edGoZWt9LRgoB55wThH0n7dhVqOHfBrPDt3NrqdUGWF74lsxEd8jh9RN+vXq9g/fuj/hQ/dpvKm3cjrfaj86NeRTW7AhaeVFaY7Ap80+D8ryFPmk3mHK4u+dwYeTlUMsGr7IlZ5CAhEAoKvwbFq8VC0FmZWI9H+ka9o+Bjf894hxrTFdVB4wBpIRceZ9MNZpexlmi6BeS+ENDOJv2j4hVKEwT5GEuMlJxhN00v/9wOXW3rNg+5I8N6gwLWbyx+al1EhV0zmVF13XkiFBU3ym4QnT++rVqgBTZKuvJGT6DyL1pVAmC00e7gMJ8BXiwIDAQAB";
    //默认即可（必填）
    public static final String charset = "utf-8";
    //默认即可（必填）
    public static final String signType = "RSA2";
    @Bean
    public AlipayClient alipayClient(){
        //沙箱环境使用https://openapi.alipaydev.com/gateway.do，线上环境使用https://openapi.alipay.com/gateway.do
        return new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do", appID, privateKey, "json", charset, publicKey, signType);
    }
    /**
     * 验签，是否正确
     */
    public static boolean checkSign(HttpServletRequest request){
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, String> paramsMap = new HashMap<>();
        requestMap.forEach((key, values) -> {
            String strs = "";
            for(String value : values) {
                strs = strs + value;
            }
            System.out.println(key +"===>"+strs);
            paramsMap.put(key, strs);
        });
        System.out.println();
        //调用SDK验证签名
        try {
            return  AlipaySignature.rsaCheckV1(paramsMap, PayConfig.publicKey, PayConfig.charset, PayConfig.signType);
        } catch (AlipayApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("*********************验签失败********************");
            return false;
        }

    }
}

