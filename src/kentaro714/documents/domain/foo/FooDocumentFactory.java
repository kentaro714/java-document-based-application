package kentaro714.documents.domain.foo;

import java.text.DateFormat;
import java.text.ParseException;

import kentaro714.documents.interfaces.FooInputBean;

public class FooDocumentFactory {
	public FooDocument createDocument(FooInputBean input) {
		FooDocument doc = new FooDocument();
		doc.setPropertyA(input.getPropA());
		doc.setPropertyB(Integer.parseInt(input.getPropB()));
//		try {
//			doc.setPropertyC(DateFormat.getInstance().parse(input.getPropC()));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return doc;
	}
}
