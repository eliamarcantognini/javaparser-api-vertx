import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import reports.interfaces.ClassReport;
import reports.interfaces.InterfaceReport;

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
		ProjectAnalyzer projectAnalyzer;
		projectAnalyzer = new ProjectAnalyzerImpl(Vertx.vertx());
		testClassReport(projectAnalyzer);
	}

	private void testInterfaceReport(ProjectAnalyzer projectAnalyzer){
		Future<InterfaceReport> future = projectAnalyzer.getInterfaceReport("src/main/java/reports/InterfaceReport.java");
		future.onSuccess(event -> {
			System.out.println("Prova1");
			System.out.println(event.toString());
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
		future.onSuccess(event -> {
			System.out.println("Future Success:");
			System.out.println(event.toString());
			System.out.println();
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
