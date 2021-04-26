package courses.server.security;

public enum RolesScopesEnum {
    ALL ("*", new PermissionsEnum[]{
            PermissionsEnum.ALL
    }),
    USER ("user", new PermissionsEnum[0]),
    ACTIVITIES ("activities", new PermissionsEnum[0]),
    GRADUATION ("graduation",  new PermissionsEnum[0]);

    private final String name;
    private PermissionsEnum[] permissionsEnums;

    RolesScopesEnum(String name, PermissionsEnum[] permissionsEnums) {
        this.name = name;
        this.permissionsEnums = permissionsEnums;
    }

    public RolesScopesEnum setPermissionsEnums(PermissionsEnum[] permissionsEnums) {
        this.permissionsEnums = permissionsEnums;
        return this;
    }

    public String getName() {
        return name;
    }

    public PermissionsEnum[] getPermissionsEnums() {
        return permissionsEnums;
    }

    public int getPermissionSize() {
        return permissionsEnums.length;
    }
}
