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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

// TODO: AsyncProjectAnalyzer class javadoc
public class AsyncProjectAnalyzer implements ProjectAnalyzer {

    // TODO: Add javadoc to fields or change them in enum
    public final static String STOP_ANALYZING_PROJECT = ">>STOP<<";
    public final static String PROJECT_REPORT_READY = "";
    public static final String CHANNEL_DEFAULT = "default";

    private final Vertx vertx;
    private Logger logger;
    private final List<String> verticleIDs;

    public AsyncProjectAnalyzer(final Vertx vertx) {
        this.vertx = vertx;
        logger = message -> vertx.eventBus().publish(CHANNEL_DEFAULT, message);
        this.verticleIDs = new ArrayList<>();
    }

    @Override
    public Future<InterfaceReport> getInterfaceReport(String srcInterfacePath) {
        return this.vertx.executeBlocking(ev -> {
            InterfacesVisitor interfaceVisitor = new InterfacesVisitor(logger);
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
            ClassesVisitor classVisitor = new ClassesVisitor(logger);
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

        Promise<PackageReport> promise = new PromiseImpl<>();
        PackageVerticle vert = new PackageVerticle(this, promise, srcPackagePath, this.logger);
        this.vertx.deployVerticle(vert).onComplete(id -> this.verticleIDs.add(id.result()));;

        return promise.future();
    }


    @Override
    public Future<ProjectReport> getProjectReport(String srcProjectFolderPath) {

        Promise<ProjectReport> promise = new PromiseImpl<>();
        ProjectVerticle vert = new ProjectVerticle(this, promise, srcProjectFolderPath, this.logger);
        this.vertx.deployVerticle(vert).onComplete(id -> this.verticleIDs.add(id.result()));

        return promise.future();
    }

    @Override
    public void analyzeProject(String srcProjectFolderName, String topic) {
        this.logger = message -> vertx.eventBus().publish(topic, message);

        this.getProjectReport(srcProjectFolderName);
    }

    CompilationUnit getCompilationUnit(String path) throws FileNotFoundException {
        return StaticJavaParser.parse(new File(path));
    }

}

