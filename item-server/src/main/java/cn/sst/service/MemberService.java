package cn.sst.service;

import cn.sst.annotation.UsedDateSource;
import cn.sst.datasource.DataSourceType;
import cn.sst.entity.Member;
import cn.sst.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author shengtengsun
 * @Description 会员Service
 * @Date 2020/8/19 5:43 下午
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
