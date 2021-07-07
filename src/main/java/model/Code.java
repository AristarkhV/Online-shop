package model;

import java.util.Objects;

public class Code {

    private Long codeID;
    private String email;
    private String code;
    private Long orderID;

    public Code(Long codeID, String code, Long orderID, String email) {
        this.codeID = codeID;
        this.email = email;
        this.code = code;
        this.orderID = orderID;
    }

    public Code(String code, Long orderID, String email) {
        this.codeID = IdCreator.idCreator();
        this.email = email;
        this.code = code;
        this.orderID = orderID;
    }

    public Long getCodeID() {
        return codeID;
    }

    public void setCodeID(Long codeID) {
        this.codeID = codeID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Code)) return false;
        Code code1 = (Code) o;
        return Objects.equals(codeID, code1.codeID) &&
                Objects.equals(email, code1.email) &&
                Objects.equals(code, code1.code) &&
                Objects.equals(orderID, code1.orderID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeID, email, code, orderID);
    }

    @Override
    public String toString() {
        return "Code{" +
                "codeID=" + codeID +
                ", email='" + email + '\'' +
                ", code='" + code + '\'' +
                ", orderID=" + orderID +
                '}';
    }
}
