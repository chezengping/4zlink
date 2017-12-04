package com.dache.security.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dache.security.commonstatic.StaticParams;
import com.dache.security.module.user.domain.SystemUser;
import com.dache.security.module.user.domain.UserRole;
import com.dache.security.module.user.service.SystemUserService;
import com.dache.security.module.user.service.UserRoleService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class ScheduleTask {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleTask.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Resource(name = "SystemUserServiceImpl")
    private SystemUserService systemUserService;

    @Resource(name = "UserRoleServiceImpl")
    private UserRoleService userRoleService;


    @Scheduled(fixedRate = 50000)
    public void reportCurrentTime() {
        logger.info("The time is now " + dateFormat.format(new Date()));
    }

    @PostConstruct
    public void init() {
        try {
            logger.info("start initializing...");
            systemUserService.clearTable();
            userRoleService.clearTable();
            SystemUser user = new SystemUser();
            user.setPassword("user");
            user.setUserName("user");
            systemUserService.save(user);
            SystemUser admin = new SystemUser();
            admin.setPassword("admin");
            admin.setUserName("admin");
            systemUserService.save(admin);

            List<SystemUser> users = systemUserService.findAll();

            for(SystemUser userItem : users){
                if(user.getUserName().equals("user")){
                    UserRole userRole = new UserRole();
                    userRole.setRole(StaticParams.USERROLE.ROLE_USER);
                    userRole.setUserId(userItem.getId());
                    userRoleService.save(userRole);
                }
                if(user.getUserName().equals("admin")){
                    UserRole adminRole = new UserRole();
                    adminRole.setRole(StaticParams.USERROLE.ROLE_ADMIN);
                    adminRole.setUserId(userItem.getId());
                    userRoleService.save(adminRole);
                }
            }
            logger.info("initialization finished.");
        } catch (Exception e) {
            logger.error(String.format("ERROR when initializing:%s", e));
        }

    }

}
