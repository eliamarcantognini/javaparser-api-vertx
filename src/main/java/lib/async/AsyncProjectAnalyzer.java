package lib.async;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.impl.future.PromiseImpl;
import lib.Logger;
import lib.ProjectAnalyzer;
import lib.reports.ClassReportImpl;
import lib.reports.InterfaceReportImpl;
import lib.reports.PackageReportImpl;
import lib.reports.ProjectReportImpl;
import lib.reports.interfaces.*;
import lib.visitors.ClassesVisitor;
import lib.visitors.InterfacesVisitor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicBoolean;

public class AsyncProjectAnalyzer implements ProjectAnalyzer {

    private final Vertx vertx;
    private final Logger logger;

    public AsyncProjectAnalyzer(final Vertx vertx) {
        this.vertx = vertx;
        logger = message -> vertx.eventBus().publish("test", message);
    }

    @Override
    public Future<InterfaceReport> getInterfaceReport(String srcInterfacePath) {
        return this.vertx.executeBlocking(ev -> {
            InterfacesVisitor interfaceVisitor = new InterfacesVisitor();
            InterfaceReport interfaceReport = new InterfaceReportImpl();
            try {
                interfaceVisitor.visit(this.getCompilationUnit(srcInterfacePath), interfaceReport);
                logger.log(interfaceReport);
                ev.complete(interfaceReport);
            } catch (FileNotFoundException e) {
                ev.fail("EXCEPTION: getInterfaceReport has failed with message: " + e.getMessage());
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
                logger.log(classReport);
                ev.complete(classReport);
            } catch (FileNotFoundException e) {
                ev.fail("EXCEPTION: getClassReport has failed with message: " + e.getMessage());
            }
        });
    }

    @Override
    public Future<PackageReport> getPackageReport(String srcPackagePath) {

        PackageReport packageReport = new PackageReportImpl();
        Promise<PackageReport> promise = new PromiseImpl<>();
        PackageVerticle vert = new PackageVerticle(this, promise, srcPackagePath);
        Future<String> verticleID = this.vertx.deployVerticle(vert);

        return promise.future();
    }


    @Override
    public Future<ProjectReport> getProjectReport(String srcProjectFolderPath) {

        ProjectReport packageReport = new ProjectReportImpl();
        Promise<ProjectReport> promise = new PromiseImpl<>();
        ProjectVerticle vert = new ProjectVerticle(this, promise, srcProjectFolderPath);
        Future<String> verticleID = this.vertx.deployVerticle(vert);

        return promise.future();
    }

    @Override
    public void analyzeProject(String srcProjectFolderName, String topic) {

    }

    CompilationUnit getCompilationUnit(String path) throws FileNotFoundException {
        return StaticJavaParser.parse(new File(path));
    }

    void setPackageNameAndPath(PackageReport packageReport, AtomicBoolean set, String name, String sourceFullPath, Report res) {
        if (!set.get()) {
            var s = sourceFullPath.split("\\.");
            packageReport.setName(s[s.length - 2]);
            packageReport.setFullPath(sourceFullPath.substring(0, sourceFullPath.length() - name.length() - 1));
            set.set(true);
        }
    }

//    public static void main(String[] args) {
//        System.out.println("ciao");
//    }

}

