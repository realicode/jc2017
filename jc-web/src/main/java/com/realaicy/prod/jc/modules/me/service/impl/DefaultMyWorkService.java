package com.realaicy.prod.jc.modules.me.service.impl;

import com.realaicy.prod.jc.common.event.WorkDoneEvent;
import com.realaicy.prod.jc.lib.core.service.impl.DefaultBaseServiceImpl;
import com.realaicy.prod.jc.modules.me.model.MyWork;
import com.realaicy.prod.jc.modules.me.model.vo.MyWorkVO;
import com.realaicy.prod.jc.modules.me.repos.MyWorkRepos;
import com.realaicy.prod.jc.modules.me.service.MyWorkService;
import com.realaicy.prod.jc.realglobal.config.StaticParams;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * Created by realaicy on 16/3/14.
 * xxx
 */
@Service
@Transactional
public class DefaultMyWorkService extends DefaultBaseServiceImpl<MyWork, BigInteger>
        implements MyWorkService {

    private final ApplicationEventPublisher publisher;

    public DefaultMyWorkService(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }


    @Override
    public void saveFromVO(MyWork po, MyWorkVO vo) {

    }

    @Override
    public Long countByUserUsername(String username) {
        return ((MyWorkRepos) baseRepository).countByUserUsername(username);
    }

    @Override
    public Long countByUserId(BigInteger id) {
        return ((MyWorkRepos) baseRepository).countByUserId(id);
    }

    @Override
    public Long todoWorkCountByUserId(BigInteger id) {
        return ((MyWorkRepos) baseRepository).countByUserIdAndStatus(id, StaticParams.REALSTATUS.MYWORK_NOTSTART);
    }

    @Override
    public MyWork findByWorkUri(String workUri) {
        return ((MyWorkRepos) baseRepository).findByWorkUri(workUri);
    }

    @Override
    public List<MyWork> findTodoWorkByUserUsername(String username) {
        return ((MyWorkRepos) baseRepository).findByUserUsernameAndStatus(username,
                StaticParams.REALSTATUS.MYWORK_NOTSTART);
    }

    @Override
    public void markDone(MyWork myWork) {
        myWork.setStatus(StaticParams.REALSTATUS.MYWORK_DONE);
        myWork.setProcessDate(new Date());
        this.save(myWork);
        WorkDoneEvent workDoneEvent = new WorkDoneEvent();
        publisher.publishEvent(workDoneEvent);
    }
}
