package siteLocal;
import org.json.JSONObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cristi
 */
public class User implements IData{
    protected String firstName;
    protected String email;
    protected String lastName;
    protected String password;
    protected String id;

    private void init( String firstName, String email, String lastName, String password, String id)
    {
        this.firstName = firstName;
        this.email = email;
        this.lastName = lastName;
        this.password = password;
        this.id = id;
    }

    public User()
    {
        super();
        init(null,null,null,null,null);
    }

    public User( String firstName, String email, String lastName, String password, String id) {
        super();
        init(firstName,email,lastName,password,id);
    }

    public User( String firstName, String email, String lastName, String password) {
        super();
        init(firstName,email,lastName,password,null);
    }

    @Override
    public void decode(JSONObject json) {
        try {
            this.firstName = (String) json.get("FirstName");
            this.lastName = (String) json.get("LastName");
            this.password = (String) json.get("Password");
            this.id = (String) json.get("ID");
            this.email = (String) json.get("Email");

        } catch (org.json.JSONException e) {
        }

    }

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", email=" + email + ", lastName=" + lastName + ", password=" + password
				+ ", id=" + id + "]";
	}


}
