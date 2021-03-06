package carstore.constants;

public enum WebApp {
    MSG_ERROR("error"),
    MSG_SUCCESS("success"),

    SRV_LOGIN("login"),

    VIEW_ROOT("/WEB-INF/view"),
    PG_SHOW_ALL_CARS("showAllCars.jsp"),
    PG_CREATE_USER("userEditForm.jsp"),
    PG_CREATE_CAR("carEditForm.jsp"),
    PG_EDIT_CAR("editCar.jsp"),
    PG_LOGIN("loginForn.jsp");

    private final String value;

    WebApp(String value) {
        this.value = value;
    }

    /**
     * Returns value.
     *
     * @return Value of value field.
     */
    public String v() {
        return this.value;
    }
}
