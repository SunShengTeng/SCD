package cn.sst.scd.service.impl;

import cn.sst.scd.annotation.UsedDateSource;
import cn.sst.scd.datasource.DataSourceType;
import cn.sst.scd.entity.Member;
import cn.sst.scd.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/10/13 3:33 下午
 * @Version 1.1.0
 **/
@Service
public class MemberService {
    @Autowired
    private MemberMapper memberMapper;

    @UsedDateSource(value = DataSourceType.member)
    public void insertMember(Member member) {
        memberMapper.insert(member);
    }
}
