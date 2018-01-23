package com.ifudata.ic.data.api.user.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ifudata.centra.base.exception.BusinessException;
import com.ifudata.centra.base.exception.SystemException;
import com.ifudata.ic.data.api.user.param.UserLoginResponse;

/**
 * 登录服务<br>
 * Date: 2016年3月16日 <br>
 * Copyright (c) 2016 ifudata.com <br>
 * 
 * @author wangyongxin
 */
@Path("/userlogin")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface ILoginSV {
    /**
     * 根据用户名查询
     * 
     * @param username
     * @return
     * @throws BusinessException,SystemException
     * @author wangyongxin
     * @ApiCode CITIC_UAC_0001
     * @RestRelativeURL userlogin/queryUserByUserName
     */
	@POST
	@Path("/queryUserByUserName")
    UserLoginResponse queryUserByUserName(String username) throws BusinessException,SystemException;


}
