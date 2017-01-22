package ua.softserveinc.tc.constants;



public final class ApiConstants {

    public static final String USER_REST_URL = "/api/user";
    public static final String USER_REST_BY_ID_URL = "/api/user/{id}";
    public static final String CHILD_REST_URL = "/api/child";
    public static final String CHILD_BY_ID_REST_URL = "/api/child/{id}";
    public static final String GET_CHILD_PARENT_REST_URL = "/api/child/{id}/parent";
    public static final String GET_ACTIVE_CHILDREN_IN_ROOM_URL = "/api/room/{roomId}/children";
    public static final String GET_APP_CONFIGURATION = "/api/configuration";
    public static final String GET_APP_LOCALIZATION = "/api/localization/{locale}";

    private ApiConstants() {
    }
}
