package com.ifudata.ic.data.service.atom.impl;

import java.util.List;

import com.ifudata.centra.sdk.util.CollectionUtil;
import com.ifudata.centra.sdk.util.StringUtil;
import com.ifudata.ic.data.dao.mapper.bo.SysUserCriteria;
import com.ifudata.ic.data.dao.mapper.factory.MapperFactory;
import com.ifudata.ic.data.service.atom.interfaces.ILoginAtomSV;
import org.springframework.stereotype.Component;

import com.ifudata.ic.data.dao.mapper.bo.SysUser;
import com.ifudata.ic.data.dao.mapper.interfaces.SysUserMapper;

@Component
public class LoginAtomSVImpl implements ILoginAtomSV {

    @Override
    public SysUser queryByUserName(SysUser user) {

        SysUserCriteria conditon = new SysUserCriteria();
        SysUserCriteria.Criteria criteria = conditon.or();
        if (!StringUtil.isBlank(user.getMobile())) {
            criteria.andMobileEqualTo(user.getMobile());
        }
        if (!StringUtil.isBlank(user.getEmail())) {
            criteria.andEmailEqualTo(user.getEmail());
        }
        if (!StringUtil.isBlank(user.getLoginName())) {
            criteria.andLoginNameEqualTo(user.getLoginName());
        }
        // 登录标记为1
//        criteria.andLoginFlagEqualTo("1");
        // 删除标记为0
        criteria.andDelFlagEqualTo("0");
        SysUserMapper sysUserMapper = MapperFactory.getSysUserMapper();
       
        List<SysUser> list = sysUserMapper.selectByExample(conditon);
        if (!CollectionUtil.isEmpty(list)) {
            return list.get(0);
        }
        return null;
    }


}
