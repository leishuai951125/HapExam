package hbi.demo.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import hbi.demo.dto.OmOrderHeaders;
import hbi.demo.dto.OrderHeadersOnLines;

import java.util.List;

public interface IOmOrderHeadersService extends IBaseService<OmOrderHeaders>, ProxySelf<IOmOrderHeadersService>{

//    List<OrderHeadersOnLines>  selectHeaderById(IRequest requestContext, hbi.demo.dto.OmOrderHeaders dto, int page, int pageSize);
}