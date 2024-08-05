package cz.kohnh.moro.config;

import cz.kohnh.moro.users.User;
import cz.kohnh.moro.users.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * User can work only with himself data.
 */
@RequiredArgsConstructor
@Component
public class UserInterceptor implements HandlerInterceptor {

    private final UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String username = getCurrentUsername();

        String strUserIdParam = request.getServletPath();
        Integer userIdParam = Integer.valueOf(strUserIdParam.substring(strUserIdParam.lastIndexOf("/") + 1));

        if (userIdParam != null) {
            User user = userMapper.selectUserById(userIdParam);

            if (user != null && !user.getUsername().equals(username)) {
                throw new AccessDeniedException("Access Denied");

            }
        }
        return true;
    }


    private String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            return userDetails.getUsername();
        } else {
            return principal.toString();
        }
    }
}
