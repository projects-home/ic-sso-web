# 单点登录服务端

### 入口
	http://ip:port/ic-sso-web/login
### 部署前提
	需要部署disconf
### 部署修改配置文件
	``` xml
	/ic-sso-web/src/main/resources/disconf.properties
	``` 
### 单点登录客户端配置

##### web.xml增加
	<filter>
	  <filter-name>xss-firewall</filter-name>
	  <filter-class>com.ifudata.ic.xss.filter.XSSFilter</filter-class>
	</filter>
	<filter-mapping>
	  <filter-name>xss-firewall</filter-name>
	  <url-pattern>/*</url-pattern>
	</filter-mapping>
	<filter>
	  <filter-name>sessionFilter</filter-name>
	  <filter-class>com.ifudata.ic.uni.session.filter.CacheSessionFilter</filter-class>
	  <init-param>
	    <param-name>ignore_suffix</param-name>
	    <param-value>.png,.jpg,.jpeg,.gif,.css,.js,.html,.htm</param-value>
	  </init-param>
	</filter>
	<filter-mapping>
	  <filter-name>sessionFilter</filter-name>
	  <url-pattern>/*</url-pattern>
	</filter-mapping>
	<filter>
	  <filter-name>ssoProxyFilter</filter-name>
	  <filter-class>com.ifudata.ic.sso.client.filter.FilterChainProxy</filter-class>
	  <init-param>
	    <param-name>ignore_resources</param-name>
	    <param-value>.png,.jpg,.jpeg,.gif,.css,.js,.html,.htm</param-value>
	  </init-param>
	</filter>
	<filter-mapping>
	  <filter-name>ssoProxyFilter</filter-name>
	  <url-pattern>/*</url-pattern>
	</filter-mapping>
	<filter>
	  <filter-name>assembleUserInfoFilter</filter-name>
	  <filter-class>com.ai.baas.op.web.filter.AssembleUserInfoFilter</filter-class>
	  <init-param>
	    <param-name>ignore_suffix</param-name>
	    <param-value>.png,.jpg,.jpeg,.gif,.css,.js,.html,.htm</param-value>
	  </init-param>
	</filter>
	<filter-mapping>
	  <filter-name>assembleUserInfoFilter</filter-name>
	  <url-pattern>/*</url-pattern>
	</filter-mapping>
##### resources下增加sso.properties文件
	#单点登录服务器登录地址(外网)
	casServerLoginUrl=http://172.16.13.159:8083/ic-sso-web/login
	#单点登录服务器登录地址(内网)
	casServerLoginUrl_Inner=http://172.16.13.159:8083/ic-sso-web/login
	# cas 服务器地址(外网)  http://IP:PORT/CasWebProName
	casServerUrlPrefix=http://172.16.13.159:8083/ic-sso-web
	# cas 服务器地址(内网)  http://IP:PORT/CasWebProName
	casServerUrlPrefix_Inner=http://172.16.13.159:8083/ic-sso-web
	#单点登录客户端主机地址(外网) http://IP:PORT
	serverName=http://172.16.63.87:8084
	#单点登录客户端主机地址(内网) http://IP:PORT
	serverName_Inner=http://172.16.13.158:8084
	encoding=utf-8
	#loginType=S
	##serverName=http://10.1.228.222:14211
	#单点登录登出地址(外网) http://IP:PORT/CasWebProName
	logOutServerUrl=http://172.16.13.159:8083/ic-sso-web/logout
	#单点登录登出地址(内网) http://IP:PORT/CasWebProName
	logOutServerUrl_Inner=http://172.16.13.159:8083/ic-sso-web/logout
	#单点登录登出后返回地址(外网) http://IP:PORT/CasWebProName
	logOutBackUrl=http://172.16.13.158:8084/ic-sso-client
	#单点登录登出后返回地址(内网) http://IP:PORT/CasWebProName
	logOutBackUrl_Inner=http://172.16.13.158:8084/ic-sso-client
	
	## internationalization locale 
	#获取用户语言的URL参数名
	localeParamName=lang
	# 获取用户语言的cookie名
	localeCookieName=locale
	#Inner Domains 
	#单点登录登出后返回地址(自动判断内外网)
	innerDomains=xxx.com

##### 配置disconf缓存文件：redis.properties
	mcs.maxTotal=1024
	mcs.maxIdle=200
	mcs.minIdle=5
	mcs.host=172.16.13.121:6389
	mcs.testOnBorrow=true
	mcs.password=