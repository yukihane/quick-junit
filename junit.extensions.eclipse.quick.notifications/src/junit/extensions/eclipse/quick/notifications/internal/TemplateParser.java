package junit.extensions.eclipse.quick.notifications.internal;

import org.eclipse.jdt.junit.model.ITestRunSession;

import static junit.extensions.eclipse.quick.notifications.internal.TemplateKey.*;

class TemplateParser {

	private String template;
	private TestCounter counter = new TestCounter();
	
	public TemplateParser() {
	}

	public String parseTemplate(ITestRunSession session) {
		String result = null;
		result = replaceResult(session,template);
		result = replaceCount(session,result);
		return result;
	}


	private String replaceCount(ITestRunSession session, String result) {
		counter.count(session);
		result = result.replaceAll(key(TOTAL_COUNT), String.valueOf(counter.getTotalTests()));
		result = result.replaceAll(key(OK_COUNT), String.valueOf(counter.getOKTests()));
		result = result.replaceAll(key(FAIL_COUNT), String.valueOf(counter.getFailureTests()));
//		result = result.replaceAll(key(IGNORE_COUNT), String.valueOf(counter.getIgnoreTests()));
		result = result.replaceAll(key(ERROR_COUNT), String.valueOf(counter.getErrorTests()));
		return result;
	}

	private String replaceResult(ITestRunSession session,String target) {
		String result = target.replaceAll(key(RESULT_COUNT), session.getTestResult(true).toString());
		return result;
	}
	
	private String key(TemplateKey key){
		return key.regexKey();
	}
	
	public void setTemplate(String template) {
		this.template = template;
	}
	
	void setCounter(TestCounter counter) {
		this.counter = counter;
	}
	
}
