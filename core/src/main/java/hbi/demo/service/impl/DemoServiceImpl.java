package hbi.demo.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import hbi.demo.dto.Demo;
import hbi.demo.service.IDemoService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class DemoServiceImpl extends BaseServiceImpl<Demo> implements IDemoService{

}