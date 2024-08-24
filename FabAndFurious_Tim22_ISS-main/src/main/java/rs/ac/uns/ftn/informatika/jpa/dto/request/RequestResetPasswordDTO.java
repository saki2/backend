package rs.ac.uns.ftn.informatika.jpa.dto.request;

public class RequestResetPasswordDTO {

    private String oldPassword;
    private String newPassword;

    public RequestResetPasswordDTO() {
    }

    public RequestResetPasswordDTO(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
