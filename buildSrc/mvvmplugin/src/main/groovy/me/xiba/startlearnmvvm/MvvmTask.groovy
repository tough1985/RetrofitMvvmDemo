package me.xiba.startlearnmvvm

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import java.text.SimpleDateFormat
import java.util.regex.Matcher
import java.util.regex.Pattern

class MvvmTask extends DefaultTask {

    @TaskAction
    def generateMvvpFile() {

        def lygMvpExtension = project.extensions.getByType(MvvmExtension)
        //应用ID
        def applicationId = lygMvpExtension.applicationId;
        //包名
        def packageName = lygMvpExtension.packageName
        //功能名
        def functionName = lygMvpExtension.functionName
        //作者
        def author = lygMvpExtension.author
        //view是activity 还是 fragment
        def isViewActivity = lygMvpExtension.isViewActivity

        def xmlName = camel2Underline(functionName)


        println "generateMvvpFile : functionName=" + functionName
        println "generateMvvpFile : xmlName=" + xmlName


        if (isViewActivity){
            xmlName = "activity_" + xmlName
        } else {
            xmlName = "fragment_" + xmlName
        }

        def mvvmArray = [
                [
                        templateName : "MvvmXml.template",
                        type : "xml",
                        fileName : ".xml"
                ],
                [
                        templateName : "ViewModel.template",
                        type : "viewmodel",
                        fileName : "VM.java"
                ]
        ]

        if (isViewActivity){
            mvvmArray.add([
                    templateName : "MvvmActivity.template",
                    type : "activity",
                    fileName : "Activity.java",
            ])


        } else {
            mvvmArray.add([
                    templateName : "MvpFragment.template",
                    type : "fragment",
                    fileName : "Fragment.java",
            ])

        }

        String dateString = getFormatTime()

        def mBinding = [
                applicaitionId  : applicationId,
                packageName     : packageName,
                functionName    : functionName,
                date            : dateString,
                author          : author,
                xmlName         : xmlName
        ];

        def packageFilePath = lygMvpExtension.applicationId.replace(".", "/");

        //代码文件根路径
        def fullPath = project.projectDir.toString() + "/src/main/java/" + packageFilePath

        //xmlPath文件路径
        def xmlPath = project.projectDir.toString() + "/src/main/res/layout"

        generateMvpFile(mvvmArray, mBinding, fullPath, xmlPath)

    }

    void generateMvpFile(def mvvmArray, def binding, def fullPath, def xmlPath){

        for(int i = 0; i < mvvmArray.size(); i++){
            preGenerateFile(mvvmArray[i], binding, fullPath, xmlPath)
        }
    }

    void preGenerateFile(def map, def binding, def fullPath, def xmlPath){
        // File mvpContractTemplateFile = new File("template/" + map.templateFileName)

//        println "preGenerateFile : map.templateName=" + map.templateName
//        println "preGenerateFile : map.type=" + map.type
//        println "preGenerateFile : map.fileName=" + map.fileName
        def template = makeTemplate(map.templateName, binding)
        def path
        def fileName

        if(map.type.equals("xml")){ //如果是xml文件
            path = xmlPath
            fileName = path + "/" + binding.xmlName + map.fileName
        } else {
            path = fullPath + "/" + binding.packageName + "/" + map.type
            fileName = path + "/" + binding.functionName + map.fileName
        }


        generateFile(path, fileName, template)
    }

    /**
     * 加载模板
     */
    def makeTemplate(def templateName, def binding){

        File f = new File("./buildSrc/mvvmplugin/template/" + templateName)

        def engine = new groovy.text.GStringTemplateEngine()

        return engine.createTemplate(f).make(binding)
    }

    /**
     * 生成文件
     * @param path
     * @param fileName
     * @param template
     */
    void generateFile(def path, def fileName, def template){
        //验证文件路径，没有则创建
        validatePath(path)

        File mvvmFile = new File(fileName)

        //如果文件已经存在，直接返回
        if(!mvvmFile.exists()){
            mvvmFile.createNewFile()
        } else {
            return
        }

        FileOutputStream out = new FileOutputStream(mvvmFile, false)
        out.write(template.toString().getBytes("utf-8"))
        out.close()
    }

    /**
     * 验证文件路径，没有则创建
     * @param path
     */
    void validatePath(def path){
        File mvvmFileDir = new File(path)

        if(!mvvmFileDir.exists()){
            mvvmFileDir.mkdirs()
        }
    }

    /**
     * 格式化当前时间
     * @return
     */
    def getFormatTime(){
        Date date = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }

    /**
     * 驼峰法转下划线
     * @param line 源字符串
     * @return 转换后的字符串
     */
    String camel2Underline(String line){
        if(line==null||"".equals(line)){
            return "";
        }
        line=String.valueOf(line.charAt(0)).toUpperCase().concat(line.substring(1));
        StringBuffer sb=new StringBuffer();
        Pattern pattern=Pattern.compile("[A-Z]([a-z\\d]+)?");
        Matcher matcher=pattern.matcher(line);
        while(matcher.find()){
            String word=matcher.group();
            sb.append(word.toLowerCase());
            sb.append(matcher.end()==line.length()?"":"_");
        }
        return sb.toString();
    }

}