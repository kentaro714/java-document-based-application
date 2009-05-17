package kentaro714.documents.domain.foo;

import junit.framework.Assert;
import kentaro714.documents.domain.ValidationErrors;
import kentaro714.documents.interfaces.FooInputBean;

import org.junit.Test;


public class FooDocumentTest {
	
	@Test
	public void testFoo() {
		FooInputBean input = new FooInputBean();
		input.setPropA("A");
		input.setPropB("3");
		input.setPropC("2009/04/04");
		
		FooDocument doc = new FooDocumentFactory().createDocument(input);
		ValidationErrors errors = new ValidationErrors();
		doc.proceed(errors);
		Assert.assertEquals("Accepted", doc.status());
		doc.proceed(errors);
		Assert.assertEquals("Approved", doc.status());
	}
}
