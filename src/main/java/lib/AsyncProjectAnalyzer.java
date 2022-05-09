package lib;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import io.vertx.core.*;
import io.vertx.core.impl.future.PromiseImpl;
import lib.reports.*;
import lib.reports.interfaces.ClassReport;
import lib.reports.interfaces.InterfaceReport;
import lib.reports.interfaces.PackageReport;
import lib.reports.interfaces.ProjectReport;
import lib.visitors.ClassesVisitor;
import lib.visitors.InterfacesVisitor;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AsyncProjectAnalyzer implements ProjectAnalyzer {

    private final Vertx vertx;

    public AsyncProjectAnalyzer(final Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    public Future<InterfaceReport> getInterfaceReport(String srcInterfacePath) {
        return this.vertx.executeBlocking(ev -> {
            InterfacesVisitor interfaceVisitor = new InterfacesVisitor();
            InterfaceReport interfaceReport = new InterfaceReportImpl();
            try {
                //CompilationUnit cu = StaticJavaParser.parse(new File(srcInterfacePath));
                interfaceVisitor.visit(this.getCompilationUnit(srcInterfacePath),interfaceReport);
                ev.complete(interfaceReport);
            } catch (FileNotFoundException e) {
                ev.fail("EXEPTION: getInterfaceReport has failed with message: " + e.getMessage());
            }
        });
    }

    @Override
    public Future<ClassReport> getClassReport(String srcClassPath) {
        return this.vertx.executeBlocking(ev -> {
            ClassesVisitor classVisitor = new ClassesVisitor();
            ClassReport classReport = new ClassReportImpl();
            try {
                classVisitor.visit(this.getCompilationUnit(srcClassPath),classReport);
                ev.complete(classReport);
            } catch (FileNotFoundException e) {
                ev.fail("EXEPTION: getClassReport has failed with message: " + e.getMessage());
            }
        });
    }

    @Override
    public Future<PackageReport> getPackageReport(String srcPackagePath) {

        PackageReport p = new PackageReportImpl();
        Promise<PackageReport> promise = new PromiseImpl<>();

        Future<String> verticleID = this.vertx.deployVerticle(new AbstractVerticle() {

            final List<Future<ClassReport>> classReports = new ArrayList<>();
            final List<Future<InterfaceReport>> interfaceReports = new ArrayList<>();

            @Override
            public void start() throws Exception {
                File folder = new File(srcPackagePath);
                List<String> list = Stream.of(Objects.requireNonNull(folder.listFiles((dir, name) -> name.endsWith(".java")))).map(File::getPath).toList();

                list.forEach(path -> {
                    try {
                        CompilationUnit cu = getCompilationUnit(path);
                        if (cu.getType(0).asClassOrInterfaceDeclaration().isInterface()) {
                            interfaceReports.add(getInterfaceReport(path));
                        } else {
                            classReports.add(getClassReport(path));
                        }
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                });

//                classReports.forEach(f -> {
////                    f.onComplete(res -> p.addClass(res));
//                });

            }

            @Override
            public void stop() throws Exception {
                super.stop();
            }
        });

        return promise.future();
    }

    @Override
    public Future<ProjectReport> getProjectReport(String srcProjectFolderPath) {
        return null;
    }

    @Override
    public void analyzeProject(String srcProjectFolderName, String topic) {

    }

    private CompilationUnit getCompilationUnit(String path) throws FileNotFoundException {
        return StaticJavaParser.parse(new File(path));
    }
}
