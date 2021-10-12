package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;
import model.User;

public class AccountDAO {

	//データベース接続に使用する情報
	private final String JDBC_URL="jdbc:h2:tcp://localhost/~/docoTsubu";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	public Account findByLogin(User user) {
		Account account = null;

		//データベースへ接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){

			//SELECT文を準備
			String sql = "SELECT USER_ID, PASS, MAIL, NAME, AGE FROM ACCOUNT WHERE USER_ID = ? AND PASS = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1,  user.getName());
			pStmt.setString(2, user.getPass());

			//SELECT文を実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			//一致したユーザーが存在した場合
			//そのユーザーを表すAccountインスタンスを生成
			if(rs.next()) {
				//結果表からデータを取得
				String userId = rs.getString("USER_ID");
				String pass = rs.getString("PASS");
				String mail = rs.getString("MAIL");
				String name = rs.getString("NAME");
				int age = rs.getInt("AGE");
				account = new Account(userId, pass, mail, name, age);
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return null;
		}
		//見つかったユーザまたはnullを返す
		return account;
	}

//		今回はめんどくさいので、新規登録機能ははしょってます
//	public boolean register(Account account) {
//
//		//データベースへ接続
//		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
//
//			//INSERT文の準備(idは自動連番なので指定しなくてよい)
//			String sql = "INSERT INTO ACCOUNT(USER_ID, PASS, MAIL, NAME, AGE) VALUES(?,?,?,?,?)";
//			PreparedStatement pStmt = conn.prepareStatement(sql);
//			pStmt.setString(1,  account.getUserId());
//			pStmt.setString(2, account.getPass());
//			pStmt.setString(3,  account.getMail());
//			pStmt.setString(4, account.getName());
//			pStmt.setInt(5, account.getAge());
//
//			int result = pStmt.executeUpdate();
//
//			if(result != 1) {
//				return false;
//			}
//
//		}catch(SQLException e) {
//			e.printStackTrace();
//			return false;
//		}
//		return true;
//	}
}
