package com.revature.project.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.project.ds.ArrayList;
import com.revature.project.ds.List;
import com.revature.project.models.Account;
import com.revature.project.utils.ConnectionUtil;

public class AccountPostgres implements AccountDAO {
	private ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();

	@Override
	public Account create(Account t) {
		try (Connection conn = connUtil.getConnection()) {
			conn.setAutoCommit(false);

			String sql = "insert into account(name, balance) values (default, ?, ?)";
			String[] keys = { "id" };

			PreparedStatement stmt = conn.prepareStatement(sql, keys);
			stmt.setString(1, t.getName());
			stmt.setDouble(2, t.getBalance());

			int rowsAffected = stmt.executeUpdate();
			ResultSet resultSet = stmt.getGeneratedKeys();
			if (resultSet.next() && rowsAffected == 1) {
				t.setId(resultSet.getInt("id"));
				conn.commit();
			} else {
				conn.rollback();
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return t;
	}

	@Override
	public Account findById(int id) {
		Account account = null;
		try (Connection conn = connUtil.getConnection()) {
			String sql = "select " + "account.id as account_id, " + "account.name, " + "account.balance "
					+ "from account " + "where account.id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet resultSet = stmt.executeQuery();

			if (resultSet.next()) {
				String name = resultSet.getString("account_name");
				double balance = resultSet.getDouble("balance");

				account = new Account(name, balance);
				account.setId(id);
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
			String sql = "select " + "account.id as account_id, " + "account.name, " + "account.balance"
					+ "from account";

			Statement stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery(sql);

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				double balance = resultSet.getDouble("balance");

				Account account = new Account(name, balance);
				account.setId(id);

				allAccounts.add(account);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allAccounts;
	}

	@Override
	public void update(Account t) {
		try (Connection conn = connUtil.getConnection()) {
			conn.setAutoCommit(false);

			String sql = "update account " + "set name=?, " + "balance=? " + "where id=?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, t.getName());
			stmt.setDouble(2, t.getBalance());

			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected <= 1) {
				conn.commit();
			} else {
				conn.rollback();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Account t) {
		try (Connection conn = connUtil.getConnection()) {
			conn.setAutoCommit(false);

			String sql = "delete from account where id=?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, t.getId());

			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected <= 1) {
				conn.commit();
			} else {
				conn.rollback();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
