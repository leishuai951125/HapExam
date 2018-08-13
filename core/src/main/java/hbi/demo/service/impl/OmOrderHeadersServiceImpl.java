package hbi.demo.service.impl;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import hbi.demo.dto.OmOrderLines;
import hbi.demo.service.IOmOrderLinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hbi.demo.dto.OmOrderHeaders;
import hbi.demo.service.IOmOrderHeadersService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class OmOrderHeadersServiceImpl extends BaseServiceImpl<OmOrderHeaders> implements IOmOrderHeadersService{

    @Autowired
    IOmOrderHeadersService omOrderHeadersService;
    @Autowired
    IOmOrderLinesService omOrderLinesService;
    
    @Override
    public List<OmOrderHeaders> batchUpdate(IRequest iRequest, List<OmOrderHeaders> headersList){
        if (headersList != null && !headersList.isEmpty()) {
            for (int i = 0; i < headersList.size(); i++) {
                OmOrderHeaders header = headersList.get(i);
                //根据是否拥HeaderId有判断是insert还是update
                Long HeaderId = header.getHeaderId();
                System.out.println(HeaderId);
                if (HeaderId == null || HeaderId==0) {
                    //insert
                    //保存头
                    omOrderHeadersService.insertSelective(iRequest, header);
                    //insert之后便有主键了
                    HeaderId = header.getHeaderId();
                    //保存行
                    List<OmOrderLines> omOrderLinesList = header.getOmOrderLinesList();
                    System.out.println(omOrderLinesList + ""+ omOrderLinesList.size());
                    if (omOrderLinesList != null && !omOrderLinesList.isEmpty())
                        for (int j = 0; j < omOrderLinesList.size(); j++) {
                        OmOrderLines omOrderLines = omOrderLinesList.get(j);
                        omOrderLines.setHeaderId(HeaderId);
                        omOrderLines.setCompanyId(header.getCompanyId());
                        omOrderLinesService.insertSelective(iRequest, omOrderLines);
                    }

                } else {
                    //update
                    //保存头
                    omOrderHeadersService.updateByPrimaryKeySelective(iRequest, header);
                    //保存行的时候需要区分行是新建还是更新
                    List<OmOrderLines> omOrderLinesList = header.getOmOrderLinesList();
                    if (omOrderLinesList != null && !omOrderLinesList.isEmpty()) {
                        for (int j = 0; j < omOrderLinesList.size(); j++) {
                            OmOrderLines OmOrderLines = omOrderLinesList.get(j);
                            Long lineId = OmOrderLines.getLineId();
                            if (lineId == null ||lineId ==0) {
                                OmOrderLines.setHeaderId(HeaderId);
                                OmOrderLines.setCompanyId(header.getCompanyId());
                                omOrderLinesService.insertSelective(iRequest, OmOrderLines);
                            } else {
                                omOrderLinesService.updateByPrimaryKeySelective(iRequest, OmOrderLines);
                            }
                        }
                    }
                }
            }
            return headersList;
        }
        return null;
    }
}