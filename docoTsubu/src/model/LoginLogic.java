package model;

import dao.AccountDAO;

public class LoginLogic {

//	public boolean execute(User user) {
//		if(user.getPass().equals("1234")) { return true; }
//		return false;
//	}

	public boolean execute(User user) {
		AccountDAO dao = new AccountDAO();
		Account account = dao.findByLogin(user);
		return account != null;
	}

}
