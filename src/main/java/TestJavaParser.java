import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import dto.ClassInterfaceDTO;
import dto.DTOParser;
import dto.DTOs;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import reports.interfaces.ClassReport;
import reports.interfaces.InterfaceReport;
import view.GUIView;
import view.ViewListener;

class MethodNameCollector extends VoidVisitorAdapter<List<String>> {
  public void visit(MethodDeclaration md, List<String> collector) {
	  super.visit(md, collector);
	  collector.add(md.getNameAsString());
  }
}

class FullCollector extends VoidVisitorAdapter<Void> {

	public void visit(PackageDeclaration fd, Void collector) {
		super.visit(fd, collector);
		System.out.println(fd);
	}

	public void visit(ClassOrInterfaceDeclaration cd, Void collector) {
		super.visit(cd, collector);
		System.out.println(cd.getNameAsString());
	}
	
	public void visit(FieldDeclaration fd, Void collector) {
		super.visit(fd, collector);
		System.out.println(fd);
	}

	public void visit(MethodDeclaration md, Void collector) {
		super.visit(md, collector);
		System.out.println(md.getName());
	}
}


public class TestJavaParser {
	static GUIView view;

	// main del prof
//	public static void main(String[] args) throws Exception {
		//CompilationUnit cu = StaticJavaParser.parse(new File("src/main/java/TestJavaParser.java"));
		/*
		var methodNames = new ArrayList<String>();
		var methodNameCollector = new MethodNameCollector();
		methodNameCollector.visit(cu,methodNames);
		methodNames.forEach(n -> System.out.println("MethodNameCollected:" + n));
		 */
		//var fullc = new FullCollector();
		//fullc.visit(cu, null);
//	}

	public static void main(String[] args) throws Exception {
		view = new GUIView(1000, 1000);
		view.addListener(new ViewListener());
		ProjectAnalyzer projectAnalyzer;
		projectAnalyzer = new ProjectAnalyzerImpl(Vertx.vertx());
		testClassReport(projectAnalyzer);
	}

	private static void testInterfaceReport(ProjectAnalyzer projectAnalyzer){
		Future<InterfaceReport> future = projectAnalyzer.getInterfaceReport("src/main/java/reports/interfaces/InterfaceReport.java");
		future.onSuccess(interfaceReport -> {
			System.out.println("Prova1");
			System.out.println(interfaceReport.toString());
			view.setText(interfaceReport.toString());
			view.setDTO(DTOs.createInterfaceDTO(interfaceReport));
		});
		future.onFailure(event ->{
			System.out.println("Prova2");
			System.out.println(event.toString());
		});
		future.onComplete(event -> {
			System.out.println("Prova3");
			System.out.println(event.toString());
		});
		System.out.println("Prova4 " + future.result());
	}

	private static void testClassReport(ProjectAnalyzer projectAnalyzer){
		Future<ClassReport> future = projectAnalyzer.getClassReport("src/main/java/reports/ClassReportImpl.java");
		future.onSuccess(classReport -> {
			System.out.println("Future Success:");
//			System.out.println(classReport.toString());
//			view.setText(classReport.toString());
//			view.setDTO(DTOs.createClassDTO(classReport));
			ObjectMapper om = new ObjectMapper();
//				var json = om.writeValueAsString(DTOs.createClassDTO(classReport));
//				System.out.println(json);
			var json = DTOParser.parseString(DTOs.createClassDTO(classReport));
			view.setText(json);
//				var obj = om.readValue(json, ClassInterfaceDTO.class);
			var obj = DTOParser.parseClassInterfaceDTO(json);
			view.setDTO(obj);
//				System.out.println(obj);
		});
		future.onFailure(event ->{
			System.out.println("Future Failure:");
			System.out.println(event.toString());
			System.out.println();
		});
		future.onComplete(event -> {
			System.out.println("Future Complete:");
			System.out.println(event.toString());
			System.out.println();
		});
		System.out.println("Future Result Without \"on\" construct" + future.result());
		//future.onSuccess(System.out::println);
		//future.onFailure(System.out::println);
		//future.onComplete(System.out::println);
	}

}
