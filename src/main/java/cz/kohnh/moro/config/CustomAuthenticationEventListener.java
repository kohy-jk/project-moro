package cz.kohnh.moro.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationEventListener implements ApplicationListener<AbstractAuthenticationEvent> {
    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationEventListener.class);

    @Override
    public void onApplicationEvent(AbstractAuthenticationEvent event) {
        if (event instanceof AuthenticationFailureBadCredentialsEvent) {
            logger.warn("Authentication failed: {}", event.getAuthentication().getPrincipal());
        } else if (event instanceof InteractiveAuthenticationSuccessEvent) {
            logger.info("Authentication successful: {}", event.getAuthentication().getName());
        }
    }
}
