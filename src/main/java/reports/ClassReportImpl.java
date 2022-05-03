package reports;

import reports.info.FieldInfo;
import reports.info.MethodInfo;
import java.util.LinkedList;
import java.util.List;

public class ClassReportImpl implements ClassReport{

    String fullClassName;
    String srcFullFileName;
    final List<MethodInfo> methodsInfo;
    final List<FieldInfo> fieldsInfo;

    public ClassReportImpl() {
        this.methodsInfo = new LinkedList<>();
        this.fieldsInfo = new LinkedList<>();
    }

    @Override
    public String getFullClassName() {
        return this.fullClassName;
    }

    @Override
    public String getSrcFullFileName() {
        return this.srcFullFileName;
    }

    @Override
    public List<MethodInfo> getMethodsInfo() {
        return this.methodsInfo;
    }

    @Override
    public List<FieldInfo> getFieldsInfo() {
        return this.fieldsInfo;
    }

    @Override
    public void setFullClassName(String fullClassName) {
        this.fullClassName = fullClassName;
    }

    @Override
    public void setSrcFullFileName(String srcFullFileName) {
        this.srcFullFileName = srcFullFileName;
    }

    @Override
    public void addMethodInfo(MethodInfo methodInfo) {
        this.methodsInfo.add(methodInfo);
    }

    @Override
    public void addFieldInfo(FieldInfo fieldInfo) {
        this.fieldsInfo.add(fieldInfo);
    }
}
