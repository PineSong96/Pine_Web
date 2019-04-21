package com.pine.admin.shiro;

import com.pine.common.utils.Constant;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: Pine
 * @Date: 2019/4/11
 * @Email:771190883@qq.com
 */
public class KickoutSessionControlFilter extends AccessControlFilter {
    /**
     *  被踢出的跳转的url
     */
    private String kickoutUrl;
    /**
     *  后者登录踢出前者登录
     */
    private boolean kickoutAfter = true;
    /**
     * 一个用户只能有1个登录在线
     */
    private int maxSession = 1;

    private SessionManager sessionManager;
    private SessionDAO sessionDAO;
    private Cache<String, Deque<Serializable>> cache;

    public void setKickoutUrl(String kickoutUrl) {
        this.kickoutUrl = kickoutUrl;
    }

    public void setKickoutAfter(boolean kickoutAfter) {
        this.kickoutAfter = kickoutAfter;
    }

    public void setMaxSession(int maxSession) {
        this.maxSession = maxSession;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public SessionDAO getSessionDAO() {
        return sessionDAO;
    }

    public void setSessionDAO(SessionDAO sessionDAO) {
        this.sessionDAO = sessionDAO;
    }

    //设置Cache的key的前缀
    public void setCacheManager(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("shiro-kickout-session");
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        if (!subject.isAuthenticated() && !subject.isRemembered()) {
            //如果没有登录，走平时的操作
            return true;
        }

        Session session = subject.getSession();
        ShiroUserInfo shiroUserInfo = ShiroUtils.getShiroUserInfo();
        //如果是微信用户不踢
        if (shiroUserInfo.getUserType().equals(Constant.USER_WEIXIN)) {
            return true;
        }

        String username = shiroUserInfo.getUserName();
        Serializable sessionId = session.getId();

        // 同步控制
        Deque<Serializable> deque = cache.get(username);
        if (deque == null) {
            deque = new LinkedList<Serializable>();
            cache.put(username, deque);
        }

        //如果队列里没有此sessionId，放入队列
        if (!deque.contains(sessionId)) {
            deque.push(sessionId);
            ((HttpServletRequest) request).getSession().setAttribute("currentUser", shiroUserInfo);
        }

        //如果队列里的sessionId数超出最大会话数，开始踢人
        while (deque.size() > maxSession) {
            Serializable kickoutSessionId = null;
            //如果踢出后者 同一个用户第二次在别的地方登录，报错org.apache.shiro.session.UnknownSessionException
            if (kickoutAfter) {
                kickoutSessionId = deque.removeFirst();
            } else { //否则踢出前者
                kickoutSessionId = deque.removeLast();
            }
            try {
                Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));
                if (kickoutSession != null) {
                    kickoutSession.setAttribute("kickout", Boolean.TRUE);
//
//                    kickoutSession.stop();
//                    sessionDAO.delete(kickoutSession);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return true;
    }

}
