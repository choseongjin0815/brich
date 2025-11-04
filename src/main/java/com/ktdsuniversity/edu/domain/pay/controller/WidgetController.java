package com.ktdsuniversity.edu.domain.pay.controller;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.domain.user.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class WidgetController {
	
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @GetMapping("/blgr/pay/{cdId}")
    public String pay(@PathVariable String cdId, Model model,
			 @SessionAttribute(value = "__LOGIN_USER__", required = false) UserVO loginUser ) {
    	
    	String amount = "10";
    	String cdNm="상품";
    	model.addAttribute("amount", amount);
    	model.addAttribute("cdNm",cdNm);
    	model.addAttribute("usrId", loginUser.getUsrId());
    	return "pay/checkout";
    }    
    
    @RequestMapping(value = "/confirm")
    public ResponseEntity<JSONObject> confirmPayment(@RequestBody String jsonBody) throws Exception {
    	
        JSONParser parser = new JSONParser();
        // api 응답값
        String orderId;
        String amount;
        String paymentKey;
        JSONObject easyPay;
        Long easyAmount;
        String orderName;  
        
        // 클라이언트가 보내준 값 (비굣값)
        String clientOrderName;
        String clientUsrId;
        try {
            // 클라이언트에서 받은 JSON 요청 바디입니다.
            JSONObject requestData = (JSONObject) parser.parse(jsonBody);
            paymentKey = (String) requestData.get("paymentKey");
            orderId = (String) requestData.get("orderId");
            amount = (String) requestData.get("amount");
            clientOrderName = (String) requestData.get("orderName");
            clientUsrId = (String) requestData.get("usrId");
            logger.info("결제 요청값 : " + requestData.toString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        };
        JSONObject obj = new JSONObject();
        obj.put("orderId", orderId);
        obj.put("amount", amount);
        obj.put("paymentKey", paymentKey);

        // TODO: 개발자센터에 로그인해서 내 결제위젯 연동 키 > 시크릿 키를 입력하세요. 시크릿 키는 외부에 공개되면 안돼요.
        // @docs https://docs.tosspayments.com/reference/using-api/api-keys
        String widgetSecretKey = "test_gsk_docs_OaPz8L5KdmQXkzRz3y47BMw6";

        // 토스페이먼츠 API는 시크릿 키를 사용자 ID로 사용하고, 비밀번호는 사용하지 않습니다.
        // 비밀번호가 없다는 것을 알리기 위해 시크릿 키 뒤에 콜론을 추가합니다.
        // @docs https://docs.tosspayments.com/reference/using-api/authorization#%EC%9D%B8%EC%A6%9D
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encodedBytes = encoder.encode((widgetSecretKey + ":").getBytes(StandardCharsets.UTF_8));
        String authorizations = "Basic " + new String(encodedBytes);

        // 결제 승인 API를 호출하세요.
        // 결제를 승인하면 결제수단에서 금액이 차감돼요.
        // @docs https://docs.tosspayments.com/guides/v2/payment-widget/integration#3-결제-승인하기
        URL url = new URL("https://api.tosspayments.com/v1/payments/confirm");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization", authorizations);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);


        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(obj.toString().getBytes("UTF-8"));

        int code = connection.getResponseCode();
        boolean isSuccess = code == 200;
        
        InputStream responseStream = isSuccess ? connection.getInputStream() : connection.getErrorStream();

        // TODO: 결제 성공 및 실패 비즈니스 로직을 구현하세요.
        Reader reader = new InputStreamReader(responseStream, StandardCharsets.UTF_8);
        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        
        if (code == 200) {
        	orderId      = (String) jsonObject.get("orderId");
            orderName    = (String) jsonObject.get("orderName");
        	paymentKey   = (String) jsonObject.get("paymentKey");
        	easyPay = (JSONObject) jsonObject.get("easyPay");
            easyAmount    = (easyPay != null && easyPay.get("amount") != null)
                               ? ((Number) easyPay.get("amount")).longValue() : null;
            
            logger.info("orderId : " + orderId);
            logger.info("orderName : " + orderName);
            logger.info("paymentKey : " + paymentKey);
            logger.info("easyAmount : " + easyAmount);
            logger.info("클라이언트가 보내준 값 (비굣값)");            
            logger.info("clientOrderName : " + clientOrderName);        
            logger.info("clientUsrId : " + clientUsrId); 

            
        }

        
        responseStream.close();
        
        logger.info("응답값  [tosspay] raw response: {}", jsonObject.toJSONString());
        return ResponseEntity.status(code).body(jsonObject);
    }

    /**
     * 인증성공처리
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String paymentRequest(HttpServletRequest request, Model model) throws Exception {
        return "/pay/success";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model) throws Exception {
        return "/pay/checkout";
    }

    /**
     * 인증실패처리
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/fail", method = RequestMethod.GET)
    public String failPayment(HttpServletRequest request, Model model) throws Exception {
        String failCode = request.getParameter("code");
        String failMessage = request.getParameter("message");

        model.addAttribute("code", failCode);
        model.addAttribute("message", failMessage);

        return "/pay/fail";
    }
}

