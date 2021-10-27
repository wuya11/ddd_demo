package com.ddd.demo.interceptor;//package com.trace.base.tool.interceptor;
//
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.trace.base.tool.domain.base.User;
//import com.trace.base.tool.util.ObjectMapperUtil;
//import com.trace.base.tool.util.StringConst;
//import com.trace.base.tool.util.ThreadHolderUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.util.StringUtils;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Optional;
//
//
///**
// * 用户信息上下文内容拦截器
// *
// * @author ty
// */
//public class UserContextInterceptor extends HandlerInterceptorAdapter {
//    private static final Logger LOGGER = LoggerFactory.getLogger(UserContextInterceptor.class);
//    @Deprecated
//    public static final String GATEWAY_USER_HEADER = "X-Gateway-User";
//    public static final String PAYLOAD_KEY = ThreadHolderUtil.PAYLOAD_KEY;
//    private final ObjectMapper objectMapper;
//
//    public UserContextInterceptor() {
//        objectMapper = ObjectMapperUtil.getSnakeObjectMapper();
//    }
//
//    public UserContextInterceptor(ObjectMapper objectMapper) {
//        this.objectMapper = objectMapper;
//    }
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        // 初始化先清空
//        ThreadHolderUtil.clearUser();
//        // 网关传递的用户信息
//        String userHeader = request.getHeader(StringConst.GATEWAY_USER_HEADER);
//        if (!StringUtils.isEmpty(userHeader)) {
//            try {
//                int uid = 0;
//                long acId = 0L;
//                String name = "";
//                JsonNode rootNode = objectMapper.readTree(userHeader);
//                if (rootNode != null) {
//                    JsonNode userNode = rootNode.get("user");
//                    if (userNode != null) {
//                        JsonNode uidNode = Optional.ofNullable(userNode.get("userId")).orElse(userNode.get("user_id"));
//                        if (uidNode != null) {
//                            uid = uidNode.asInt();
//                        }
//                        JsonNode acIdNode = Optional.ofNullable(userNode.get("acId")).orElse(userNode.get("ac_id"));
//                        if (acIdNode != null) {
//                            acId = acIdNode.asLong();
//                        }
//                        JsonNode nameNode = Optional.ofNullable(userNode.get("userName")).orElse(userNode.get("user_name"));
//                        if (nameNode != null) {
//                            name = nameNode.asText();
//                        }
//                    }
//                }
//                User user = new User();
//                user.setAcId(acId);
//                user.setUserId(uid);
//                user.setUserName(name);
//                ThreadHolderUtil.setUser(user);
//                ThreadHolderUtil.setValue(PAYLOAD_KEY, userHeader);
//                LOGGER.debug("userId:{}访问{},方法{}", user.getUserId(), request.getRequestURL().toString(), request.getMethod());
//            } catch (IOException e) {
//                // TODO 忽略
//            }
//
//        }
//        return true;
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//        ThreadHolderUtil.clearUser();
//    }
//}
