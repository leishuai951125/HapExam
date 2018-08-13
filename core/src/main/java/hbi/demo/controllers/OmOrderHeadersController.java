package hbi.demo.controllers;

import hbi.demo.dto.OmOrderLines;
import hbi.demo.dto.OrderHeadersOnLines;
import hbi.demo.mapper.OrderHeadersOnLinesMapper;
import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import hbi.demo.dto.OmOrderHeaders;
import hbi.demo.service.IOmOrderHeadersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.BindingResult;
import java.util.List;

    @Controller
    public class OmOrderHeadersController extends BaseController{

    @Autowired
    private IOmOrderHeadersService service;

    @Autowired
    private OrderHeadersOnLinesMapper orderHeadersOnLinesMapper;


    @RequestMapping(value = "/hap/om/order/headers/query")
    @ResponseBody
    public ResponseData query(OmOrderHeaders dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/hap/om/order/headers/queryHeaderById")
    @ResponseBody
    public ResponseData queryHeaderById(OmOrderHeaders dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        List<OrderHeadersOnLines> list=orderHeadersOnLinesMapper.selectHeaderById(dto.getHeaderId());
        for (OrderHeadersOnLines orderHeadersOnLines : list) {
            System.out.println(orderHeadersOnLines.toString());
        }
        return new ResponseData(list);
    }



    @RequestMapping(value = "/hap/om/order/headers/submit")
    @ResponseBody
    public ResponseData update(@RequestBody List<OmOrderHeaders> dto, BindingResult result, HttpServletRequest request){
        getValidator().validate(dto, result);
        if (result.hasErrors()) {
        ResponseData responseData = new ResponseData(false);
        responseData.setMessage(getErrorMessage(result, request));
        return responseData;
        }
        IRequest requestCtx = createRequestContext(request);
//        return new ResponseData(service.batchUpdate(requestCtx, dto));
        System.out.println("批量更新");
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/hap/om/order/headers/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<OmOrderHeaders> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    }