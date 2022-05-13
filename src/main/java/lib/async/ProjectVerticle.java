package lib.async;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import lib.Logger;
import utils.Printer;
import lib.reports.ProjectReportImpl;
import lib.reports.interfaces.PackageReport;
import lib.reports.interfaces.ProjectReport;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class ProjectVerticle extends AbstractVerticle {

    private static final String NO_MAIN_CLASS_MESSAGE = "Main class not found in this project";

    private final Promise<ProjectReport> promise;
    private final ProjectReport projectReport = new ProjectReportImpl();
    private final AsyncProjectAnalyzer analyzer;
    private final String projectPath;
    private final Logger logger;

    public ProjectVerticle(AsyncProjectAnalyzer analyzer, Promise<ProjectReport> promise, String projectPath, Logger logger) {
        this.analyzer = analyzer;
        this.promise = promise;
        this.projectPath = projectPath;
        this.logger = logger;
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
            var futureCompose = f.compose(report -> {
                projectReport.addPackageReport(report);

                // With stream often doesn't take the main class. Why?
//                var opt = report.getClassesReports()
//                        .stream()
//                        .filter(c -> c.getMethodsInfo()
//                                .stream()
//                                .anyMatch(m -> m.getName().equals("main")))
//                        .findFirst();
//                projectReport.setMainClass(opt.orElseGet(ClassReportImpl::new));
                report.getClassesReports().forEach(c -> c.getMethodsInfo().forEach(m -> {
                    if (m.getName().equals("main")) projectReport.setMainClass(c);
                }));

                return Future.succeededFuture(report);

            });
            packageReports.add(futureCompose);
        });

        MyCompositeFuture.join(packageReports).onSuccess(r -> {
            if (projectReport.getMainClass().getName().isBlank()) logger.log(NO_MAIN_CLASS_MESSAGE);
            logger.log(projectReport);
            promise.complete(projectReport);
        });
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }
}
