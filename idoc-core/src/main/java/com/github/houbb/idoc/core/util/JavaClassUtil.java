package com.github.houbb.idoc.core.util;

import com.github.houbb.idoc.common.util.CollectionUtil;
import com.github.houbb.idoc.common.util.ObjectUtil;
import com.github.houbb.log.integration.core.Log;
import com.github.houbb.log.integration.core.LogFactory;
import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.Type;
import com.thoughtworks.qdox.parser.ParseException;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

/**
 * JavaClass 工具类
 * Created by bbhou on 2017/11/2.
 * for: com.thoughtworks.qdox
 * @author bbhou
 * @since 0.0.1
 */
public final class JavaClassUtil {

    /**    
     *  java类util    
     */    
    private JavaClassUtil(){}

    /**    
     *  我不是例外    
     */    
    private static final JavaClassUtil INSTANCE = new JavaClassUtil();

    /**
     * 获取一个实例
     * @return 实例本身
     */
    public static JavaClassUtil getInstance() {
        return INSTANCE;
    }

    //region fields
    /**
     * <code>public</code> parameter used by level*
     */
    public static final String LEVEL_PUBLIC = "public";

    /**
     * <code>protected</code> parameter used by level*
     */
    public static final String LEVEL_PROTECTED = "protected";

    /**
     * <code>package</code> parameter used by level*
     */
    public static final String LEVEL_PACKAGE = "package";

    /**
     * <code>private</code> parameter used by level*
     */
    public static final String LEVEL_PRIVATE = "private";

    /**
     * The current project class loader.
     */
    private ClassLoader projectClassLoader;
    //endregion

    /**
     * Calling Qdox to find {@link JavaClass} objects from the Maven project sources.
     * Ignore java class if Qdox has parsing errors.
     * @param project 项目信息
     * @param encoding 编码信息
     * @param excludes 排除文件正则
     * @param includes 包含文件正则
     * @return  an array of {@link JavaClass} found by QDox
     * @throws IOException  if any
     * @throws MojoExecutionException   if any
     */
    public JavaClass[] getQdoxClasses(MavenProject project, String encoding,
                                      final String includes, final String excludes)
            throws IOException, MojoExecutionException {
        if ("pom".equalsIgnoreCase(project.getPackaging())) {
            getLog().warn("This project has 'pom' packaging, no Java sources is available.");
            return new JavaClass[]{};
        }

        List<File> javaFiles = new LinkedList<>();
        for (String sourceRoot : getProjectSourceRoots(project)) {
            File f = new File(sourceRoot);
            if (f.isDirectory()) {
                javaFiles.addAll(FileUtils.getFiles(f, includes, excludes, true));
            } else {
                getLog().warn(f + " doesn't exist. Ignored it.");
            }
        }

        JavaDocBuilder builder = new JavaDocBuilder();
        builder.getClassLibrary().addClassLoader(getProjectClassLoader(project));
        builder.setEncoding(encoding);
        for (File f : javaFiles) {
            if (!f.getAbsolutePath().toLowerCase(Locale.ENGLISH).endsWith(".java")) {
                getLog().warn("'" + f + "' is not a Java file. Ignored it.");
                continue;
            }

            try {
                builder.addSource(f);
            } catch (ParseException e) {
                getLog().warn("QDOX ParseException: " + e.getMessage() + ". Can't fix it.");
            }
        }

        return builder.getClasses();
    }

    /**
     * @return the classLoader for the given project using lazy instantiation.
     * @throws MojoExecutionException if any
     */
    /**    
     * 获得项目类加载器    
     *    
     * @param project 项目    
     * @return java.lang.ClassLoader    
     * @throws MojoExecutionException if any
     */
    private ClassLoader getProjectClassLoader(MavenProject project)
            throws MojoExecutionException {
        if (projectClassLoader == null) {
            List<String> classPath;
            try {
                classPath = getCompileClasspathElements(project);
            } catch (DependencyResolutionRequiredException e) {
                throw new MojoExecutionException("DependencyResolutionRequiredException: " + e.getMessage(), e);
            }

            List<URL> urls = new ArrayList<>(classPath.size());
            for (String filename : classPath) {
                try {
                    urls.add(new File(filename).toURL());
                } catch (MalformedURLException e) {
                    throw new MojoExecutionException("MalformedURLException: " + e.getMessage(), e);
                }
            }

            projectClassLoader = new URLClassLoader(urls.toArray(new URL[urls.size()]), null);
        }

        return projectClassLoader;
    }

    /**
     * @param p not null
     * @return the compile classpath elements
     * @throws DependencyResolutionRequiredException if any
     */
    /**
     * 获取编译classpath元素
     *
     * @param p p
     * @return java.util.List
     * @throws DependencyResolutionRequiredException if any
     */    
    protected List<String> getCompileClasspathElements(MavenProject p)
            throws DependencyResolutionRequiredException {
        return (p.getCompileClasspathElements() == null
                ? Collections.<String>emptyList()
                : new LinkedList<String>(p.getCompileClasspathElements()));
    }

    /**
     * @param p not null maven project.
     * @return the list of source paths for the given project.
     */
    /**    
     * 获取项目源的根    
     *    
     * @param p p    
     * @return java.util.List    
     */    
    protected List<String> getProjectSourceRoots(MavenProject p) {
        return (p.getCompileSourceRoots() == null
                ? Collections.<String>emptyList()
                : new LinkedList<String>(p.getCompileSourceRoots()));
    }

    /**    
     * 得到日志    
     *    
     * @return com.github.houbb.log.integration.core.Log    
     */    
    private Log getLog() {
        return LogFactory.getLog(this.getClass());
    }


    /**
     * @param modifiers list of modifiers (public, private, protected, package)
     * @return <code>true</code> if modifier is align with <code>level</code>.
     */
    /**    
     * 在水平上    
     *    
     * @param modifiers 修饰符    
     * @param level 水平    
     * @return boolean    
     */    
    public static boolean isInLevel(String[] modifiers, final String level )
    {
        List<String> modifiersAsList = Arrays.asList( modifiers );

        if ( LEVEL_PUBLIC.equalsIgnoreCase( level.trim() ) )
        {
            return modifiersAsList.contains( LEVEL_PUBLIC );
        }

        if ( LEVEL_PROTECTED.equalsIgnoreCase( level.trim() ) )
        {
            return ( modifiersAsList.contains( LEVEL_PUBLIC ) || modifiersAsList.contains( LEVEL_PROTECTED ) );
        }

        if ( LEVEL_PACKAGE.equalsIgnoreCase( level.trim() ) )
        {
            return !modifiersAsList.contains( LEVEL_PRIVATE );
        }

        // should be private (shows all classes and members)
        return true;
    }

    /**
     * 是否包含此级别
     * @param modifiers 所有的访问修饰符数组
     * @param level 级别
     * @return  {@code true} 是
     */
    public static boolean isContainsLevel(String[] modifiers, final String level) {
        List<String> modifiersAsList = Arrays.asList( modifiers );
        return modifiersAsList.contains(level);
    }


    /**
     * 根据类名称 获取对应的 JavaClass 信息
     * @param javaClasses   所有的 java class 列表
     * @param className class 名称
     * @return  获取对应的 JavaClass 信息
     */
    public JavaClass getJavaClass(JavaClass[] javaClasses, final String className) {
        for(JavaClass javaClass : javaClasses) {
            if(className.equals(javaClass.getName())) {
                return javaClass;
            }
        }
        return null;
    }

    /**
     * 获取指定类的所有字段信息
     * @param javaClass 类信息
     * @return 字段列表
     */
    public static List<JavaField> getAllJavaFieldList(final JavaClass javaClass) {
        if(ObjectUtil.isNull(javaClass)) {
            return Collections.emptyList();
        }
        List<JavaField> javaFieldList = new ArrayList<>();
        // 当前类
        CollectionUtil.addArray(javaFieldList, javaClass.getFields());
        //处理父类信息-这里的 parentClass 是空的
        JavaClass supperClass = javaClass.getSuperJavaClass();
        while (supperClass != null
            && !"java.lang.Object".equals(supperClass.getFullyQualifiedName())) {
            CollectionUtil.addArray(javaFieldList, supperClass.getFields());
            supperClass = supperClass.getSuperJavaClass();
        }
        return javaFieldList;
    }

    /**
     * 是否为 jdk 默认的对象类型
     *
     * @param type 类型
     * @return 是否
     */
    public static boolean isPrimitiveOrJdk(final Type type) {
        return type.isPrimitive()
                || type.getFullyQualifiedName().startsWith("java.")
                || type.getFullyQualifiedName().startsWith("sun.");
    }

    /**
     * 是否为死循环
     * @param javaField 当前字段
     * @param javaFieldList 对应字段列表
     * @return 结果
     */
    public static boolean isDeadCycle(final JavaField javaField,
                                final List<JavaField> javaFieldList) {
        for(JavaField javaField1 : javaFieldList) {
            if(javaField.getType().getFullyQualifiedName().equals(javaField1.getType().getFullyQualifiedName())) {
                return true;
            }
        }
        return false;
    }

}
