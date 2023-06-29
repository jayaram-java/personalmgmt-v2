package com.company.Personalmgmt.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.company.Personalmgmt.dto.GeneralDto;

@Repository
public class GeneralStoredProcedureCall {

	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	/*@SuppressWarnings("unused")
	public List<GeneralDto> getBalanceSheetDetails(String month, String year) {
		
		List<GeneralDto> generalDtos = new ArrayList<GeneralDto>();

		String[] balanceSheet_result = new String[2];

		String balanceSheetDetails = "{CALL expense_trans(?,?)}";

		Connection databaseConnection = null;
		CallableStatement callableStatement = null;
		ResultSet resultset = null;

		String name = "";
		String mode = "";

		try {

			databaseConnection = jdbcTemplate.getDataSource().getConnection();

			callableStatement = databaseConnection.prepareCall(balanceSheetDetails);
			callableStatement.setString(1, month);
			callableStatement.setString(2, year);
			
			callableStatement.registerOutParameter(3, Types.VARCHAR);
			callableStatement.registerOutParameter(4, Types.VARCHAR);
			callableStatement.registerOutParameter(5,Types.DOUBLE);

			callableStatement.executeUpdate();
			
			System.out.println(callableStatement.getFetchSize());
			System.out.println(callableStatement.getFetchSize());
			
			while (resultset.next()) {
				
				GeneralDto generalDto = new GeneralDto();

				name = resultset.getString("name");
				mode = resultset.getString("mode");
				
				generalDto.setMode(mode);
				generalDto.setType(name);
				
				generalDtos.add(generalDto);
			}
			
			balanceSheet_result[0] = name;
			balanceSheet_result[1] = mode;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				
				callableStatement.close();
				databaseConnection.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return generalDtos;

	}*/	

}
