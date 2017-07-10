package com.drones4hire.dronesapp.services.services.util;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.SearchResult;
import com.drones4hire.dronesapp.models.db.payments.WithdrawRequest;
import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.services.util.model.ProjectCSVModel;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class CSVWriter
{

	private static final String [] USERS_CSV_HEADER = {"id", "username", "email", "firstName", "lastName", "rating", "confirmed", "enabled", "createdAt"};
	private static final CellProcessor[] USERS_CELL_PROCESSORS = new CellProcessor[] {
			new NotNull(), // id
			new NotNull(), // username
			new NotNull(), // email
			new NotNull(), // firstName
			new NotNull(), // lastName
			new Optional(),// rating
			new NotNull(), // confirmed
			new NotNull(), // enabled
			new NotNull()  // createdAt
	};

	private static final String [] PROJECTS_CSV_HEADER = {"id", "title", "clientEmail", "pilotEmail", "duration", "budget", "status", "country", "city", "service", "startDate", "finishDate", "createdAt"};
	private static final CellProcessor[] PROJECTS_CELL_PROCESSORS = new CellProcessor[] {
			new NotNull(), // id
			new NotNull(), // title
			new NotNull(), // clientEmail
			new Optional(),// pilotEmail
			new Optional(),// duration
			new NotNull(), // budget
			new NotNull(), // status
			new NotNull(), // country
			new NotNull(), // city
			new NotNull(), // service
			new NotNull(), // startDate
			new Optional(),// finishDate
			new NotNull()  // createdAt
	};

	private static final String [] WITHDRAW_REQUESTS_CSV_HEADER = {"id", "userId", "amount", "currency", "comment", "adminComment", "status", "modifiedAt", "createdAt"};
	private static final CellProcessor[] WITHDRAW_REQUESTS_CELL_PROCESSORS = new CellProcessor[] {
			new NotNull(), // id
			new NotNull(), // userId
			new NotNull(), // amount
			new NotNull(), // currency
			new Optional(),// comment
			new Optional(),// adminComment
			new NotNull(), // status
			new NotNull(), // modifiedAt
			new NotNull()  // createdAt
	};

	public static void exportUsersToCSV(SearchResult<User> searchResult, Writer writer) throws ServiceException,
			IOException
	{
		try
		{
			ICsvBeanWriter beanWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE);
			beanWriter.writeHeader(USERS_CSV_HEADER);
			for(User user : searchResult.getResults())
			{
				beanWriter.write(user, USERS_CSV_HEADER, USERS_CELL_PROCESSORS);
			}
			beanWriter.close();
		}
		catch(Exception e)
		{
			throw new ServiceException("Users not exported to CSV!" + e.getMessage());
		}
	}

	public static void exportProjectsToCSV(List<ProjectCSVModel> searchResult, Writer writer) throws ServiceException,
			IOException
	{
		try
		{
			ICsvBeanWriter beanWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE);
			beanWriter.writeHeader(PROJECTS_CSV_HEADER);
			for(ProjectCSVModel result : searchResult)
			{
				beanWriter.write(result, PROJECTS_CSV_HEADER, PROJECTS_CELL_PROCESSORS);
			}
			beanWriter.close();
		}
		catch(Exception e)
		{
			throw new ServiceException("Projects not exported to CSV!" + e.getMessage());
		}
	}

	public static void exportWithdrawToCSV(SearchResult<WithdrawRequest> searchResult, Writer writer) throws ServiceException,
			IOException
	{
		try
		{
			ICsvBeanWriter beanWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE);
			beanWriter.writeHeader(WITHDRAW_REQUESTS_CSV_HEADER);
			for(WithdrawRequest withdrawRequest : searchResult.getResults())
			{
				beanWriter.write(withdrawRequest, WITHDRAW_REQUESTS_CSV_HEADER, WITHDRAW_REQUESTS_CELL_PROCESSORS);
			}
			beanWriter.close();
		}
		catch(Exception e)
		{
			throw new ServiceException("Withdraw requests not exported to CSV!" + e.getMessage());
		}
	}
}
