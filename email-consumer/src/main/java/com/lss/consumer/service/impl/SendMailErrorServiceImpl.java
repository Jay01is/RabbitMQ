package com.lss.consumer.service.impl;

import com.lss.consumer.entity.SendMailError;
import com.lss.consumer.mapper.SendMailErrorMapper;
import com.lss.consumer.service.ISendMailErrorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jay01is
 * @since 2023-02-06
 */
@Service
public class SendMailErrorServiceImpl extends ServiceImpl<SendMailErrorMapper, SendMailError> implements ISendMailErrorService {

}
