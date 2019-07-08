package com.flca.frw.test;

import java.util.Date;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import com.flca.frw.context.RequestContext;


public class TestFlcaContextJsonMapper
{

	private static ObjectMapper om = new ObjectMapper();
	
	private static String jsonString;
	
	@Test 
	public void testSearialize()
	{
		RequestContext context = new RequestContext();
		context.setServicename("servicename");
		context.setStartetAt(new Date());
		
		try {
			jsonString = om.writeValueAsString(context);
			Assert.assertTrue(jsonString != null && jsonString.length() > 0);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		} 
	}
	
	@Test
	public void testDeserialize() 
	{
		try {
			RequestContext context2 = om.readValue(jsonString, RequestContext.class);
			Assert.assertTrue(context2 != null);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		} 
				}
}
