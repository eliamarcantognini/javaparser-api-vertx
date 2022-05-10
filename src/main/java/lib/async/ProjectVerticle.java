package lib.async;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import lib.reports.PackageReportImpl;
import lib.reports.ProjectReportImpl;
import lib.reports.interfaces.PackageReport;
import lib.reports.interfaces.ProjectReport;

public class ProjectVerticle extends AbstractVerticle {
    private final Promise<ProjectReport> promise;
    private final ProjectReport projectReport = new ProjectReportImpl();
    private final AsyncProjectAnalyzer analyzer;
    private final String srcPackagePath;

    public ProjectVerticle(AsyncProjectAnalyzer analyzer, Promise<ProjectReport> promise, String srcPackagePath) {
        this.analyzer = analyzer;
        this.promise = promise;
        this.srcPackagePath = srcPackagePath;
    }

}
