package lib.async;

import com.github.javaparser.ast.CompilationUnit;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import lib.Printer;
import lib.reports.PackageReportImpl;
import lib.reports.interfaces.ClassReport;
import lib.reports.interfaces.InterfaceReport;
import lib.reports.interfaces.PackageReport;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

public class PackageVerticle extends AbstractVerticle {

    private final Promise<PackageReport> promise;
    private final PackageReport packageReport = new PackageReportImpl();
    private final AsyncProjectAnalyzer analyzer;
    private final String srcPackagePath;

    public PackageVerticle(AsyncProjectAnalyzer analyzer, Promise<PackageReport> promise, String srcPackagePath) {
        this.analyzer = analyzer;
        this.promise = promise;
        this.srcPackagePath = srcPackagePath;
    }

    @Override
    public void start() {
        AtomicBoolean set = new AtomicBoolean(false);
        final List<Future<ClassReport>> classReports = new ArrayList<>();
        final List<Future<InterfaceReport>> interfaceReports = new ArrayList<>();

        File folder = new File(srcPackagePath);
        var list = Stream.of(Objects.requireNonNull(folder.listFiles((dir, name) -> name.endsWith(".java")))).map(File::getPath).toList();
        list.forEach(path -> {
            CompilationUnit cu;
            try {
                cu = analyzer.getCompilationUnit(path);
                if (cu.getType(0).asClassOrInterfaceDeclaration().isInterface()) {
                    Future<InterfaceReport> f = analyzer.getInterfaceReport(path);
                    var futureCompose = f.compose(report -> {
                        //LOGGER
                        Printer.printMessage("LOGGER-INTERFACE: " + report.hashCode());
                        packageReport.addInterfaceReport(report);
                        analyzer.setPackageNameAndPath(packageReport, set, report.getName(), report.getSourceFullPath(), report);
                        return Future.succeededFuture(report);
                    });
                    interfaceReports.add(futureCompose);

                } else {
                    Future<ClassReport> f = analyzer.getClassReport(path);
                    var futureCompose = f.compose(report -> {
                        //LOGGER
                        Printer.printMessage("LOGGER-CLASS: " + report.hashCode());
                        packageReport.addClassReport(report);
                        analyzer.setPackageNameAndPath(packageReport, set, report.getName(), report.getSourceFullPath(), report);
                        return Future.succeededFuture(report);
                    });
                    classReports.add(futureCompose);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        var classReportsFuture = MyCompositeFuture.join(classReports);
        var interfaceReportsFuture = MyCompositeFuture.join(interfaceReports);
        CompositeFuture.all(classReportsFuture, interfaceReportsFuture).onSuccess(r -> promise.complete(packageReport));

    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

}
