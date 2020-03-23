package cn.ekgc.itrip.controller;

import cn.ekgc.itrip.base.controller.BaseController;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <b>支付宝界面</b>
 */
@RestController("tradeController")
@RequestMapping("/trade/api")
public class TradeController extends BaseController {
	@GetMapping(value = "/prepay/{tradeNo}")
	public  void testTrade(@PathVariable("tradeNo")String tradeNo)throws Exception{
		AlipayClient alipayClient =  new DefaultAlipayClient
				( "https://openapi.alipaydev.com/gateway.do" ,
						"2016101800719591",
						"MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCNup6E9ndeTjBVrKzQqYx0hWv0ErwOuF3KmuayBI3nXUNc930Dk3HZbVJFIMWjeeDmVT2Ikf6v5cKkhtZak1pmYPOZqVo2h7Y28NJWz6lzAj7lzPZfhLGCtC8piXed9lw9bRCnFQwM3vvFtY2H9Yhg2wpcamKWvuRDXwp3vA2e9hS5cGrC7AmRdUi+Y6rQ5Konm2M2heGebWKqd1Ox4TC4zlmm8SQQHJd7P5HH5vMwEO/b9DLq2h/O4U3UZyfuUr59if9qSYRfH8UAqtVUniahuaNSFBMNv8sRLgkDkutYjAsQM4MTJQIePqKd8Ak9udtg4efYCUXg5t5VKHeuGpAzAgMBAAECggEAXstlX1ViPadaWNNnuyDMGttqexNUyRuzT+IPTx4Qs6EDf1jhOtURnvWpwybKSi8op6Z3w7KygH/SkizFjktkeXl7NVm6RyK6U0D7zqiSk6P64cUXPIgKGnvd15BkezjeNQ+PxdEkAC/cgj6ObVNiZjXbVjipvF3tWspku/UBv0Gj44UzZMkqPH9eYqZsc+rfMoqDa1fsOBO0q6NzRaYjyGID5UlCH3oyrwZUjtPbyr480aVc7HGvJ+jjdbjS2bQAkbS5nXTZLVO9TkITeRel9xN5gbQ+fFrH+DcGDBpO7dq2wVkAbrcheC4GGcHttyNJXMQQ5DcTxXrtaGtMgR9hEQKBgQDVYjiW0sEr0fNW7loCWfyMurAglNI173ZrHn+GsGUyJNk7Id2sD9wYbX/r1AP8USM/Ra7h3fHn3ZO2na+85oHDMeKHVZZ2m9C5Siaw4SiUn4BIzvefVsOPjYLk9XfjA8IambLz2EGaq/xhWqoHCo6SHblWb8ty6wRAiqzQO6KDLQKBgQCqCN7+LLctYCOhsktsk7D0FIXuylSJPOThnV8A9zKEJAByloUWdxA7WMcdTujk9hE2aQ4Eo17w1aHDd6J4w34VghPSGdMN7+Y4zKd6LbV3wi76WhpEQ+/0VvM1IxHkdavbz8D3d8Ttw3zLrVrfd70x+KQCQAygTJi+f/4ndlL83wKBgCqLkfLohKGDE/yyO3oajRNWKcXlRmCApWDITO8C/rpXxIRYxUUISH8KXiYcOMs3NkroTu0z/oaXlFsl56NHZnoP2TXYp85wVNaM5VgBHME9aCucMJ0xhH573axfEQ9PcYUyPg8GgFYhkomqtRoATdruWETWt37rQwbHX5uJOGblAoGAAtCkyHZ7DeBClsG55/FGA7R3Ry3IWoFMlMOjOAPfrTpfW4ZMa8PK3ACrQzv0NR4xyMHSfpcp0jjnNbI8WV17L+X4aAkPanKnUBdrSmWJVJM9bd2iA7FQPq5V1nJfMHYWQ0xcOHyeIQWUgla7Y2m7EyG3rWXcD6dU/ApobcTSuOcCgYEArNq8ABM3mivB4Rf484t6bxRaE4WXil6Cj1abuA8mvZrCq9FP0n0ckPCb4I1THG0UDBTTUqjTt7GceaD6h7mftrCGRFNr3oGfqrA74cOYCPEFxEQ6dwpyTwD7nwO2iKdsCQGuyDEW4Ek5pnd7O3to1hRQSjXJ4aiFMKYNuvkUhNY=",
						"json",
						"UTF-8",
						"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjbqehPZ3Xk4wVays0KmMdIVr9BK8DrhdyprmsgSN511DXPd9A5Nx2W1SRSDFo3ng5lU9iJH+r+XCpIbWWpNaZmDzmalaNoe2NvDSVs+pcwI+5cz2X4SxgrQvKYl3nfZcPW0QpxUMDN77xbWNh/WIYNsKXGpilr7kQ18Kd7wNnvYUuXBqwuwJkXVIvmOq0OSqJ5tjNoXhnm1iqndTseEwuM5ZpvEkEByXez+Rx+bzMBDv2/Qy6tofzuFN1Gcn7lK+fYn/akmEXx/FAKrVVJ4mobmjUhQTDb/LES4JA5LrWIwLEDODEyUCHj6infAJPbnbYOHn2AlF4ObeVSh3rhqQMwIDAQAB",
						"RSA2");  //获得初始化的AlipayClient
		AlipayTradePagePayRequest alipayRequest =  new  AlipayTradePagePayRequest(); //创建API对应的request
		alipayRequest.setReturnUrl( "http://localhost/itrip" );
		alipayRequest.setNotifyUrl( "http://domain.com/CallBack/notify_url.jsp" ); //在公共参数中设置回跳和通知地址
		alipayRequest.setBizContent( "{"  +
				"    \"out_trade_no\":\"20150320010101001\","  +
				"    \"product_code\":\"FAST_INSTANT_TRADE_PAY\","  +
				"    \"total_amount\":88.88,"  +
				"    \"subject\":\"Iphone6 16G\","  +
				"    \"body\":\"Iphone6 16G\","  +
				"    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\","  +
				"    \"extend_params\":{"  +
				"    \"sys_service_provider_id\":\"2088511833207846\""  +
				"    }" +
				"  }" ); //填充业务参数
		String form= "" ;
		try  {
			form = alipayClient.pageExecute(alipayRequest).getBody();  //调用SDK生成表单
		}  catch  (AlipayApiException e) {
			e.printStackTrace();
		}
		response.setContentType( "text/html;charset=UTF-8");
		response.getWriter().write(form); //直接将完整的表单html输出到页面
		response.getWriter().flush();
		response.getWriter().close();
	}
}
