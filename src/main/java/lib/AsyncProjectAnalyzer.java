package lib;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.impl.future.PromiseImpl;
import lib.reports.ClassReportImpl;
import lib.reports.InterfaceReportImpl;
import lib.reports.PackageReportImpl;
import lib.reports.interfaces.*;
import lib.visitors.ClassesVisitor;
import lib.visitors.InterfacesVisitor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
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
                interfaceVisitor.visit(this.getCompilationUnit(srcInterfacePath), interfaceReport);
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
                classVisitor.visit(this.getCompilationUnit(srcClassPath), classReport);
                ev.complete(classReport);
            } catch (FileNotFoundException e) {
                ev.fail("EXEPTION: getClassReport has failed with message: " + e.getMessage());
            }
        });
    }

    @Override
    public Future<PackageReport> getPackageReport(String srcPackagePath) {

//        return this.vertx.executeBlocking(ev -> {
//            AtomicInteger completed = new AtomicInteger(0);
//            final PackageReport packageReport = new PackageReportImpl();
//            final List<Future<ClassReport>> classReports = new ArrayList<>();
//            final List<Future<InterfaceReport>> interfaceReports = new ArrayList<>();
//
//            File folder = new File(srcPackagePath);
//            var list = Stream.of(Objects.requireNonNull(folder.listFiles((dir, name) -> name.endsWith(".java")))).map(File::getPath).toList();
//            list.forEach(path -> {
//                CompilationUnit cu = null;
//                try {
//                    cu = getCompilationUnit(path);
//                    if (cu.getType(0).asClassOrInterfaceDeclaration().isInterface())
//                        interfaceReports.add(getInterfaceReport(path));
//                    else classReports.add(getClassReport(path));
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//            });
//            AtomicBoolean set = new AtomicBoolean(false);
//            classReports.forEach(f -> f.onSuccess(res -> {
//                packageReport.addClassReport(res);
//                setPackageNameAndPath(packageReport, set, res.getName(), res.getSourceFullPath(), res);
//                checkCompletion(completed, classReports, interfaceReports, ev, packageReport);
//            }));
//            interfaceReports.forEach(f -> f.onSuccess(res -> {
//                packageReport.addInterfaceReport(res);
//                setPackageNameAndPath(packageReport, set, res.getName(), res.getSourceFullPath(), res);
//                checkCompletion(completed, classReports, interfaceReports, ev, packageReport);
//            }));
//        });

        PackageReport p = new PackageReportImpl();
        Promise<PackageReport> promise = new PromiseImpl<>();

        Future<String> verticleID = this.vertx.deployVerticle(new AbstractVerticle() {

            final List<Future<ClassReport>> classReports = new ArrayList<>();
            final List<Future<InterfaceReport>> interfaceReports = new ArrayList<>();

            @Override
            public void start() throws Exception {
                AtomicInteger completed = new AtomicInteger(0);
                final PackageReport packageReport = new PackageReportImpl();
                final List<Future<ClassReport>> classReports = new ArrayList<>();
                final List<Future<InterfaceReport>> interfaceReports = new ArrayList<>();

                File folder = new File(srcPackagePath);
                var list = Stream.of(Objects.requireNonNull(folder.listFiles((dir, name) -> name.endsWith(".java")))).map(File::getPath).toList();
                list.forEach(path -> {
                    CompilationUnit cu = null;
                    try {
                        cu = getCompilationUnit(path);
                        if (cu.getType(0).asClassOrInterfaceDeclaration().isInterface())
                            interfaceReports.add(getInterfaceReport(path));
                        else classReports.add(getClassReport(path));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                });
                AtomicBoolean set = new AtomicBoolean(false);
                classReports.forEach(f -> f.onSuccess(res -> {
                    packageReport.addClassReport(res);
                    setPackageNameAndPath(packageReport, set, res.getName(), res.getSourceFullPath(), res);
                    checkCompletion(completed, classReports, interfaceReports, promise, packageReport);
                }));
                interfaceReports.forEach(f -> f.onSuccess(res -> {
                    packageReport.addInterfaceReport(res);
                    setPackageNameAndPath(packageReport, set, res.getName(), res.getSourceFullPath(), res);
                    checkCompletion(completed, classReports, interfaceReports, promise, packageReport);
                }));

            }

            @Override
            public void stop() throws Exception {
                super.stop();
            }
        });

        return promise.future();
    }

    private void checkCompletion(AtomicInteger completed, List<Future<ClassReport>> classReports, List<Future<InterfaceReport>> interfaceReports, Promise<PackageReport> ev, PackageReport packageReport) {
        completed.getAndIncrement();
        if (completed.get() == classReports.size() + interfaceReports.size()) ev.complete(packageReport);
    }

    private void setPackageNameAndPath(PackageReport packageReport, AtomicBoolean set, String name, String sourceFullPath, Report res) {
        if (!set.get()) {
            packageReport.setName(sourceFullPath.split("\\.")[0]);
            packageReport.setFullPath(sourceFullPath.substring(0, sourceFullPath.length() - name.length() - 1));
            set.set(true);
        }
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
