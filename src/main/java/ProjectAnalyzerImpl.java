import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import reports.*;
import visitors.InterfacesVisitor;
import java.io.File;
import java.util.function.Consumer;

public class ProjectAnalyzerImpl implements ProjectAnalyzer {

    private final Vertx vertx;

    public ProjectAnalyzerImpl(final Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    public Future<InterfaceReport> getInterfaceReport(String srcInterfacePath) {
        return this.vertx.executeBlocking(ev -> {
            try {
                CompilationUnit cu = StaticJavaParser.parse(new File(srcInterfacePath));
                InterfacesVisitor interfaceVisitor = new InterfacesVisitor();
                InterfaceReport interfaceReport = new InterfaceReportImpl();
                interfaceVisitor.visit(cu,interfaceReport);
                ev.complete(interfaceReport);
            } catch (Exception e) {
                ev.fail("EXEPTION: getInterfaceReport has failed with message: " + e.getMessage());
            }
        });
    }

    @Override
    public Future<ClassReport> getClassReport(String srcClassPath) {
        return null;
    }

    @Override
    public Future<PackageReport> getPackageReport(String srcPackagePath) {
        return null;
    }

    @Override
    public Future<ProjectReport> getProjectReport(String srcProjectFolderPath) {
        return null;
    }

    @Override
    public void analyzeProject(String srcProjectFolderName, Consumer<ProjectElem> callback) {

    }
}
