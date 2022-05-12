import dto.DTOParser;
import dto.DTOs;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import lib.async.AsyncProjectAnalyzer;
import lib.ProjectAnalyzer;
import lib.reports.interfaces.ClassReport;
import lib.reports.interfaces.InterfaceReport;
import lib.reports.interfaces.PackageReport;
import lib.reports.interfaces.ProjectReport;
import view.AnalyzerGUI;
import view.StartGUI;
import view.ViewListener;

public class TestJavaParser {
    static StartGUI view;
    static Vertx vertx = Vertx.vertx();

    public static void main(String[] args) {
        view = new StartGUI(new ViewListener());
//        view.addListener(new ViewListener());
        ProjectAnalyzer projectAnalyzer;
        projectAnalyzer = new AsyncProjectAnalyzer(vertx);
        testClassReport(projectAnalyzer);
    }

    private static void testInterfaceReport(ProjectAnalyzer projectAnalyzer) {
        Future<InterfaceReport> future = projectAnalyzer.getInterfaceReport("src/main/java/lib/reports/interfaces/InterfaceReport.java");
        future.onSuccess(interfaceReport -> {
            var json = DTOParser.parseString(DTOs.createInterfaceDTO(interfaceReport));
//            view.setText(json);
            var obj = DTOParser.parseClassInterfaceDTO(json);
//            view.renderTree(obj);
        });
        futureOnFailureOnComplete(future);
    }

    private static void testClassReport(ProjectAnalyzer projectAnalyzer) {
        Future<ClassReport> future = projectAnalyzer.getClassReport("src/main/java/lib/reports/ClassReportImpl.java");
        vertx.eventBus().consumer("test", m -> System.out.println("Ho capito! + m: " + m.body()));
        future.onSuccess(classReport -> {
            var json = DTOParser.parseString(DTOs.createClassDTO(classReport));
//            view.setText(json);
            var obj = DTOParser.parseClassInterfaceDTO(json);
//            view.renderTree(obj);
        });
        futureOnFailureOnComplete(future);
    }

    private static void testPackageReport(ProjectAnalyzer projectAnalyzer) {
        Future<PackageReport> future = projectAnalyzer.getPackageReport("src/main/java/lib/reports");
        future.onSuccess(packageReport -> {
            var json = DTOParser.parseString(DTOs.createPackageDTO(packageReport));
//            view.setText(json);
            var obj = DTOParser.parsePackageDTO(json);
//            view.renderTree(obj);
        });
        futureOnFailureOnComplete(future);
    }

    public static void testProjectReport(ProjectAnalyzer projectAnalyzer) {
        Future<ProjectReport> future = projectAnalyzer.getProjectReport("src/main/java/lib");
        future.onSuccess(projectReport -> {
            var json = DTOParser.parseString(DTOs.createProjectDTO(projectReport));
//            view.setText(json);
            var obj = DTOParser.parseProjectDTO(json);
//            view.renderTree(obj);
        });
        futureOnFailureOnComplete(future);
    }

    private static <T> void futureOnFailureOnComplete(Future<T> future) {
        future.onFailure(event -> {
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
