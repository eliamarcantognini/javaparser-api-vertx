import io.vertx.core.Future;
import io.vertx.core.Vertx;
import reports.ClassReport;
import reports.InterfaceReport;
import reports.PackageReport;
import reports.ProjectReport;

import java.util.function.Consumer;

public class ProjectAnalyzerImpl implements ProjectAnalyzer {

    private final Vertx vertx;

    public ProjectAnalyzerImpl(final Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    public Future<InterfaceReport> getInterfaceReport(String srcInterfacePath) {
        return this.vertx.executeBlocking(ev -> {

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
