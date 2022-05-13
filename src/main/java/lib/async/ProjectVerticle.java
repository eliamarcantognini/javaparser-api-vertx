package lib.async;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import lib.Printer;
import lib.reports.ClassReportImpl;
import lib.reports.ProjectReportImpl;
import lib.reports.interfaces.PackageReport;
import lib.reports.interfaces.ProjectReport;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class ProjectVerticle extends AbstractVerticle {
    private final Promise<ProjectReport> promise;
    private final ProjectReport projectReport = new ProjectReportImpl();
    private final AsyncProjectAnalyzer analyzer;
    private final String projectPath;

    public ProjectVerticle(AsyncProjectAnalyzer analyzer, Promise<ProjectReport> promise, String projectPath) {
        this.analyzer = analyzer;
        this.promise = promise;
        this.projectPath = projectPath;
    }

    @Override
    public void start() {
        final List<Future<PackageReport>> packageReports = new ArrayList<>();
        final File folder = new File(projectPath);

        if (!folder.isDirectory()) promise.fail("Path is not a directory");
        var list = Stream
                .concat(Stream.of(folder.toString()),
                        Stream.of(Objects.requireNonNull(folder.listFiles()))
                                .filter(File::isDirectory)
                                .map(File::getPath))
                .toList();

        list.forEach(path -> {
            Future<PackageReport> f = analyzer.getPackageReport(path);

            Printer.printMessage("Analizzo il package: " + path);
            var futureCompose = f.compose(report -> {
                //LOGGER
                Printer.printMessage("LOGGER PACKAGE: " + report.hashCode());
//                Printer.printMessage("LOGGER PACKAGE: " + report);
                projectReport.addPackageReport(report);

                var opt = report.getClassesReports()
                        .stream()
                        .filter(c -> c.getMethodsInfo()
                                .stream()
                                .anyMatch(m -> m.getName().equals("main")))
                        .findFirst();
                projectReport.setMainClass(opt.orElseGet(ClassReportImpl::new));

                return Future.succeededFuture(report);

            });
            packageReports.add(futureCompose);
        });

        MyCompositeFuture.join(packageReports).onSuccess(r -> {
            //LOGGER EMPTY MAIN CLASS projectReport.getMainClass().getName().isBlank()
            promise.complete(projectReport);
        });
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }
}
