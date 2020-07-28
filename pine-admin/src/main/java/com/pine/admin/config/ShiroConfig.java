package com.pine.admin.config;

import com.pine.admin.shiro.CustomRolesAuthorizationFilter;
import com.pine.admin.shiro.KickoutSessionControlFilter;
import com.pine.admin.shiro.ShiroFormAuthenticationFilter;
import com.pine.admin.shiro.UserRealm;
import com.sun.jersey.core.util.Base64;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro的配置文件
 *
 * @author Mark sunlightcs@gmail.com
 */
@Configuration
public class ShiroConfig {
    private static final String SESSION_KEY = "shiro:session:";
    private static final String NAME = "custom.name";
    private static final String VALUE = "/";

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager securityManager, @Qualifier("kickoutSessionControlFilter") KickoutSessionControlFilter kickoutSessionControlFilter) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        shiroFilter.setLoginUrl("/login");
        shiroFilter.setUnauthorizedUrl("/error");

        //注意过滤器配置顺序 不能颠倒
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了，登出后跳转配置的loginUrl
        Map<String, String> filterMap = new LinkedHashMap<>();
        // 配置不会被拦截的链接 顺序判断
        filterMap.put("/swagger/**", "anon");
        filterMap.put("/v2/api-docs", "anon");
        filterMap.put("/swagger-ui.html", "anon");
        filterMap.put("/webjars/**", "anon");
        filterMap.put("/swagger-resources/**", "anon");
        filterMap.put("/statics/**", "anon");
        filterMap.put("/login", "anon");
        filterMap.put("/wx/**", "anon");
        filterMap.put("/assets/**", "anon");
        filterMap.put("/favicon.ico", "anon");
        filterMap.put("/captcha.jpg", "anon");
        filterMap.put("/test/**", "anon");
        filterMap.put("/**", "authc");
        LinkedHashMap<String, Filter> filtsMap = new LinkedHashMap<String, Filter>();
        filtsMap.put("authc", new ShiroFormAuthenticationFilter());
        shiroFilter.setFilters(filtsMap);
        shiroFilter.setFilterChainDefinitionMap(filterMap);

        return shiroFilter;
    }

    @Bean
    public CustomRolesAuthorizationFilter rolesAuthorizationFilter() {
        return new CustomRolesAuthorizationFilter();
    }

    /**
     * 使注解生效
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator app = new DefaultAdvisorAutoProxyCreator();
        app.setProxyTargetClass(true);
        return app;
    }

    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean("delegatingFilterProxy")
    public FilterRegistrationBean delegatingFilterProxy() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("shiroFilter");
        filterRegistrationBean.setFilter(proxy);
        return filterRegistrationBean;
    }


    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;

    }

    /**
     * 将自己的验证方式加入容器
     *
     * @return
     */
    @Bean(name = "myShiroRealm")
    public UserRealm myShiroRealm(@Qualifier("redisCacheManager") RedisCacheManager redisCacheManager) {
        UserRealm myShiroRealm = new UserRealm();
        myShiroRealm.setCacheManager(redisCacheManager);
        myShiroRealm.setAuthenticationCachingEnabled(false);
        myShiroRealm.setAuthorizationCachingEnabled(false);
        return myShiroRealm;
    }

    /**
     * Redis集群使用RedisClusterManager，单个Redis使用RedisManager
     *
     * @param redisProperties
     * @return
     */
    @Bean(name = "redisManager")
    public RedisManager redisManager(RedisProperties redisProperties) {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(redisProperties.getHost() + ":" + redisProperties.getPort());
        redisManager.setPassword(redisProperties.getPassword());
        return redisManager;
    }

    @Bean(name = "redisCacheManager")
    public RedisCacheManager redisCacheManager(@Qualifier("redisManager") RedisManager redisManager) {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager);
        //redis中针对不同用户缓存
        redisCacheManager.setPrincipalIdFieldName("userName");
        //用户权限信息缓存时间
        redisCacheManager.setExpire(200000);
        return redisCacheManager;

    }

    @Bean(name = "redisSessionDAO")
    public RedisSessionDAO redisSessionDAO(@Qualifier("redisManager") RedisManager redisManager) {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setExpire(86400);
        redisSessionDAO.setKeyPrefix(SESSION_KEY);
        redisSessionDAO.setRedisManager(redisManager);
        return redisSessionDAO;
    }

    /**
     * //权限管理，配置主要是Realm的管理认证
     *
     * @return
     */
    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("myShiroRealm") UserRealm myShiroRealm, @Qualifier("sessionManager") SessionManager sessionManager, @Qualifier("redisCacheManager") RedisCacheManager redisCacheManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm);
        securityManager.setCacheManager(redisCacheManager);
        securityManager.setSessionManager(sessionManager);
        securityManager.setRememberMeManager(rememberMeManager());
        //注入Cookie记住我管理器
        return securityManager;
    }


    @Bean("rememberMeManager")
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(simpleCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
        return cookieRememberMeManager;
    }

    @Bean("simpleCookie")
    public SimpleCookie simpleCookie() {
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setName(NAME);
        simpleCookie.setValue(VALUE);
        return simpleCookie;
    }

    /**
     * 配置shiro session 的一个管理器
     *
     * @return
     */
    @Bean(name = "sessionManager")
    public SessionManager sessionManager(@Qualifier("redisSessionDAO") RedisSessionDAO redisSessionDAO, @Qualifier("simpleCookie") SimpleCookie simpleCookie) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO);
        sessionManager.setSessionIdCookieEnabled(true);
//        sessionManager.setSessionIdCookie(simpleCookie);
        return sessionManager;
    }

    /**
     * 注入踢人策略
     *
     * @return
     */
    @Bean("kickoutSessionControlFilter")
    public KickoutSessionControlFilter kickoutSessionControlFilter(
            @Qualifier("sessionManager") SessionManager sessionManager,
            @Qualifier("redisCacheManager") RedisCacheManager redisCacheManager,
            @Qualifier("redisSessionDAO") RedisSessionDAO redisSessionDAO) {
        KickoutSessionControlFilter kickout = new KickoutSessionControlFilter();
        kickout.setCacheManager( redisCacheManager);
        kickout.setSessionManager(sessionManager);
        kickout.setKickoutAfter(true);
        kickout.setSessionDAO(redisSessionDAO);
        kickout.setMaxSession(1);
        return kickout;
    }
    @Bean
    public EhCacheManager getEhCacheManager() {
        EhCacheManager em = new EhCacheManager();
        //配置shiro缓存
        em.setCacheManagerConfigFile("classpath:ehcache.xml");
        return em;
    }

}
