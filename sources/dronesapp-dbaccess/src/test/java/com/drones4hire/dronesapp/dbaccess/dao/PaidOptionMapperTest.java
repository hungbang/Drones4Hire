package com.drones4hire.dronesapp.dbaccess.dao;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNull;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.PaidOptionMapper;
import com.drones4hire.dronesapp.models.db.commons.Currency;
import com.drones4hire.dronesapp.models.db.projects.PaidOption;

@Test
@ContextConfiguration("classpath:com/drones4hire/dronesapp/dbaccess/dbaccess-test.xml")
public class PaidOptionMapperTest extends AbstractTestNGSpringContextTests
{
	/**
     * Turn this on to enable this test
     */
    private static final boolean ENABLED = false;

	@Autowired
	private PaidOptionMapper paidOptionMapper;

	private static final PaidOption PAID_OPTION = new PaidOption()
	{

		private static final long serialVersionUID = 1L;
		{
			setTitle("PO1");
			setDescription("D1");
			setPrice(new BigDecimal(10.00D));
			setCurrency(Currency.USD);
		}
	};

	@Test(enabled = ENABLED)
	public void testCreatePaidOption()
	{
		paidOptionMapper.createPaidOption(PAID_OPTION);
		assertNotEquals(PAID_OPTION.getId(), 0, "PaidOption id not autogenerated");
	}

	@Test(enabled = ENABLED, dependsOnMethods = { "testCreatePaidOption" })
	public void testGetPaidOptionById()
	{
		check(paidOptionMapper.getPaidOptionById(PAID_OPTION.getId()));
	}

	@Test(enabled = ENABLED, dependsOnMethods = { "testGetPaidOptionById" })
	public void testGetAllPaidOptions()
	{
		List<PaidOption> paidOptions = paidOptionMapper.getAllPaidOptions();
		check(paidOptions.get(0));
	}

	@Test(enabled = ENABLED, dependsOnMethods = { "testGetAllPaidOptions" })
	public void testUpdatePaidOption()
	{
		PAID_OPTION.setTitle("PO2");
		PAID_OPTION.setDescription("D2");
		PAID_OPTION.setPrice(new BigDecimal(20.00D));
		PAID_OPTION.setCurrency(Currency.EUR);
		paidOptionMapper.updatePaidOption(PAID_OPTION);
		check(paidOptionMapper.getPaidOptionById(PAID_OPTION.getId()));
	}

	@Test(enabled = ENABLED, dependsOnMethods = { "testUpdatePaidOption" })
	public void testDeletePaidOption()
	{
		paidOptionMapper.deletePaidOption(PAID_OPTION.getId());
		assertNull(paidOptionMapper.getPaidOptionById(PAID_OPTION.getId()));
	}

	private void check(PaidOption paidOption)
	{
		assertEquals(paidOption.getId(), PAID_OPTION.getId(), "PaidOption id must match");
		assertEquals(paidOption.getPrice().compareTo(PAID_OPTION.getPrice()), 0, "PaidOption price must match");
		assertEquals(paidOption.getCurrency(), PAID_OPTION.getCurrency(), "PaidOption currency must match");
		assertEquals(paidOption.getTitle(), PAID_OPTION.getTitle(), "PaidOption title must match");
		assertEquals(paidOption.getDescription(), PAID_OPTION.getDescription(), "PaidOption description must match");
	}
}
