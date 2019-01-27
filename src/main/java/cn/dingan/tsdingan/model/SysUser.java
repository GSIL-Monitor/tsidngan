package cn.dingan.tsdingan.model;

public class SysUser {
    private String id;


    private String username;

    private String phone;

    private String email;

    private String account;

    private String accountType;

    private String driverSchoolId;
    
    private String password;
    
    private String isactivation;
    
    public String getIsactivation() {
		return isactivation;
	}

	public void setIsactivation(String isactivation) {
		this.isactivation = isactivation;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getDriverSchoolId() {
        return driverSchoolId;
    }

    public void setDriverSchoolId(String driverSchoolId) {
        this.driverSchoolId = driverSchoolId;
    }
}