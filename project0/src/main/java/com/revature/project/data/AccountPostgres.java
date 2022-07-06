package com.revature.project.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.project.ds.ArrayList;
import com.revature.project.ds.List;
import com.revature.project.models.Account;
import com.revature.project.models.Beneficiary;
import com.revature.project.utils.ConnectionUtil;

public class AccountPostgres implements AccountDAO {
	private ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();

	@Override
	public Account create(Account account) {
		try (Connection conn = connUtil.getConnection()) {
			conn.setAutoCommit(false);

			String sql = "insert into account(id, account_name, description, balance, user_id)"
					+ " values (default, ?, ?, ?, ?)";
			String[] keys = { "id" };

			PreparedStatement stmt = conn.prepareStatement(sql, keys);
			stmt.setString(1, account.getName());
			stmt.setString(2, account.getDescription());
			stmt.setDouble(3, account.getBalance());
			stmt.setInt(4, account.getId());

			int rowsAffected = stmt.executeUpdate();
			ResultSet resultSet = stmt.getGeneratedKeys();
			if (resultSet.next() && rowsAffected == 1) {
				account.setId(resultSet.getInt("id"));
				conn.commit();
			} else {
				conn.rollback();
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return account;
	}

	@Override
	public Account findById(int id) {
		Account account = null;
		try (Connection conn = connUtil.getConnection()) {
			String sql = "select \n"
					+ "	person.id  as user_id, \n"
					+ "	account.id as account_id, \n"
					+ "	account.account_name, \n"
					+ "	account.description, \n"
					+ "	account.balance, \n"
					+ "	beneficiary.beneficiary_name \n"
					+ "from person \n"
					+ "	inner join account on account.user_id = person.id \n"
					+ "	inner join beneficiary on beneficiary.account_id = account.id \n"
					+ "where account.id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet resultSet = stmt.executeQuery();

			if (resultSet.next()) {
				String name = resultSet.getString("account_name");
				double balance = resultSet.getDouble("balance");
				String description = resultSet.getString("description");

				Beneficiary bene = new Beneficiary(resultSet.getInt("id"), resultSet.getString("name"));

				account = new Account(name, description, balance, bene);
				account.setId(id);
				account.setBeneficiary(bene);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return account;
	}

	@Override
	public List<Account> findAll() {
		List<Account> allAccounts = new ArrayList();
		try (Connection conn = connUtil.getConnection()) {
			String sql = "select\r\n" + "	person.id  as user_id,\r\n" + "	account.id as account_id,\r\n"
					+ "	account.account_name,\r\n" + "	account.description,\r\n" + "	account.balance,\r\n"
					+ "	beneficiary.beneficiary_name\r\n" + "from person\r\n"
					+ "	inner join account on account.user_id = person.id\r\n"
					+ "	inner join beneficiary on beneficiary.account_id = account.id \r\n" + "order by account.id asc";
			Statement stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery(sql);

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				double balance = resultSet.getDouble("balance");
				String description = resultSet.getString("description");

				Beneficiary beneficiary = new Beneficiary(resultSet.getInt("id"), resultSet.getString("name"));

				Account account = new Account(name, description, balance, beneficiary);
				account.setId(id);
				account.setBeneficiary(beneficiary);

				allAccounts.add(account);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allAccounts;
	}

	@Override
	public void update(Account t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Account t) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Account> findByBeneficiary(Beneficiary beneficiary) {
		// TODO Auto-generated method stub
		return null;
	}

}
