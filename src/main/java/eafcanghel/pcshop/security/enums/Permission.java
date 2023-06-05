package eafcanghel.pcshop.security.enums;

import lombok.*;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin.read"),
    ADMIN_UPDATE("admin.update"),
    ADMIN_CREATE("admin.create"),
    ADMIN_DELETE("admin.delete"),

    USER_READ("user.read"),
    USER_UPDATE("user.update"),
    USER_CREATE("user.create"),
    USER_DELETE("user.delete"),
    
    MANAGER_READ("management.read"),
    MANAGER_UPDATE("management.update"),
    MANAGER_CREATE("management.create"),
    MANAGER_DELETE("management.delete");
    @Getter
    private final String permission;
}




