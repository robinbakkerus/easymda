package blazeds.test;

import org.junit.Test;

import flca.blazeds.template.BlazeDsTemplates;
import flca.blazeds.template.TidBlazeDs;

public class GenerateMocksTest extends GenerateBaseTest {

	@Test 
	public void testGenerateKlantbeeldMockJavaGrpc() {
		for (Class<?> clz : MSG_CLASSES) {
			if (!clz.isEnum()) {
				String s = this.generate(clz, BlazeDsTemplates.getTemplate(TidBlazeDs.MOCK_JAVA_GRPC));
				System.out.println(s);
			}
		}
	}
	
	@Test
	public void testGenerateKlantbeeldMockJava() {
		for (Class<?> clz : MSG_CLASSES) {
			if (!clz.isEnum()) {
				String s = this.generate(clz, BlazeDsTemplates.getTemplate(TidBlazeDs.MOCK_JAVA));
				System.out.println(s);
			}
		}
	}
	
	@Test
	public void testGenerateKlantbeeldMockDart() {
		for (Class<?> clz : MSG_CLASSES) {
			if (!clz.isEnum()) {
				String s = this.generate(clz, BlazeDsTemplates.getTemplate(TidBlazeDs.MOCK_DART));
				System.out.println(s);
			}
		}
	}
}
