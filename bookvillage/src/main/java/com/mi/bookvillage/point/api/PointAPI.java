package com.mi.bookvillage.point.api;

import com.mi.bookvillage.common.response.APIResponse;
import com.mi.bookvillage.common.response.APIResponseBuilderFactory;
import com.mi.bookvillage.common.security.JWTokenUtil;
import com.mi.bookvillage.customer.model.service.CustomerService;
import com.mi.bookvillage.customer.model.vo.CustomerVO;
import com.mi.bookvillage.point.model.service.PointService;
import com.mi.bookvillage.point.model.vo.PointVO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * PointAPI
 */
@RestController
public class PointAPI {

    private PointService pointService;
    private CustomerService customerService;
    private APIResponseBuilderFactory apiResponseBuilderFactory;
    private String token;

    public PointAPI(PointService pointService
                  , CustomerService customerService
                  , APIResponseBuilderFactory apiResponseBuilderFactory){
        this.pointService = pointService;
        this.customerService = customerService;
        this.apiResponseBuilderFactory = apiResponseBuilderFactory;
    }

    /**
     * 포인트 목록 조회
     * @return
     */
    @RequestMapping(value = "/point/list", method = RequestMethod.GET)
    public APIResponse getPointList( HttpServletRequest request ){
        // --- header 토큰 GET
         token = request.getHeader("Authorization");

        // --- 토큰 유효성 검사
        if( token == null || ! JWTokenUtil.checkToken(token)){
            return null; // --- Exception 추가
        }
        // --- 토큰 해독
        Map<String, Object> adminObj = JWTokenUtil.getTokenInfo(token);
        String customerId = (String)adminObj.get("userId");

       List<PointVO> pointList = pointService.getPointListById(customerId);

       return apiResponseBuilderFactory.success().setData(pointList).build();
    }


    @RequestMapping(value = "/point/charge", method = RequestMethod.POST)
    public APIResponse transactionPoint(@RequestBody PointVO pointVO,
                                         HttpServletRequest request ) {
        token = request.getHeader("Authorization");

        if( token == null || ! JWTokenUtil.checkToken(token)){
            return null; // --- Exception 추가
        }

        // 토큰 해독 후 customer 객체 GET
        Map<String, Object> adminObj = JWTokenUtil.getTokenInfo(token);
        String customerId = (String)adminObj.get("userId");
        CustomerVO customer = customerService.getCustomerDetailById(customerId);

        // point 객체에 userSeq 세팅
        pointVO.setUserSeq(customer.getUserSeq());
        // point 객체에 previous 포인트 계산해서 세팅
        int previousPoint = pointService.getPreviousTotalPoint(customer.getUserSeq());
        pointVO.setPreviousPoint(previousPoint);

        // 포인트 적립
        pointService.transactionPoint(pointVO);
        return apiResponseBuilderFactory.success().build();
    }





}