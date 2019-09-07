package com.tent.master;

import com.tent.common.jpa.*;
import com.tent.po.entity.sys.Sequence;
import com.tent.service.impl.sys.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by haobingfu on 2019/9/6.
 */
@Controller
@RequestMapping(value = "sys/sequence")
public class SequenceController extends BaseController<Sequence,Sequence> {

    @Autowired
    private SequenceService sequenceService;

    @Override
    public BaseService<Sequence> getBaseService() {
        return this.sequenceService;
    }

    @Override
    public String positionJsp() {
        return "sys";
    }

    @Override
    public String prefixJsp() {
        return "sequence";
    }
}
