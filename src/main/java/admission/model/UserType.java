package admission.model;

public enum UserType {
    ADULT(new Adult()),
    CHILD(new Child());

    private IUserType userType;

    UserType(IUserType userType) {
        this.userType = userType;
    }

    public String japaneseTypeName() {
       return userType.getJapaneseType();
    }

}
