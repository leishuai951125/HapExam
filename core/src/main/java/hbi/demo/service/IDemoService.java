package hbi.demo.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import hbi.demo.dto.Demo;

public interface IDemoService extends IBaseService<Demo>, ProxySelf<IDemoService>{

}