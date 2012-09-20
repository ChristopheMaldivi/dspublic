package com.dailystudio.dataobject.query;

import com.dailystudio.dataobject.Column;
import com.dailystudio.dataobject.query.QueryToken;
import com.dailystudio.dataobject.DoubleColumn;
import com.dailystudio.dataobject.Template;
import com.dailystudio.dataobject.IntegerColumn;
import com.dailystudio.dataobject.TextColumn;
import com.dailystudio.test.ActivityTestCase;

public class ExpressionTokenTest extends ActivityTestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testCreateExpressionToken() {
		ExpressionToken token = null;

		token = new ExpressionToken(1234);
		assertEquals(new QueryToken("1234"), token);

		token = new ExpressionToken(3.1415);
		assertEquals(new QueryToken("3.1415"), token);

		token = new ExpressionToken("a < 10");
		assertEquals(new QueryToken("a < 10"), token);
	}
	
	public void testAndOperation() {
		Column column = null;
		
		column = new IntegerColumn("intVal");
		assertNotNull(column);
		
		ExpressionToken token = null;
		token = column.gt(1000);
		assertEquals(new QueryToken("intVal > 1000"), token);
		
		token.and(column.lt(2000));
		assertEquals(new QueryToken("( intVal > 1000 ) AND ( intVal < 2000 )"), token);
	}
	
	public void testOrOperation() {
		Column column = null;
		
		column = new IntegerColumn("intVal");
		assertNotNull(column);
		
		ExpressionToken token = null;
		token = column.lt(1000);
		assertEquals(new QueryToken("intVal < 1000"), token);
		
		token.or(column.gt(2000));
		assertEquals(new QueryToken("( intVal < 1000 ) OR ( intVal > 2000 )"), token);
	}

	public void testSamples() {
		Template templ = new Template();
		assertNotNull(templ);
		
		templ.addColumn(new IntegerColumn("_id", false, true));
		templ.addColumn(new IntegerColumn("intValue"));
		templ.addColumn(new DoubleColumn("doubleValue"));
		templ.addColumn(new TextColumn("textValue"));
		
		
		assertEquals(new QueryToken("( intValue > 1000 ) AND ( intValue < 2000 )"),
				templ.getColumn("intValue").gt(1000).and(templ.getColumn("intValue").lt(2000)));
	}
	
}
