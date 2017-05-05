package com.sanyam.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.sanyam.dao.UserDao;
import com.sanyam.entity.User;
import com.sanyam.entity.UserDetails;
import com.sanyam.utility.AESencrp;
import com.sanyam.utility.Constants;

public class UserDaoImpl implements UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public String register(UserDetails user) {

		String response = "";
		String sql = "insert into users values(?,?,?,?,?,?,?)";
		try{
			int count = jdbcTemplate.update(sql, new Object[] { user.getUsername(), AESencrp.encrypt(user.getPassword()), user.getFirstname(),user.getLastname(), user.getEmail(), user.getAddress(), user.getPhone() });
			if(count>0){
				response = Constants.SUCCESS;
			}
			else{
				response = Constants.FAILURE;
			}
		}
		catch(Exception e){
			//to check duplicate entry of username
			if(e.getMessage().contains("Duplicate")){
				response = Constants.DUPLICATE;
			}
			//if registeration fails due to some other reason
			else{
				response = Constants.FAILURE;
			}
		}
		return response;

	}

	public UserDetails validateUser(User login) {
		List<UserDetails> userDetails = new ArrayList<UserDetails>();
		try{
			String sql = "select * from users where username='"+login.getUsername()+"' and password='"+AESencrp.encrypt(login.getPassword())+ "'";
			userDetails = jdbcTemplate.query(sql, new UserMapper());
		}catch(Exception e){
			e.printStackTrace();
		}
		return userDetails.size() > 0 ? userDetails.get(0) : null;

	}

}

class UserMapper implements RowMapper<UserDetails> {

	public UserDetails mapRow(ResultSet rs, int arg1) throws SQLException {
		UserDetails userDetails = new UserDetails();
		userDetails.setUsername(rs.getString("username"));
		try{
			userDetails.setPassword(AESencrp.decrypt(rs.getString("password")));
		}catch(Exception e){
			e.printStackTrace();
		}
		userDetails.setFirstname(rs.getString("firstname"));
		userDetails.setLastname(rs.getString("lastname"));
		userDetails.setEmail(rs.getString("email"));
		userDetails.setAddress(rs.getString("address"));
		userDetails.setPhone(rs.getString("phone"));
		return userDetails;

	}

}