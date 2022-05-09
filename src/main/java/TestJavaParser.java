import java.util.List;

import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import dto.DTOParser;
import dto.DTOs;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import lib.AsyncProjectAnalyzer;
import lib.ProjectAnalyzer;
import lib.reports.interfaces.ClassReport;
import lib.reports.interfaces.InterfaceReport;
import lib.reports.interfaces.PackageReport;
import lib.reports.interfaces.ProjectReport;
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
		projectAnalyzer = new AsyncProjectAnalyzer(Vertx.vertx());
		testClassReport(projectAnalyzer);
	}

	private static void testInterfaceReport(ProjectAnalyzer projectAnalyzer){
		Future<InterfaceReport> future = projectAnalyzer.getInterfaceReport("src/main/java/lib.reports/interfaces/InterfaceReport.java");
		future.onSuccess(interfaceReport -> {
			var json = DTOParser.parseString(DTOs.createInterfaceDTO(interfaceReport));
			view.setText(json);
			var obj = DTOParser.parseClassInterfaceDTO(json);
			view.renderTree(obj);
		});
		futureOnFailureOnComplete(future);
	}

	private static void testClassReport(ProjectAnalyzer projectAnalyzer){
		Future<ClassReport> future = projectAnalyzer.getClassReport("src/main/java/lib.reports/ClassReportImpl.java");
		future.onSuccess(classReport -> {
			var json = DTOParser.parseString(DTOs.createClassDTO(classReport));
			view.setText(json);
			var obj = DTOParser.parseClassInterfaceDTO(json);
			view.renderTree(obj);
		});
		futureOnFailureOnComplete(future);
	}

	private static void testPackageReport(ProjectAnalyzer projectAnalyzer){
		Future<PackageReport> future = projectAnalyzer.getPackageReport("src/main/java/lib.reports");
		future.onSuccess(packageReport -> {
			var json = DTOParser.parseString(DTOs.createPackageDTO(packageReport));
			view.setText(json);
			var obj = DTOParser.parsePackageDTO(json);
			view.renderTree(obj);
		});
		futureOnFailureOnComplete(future);
	}

	private static void testProjectReport(ProjectAnalyzer projectAnalyzer){
		Future<ProjectReport> future = projectAnalyzer.getProjectReport("src/main/java/lib.reports");
		future.onSuccess(projectReport -> {
			var json = DTOParser.parseString(DTOs.createProjectDTO(projectReport));
			view.setText(json);
			var obj = DTOParser.parsePackageDTO(json);
			view.renderTree(obj);
		});
		futureOnFailureOnComplete(future);
	}

	private static <T> void futureOnFailureOnComplete(Future<T> future){
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
	}

}
