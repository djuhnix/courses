package courses.server.security;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public enum RolesEnum {
    ADMIN ("admin", new RolesScopesEnum[]{RolesScopesEnum.ALL}),
    TEACHER (
            "teacher",
            new RolesScopesEnum[]{
                    RolesScopesEnum.ACTIVITIES.setPermissionsEnums(
                            new PermissionsEnum[]{PermissionsEnum.ALL}
                    ),
                    RolesScopesEnum.GRADUATION.setPermissionsEnums(
                            new PermissionsEnum[]{PermissionsEnum.ALL}
                    ),
            }
    ),
    STUDENT (
            "student",
            new RolesScopesEnum[]{
                    RolesScopesEnum.ACTIVITIES.setPermissionsEnums(
                            new PermissionsEnum[]{PermissionsEnum.READ, PermissionsEnum.UPDATE}
                    ),
                    RolesScopesEnum.GRADUATION.setPermissionsEnums(
                            new PermissionsEnum[]{PermissionsEnum.READ, PermissionsEnum.UPDATE}
                    ),
            }
    );

    private final String roleName;

    private final RolesScopesEnum[] scopesEnums;

    RolesEnum(String role, RolesScopesEnum[] scopes) {
        roleName = role;
        scopesEnums = scopes;
    }

    public String getRoleName() {
        return roleName;
    }

    public Set<String> getPermissionsSet() {
        ArrayList<String> perm = new ArrayList<>();
        for (RolesScopesEnum scopesEnum : scopesEnums) {
            for (PermissionsEnum permissionsEnum : scopesEnum.getPermissionsEnums()) {
                perm.add(scopesEnum.getName() + ":" + permissionsEnum);
            }
        }
        return new HashSet<>(perm);
    }

    public PermissionsEnum[] getPermissions(RolesScopesEnum scope) {
        PermissionsEnum[] permissions = null;
        Optional<RolesScopesEnum> optionalRolesScopesEnum = Arrays.stream(scopesEnums)
                .filter(x -> x.getName().equals(scope.getName()))
                .findFirst();
        if (optionalRolesScopesEnum.isPresent())
            permissions = optionalRolesScopesEnum.get().getPermissionsEnums();
        return  permissions;
    }

    public RolesScopesEnum[] getScopesEnums() {
        return scopesEnums;
    }
}
