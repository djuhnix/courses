package courses.server.security;

public enum PermissionsEnum {
    ALL ("*"),
    READ ("read"),
    POST ("post"),
    UPDATE ("update"),
    DELETE ("delete");

    private final String permissionName;

    private PermissionsEnum(String name) {
        permissionName = name;
    }

    public String getPermissionName() {
        return permissionName;
    }
}
