package com.webapp.utils.wrun;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;

import org.apache.commons.io.IOUtils;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;

import com.webapp.utils.config.PathUtils;
import com.webapp.utils.file.CmdUtils;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.proxy.Enhancer;

public class ClassBuilderAll<T> {

	private static void cglib() {
//    	BeanMap props = BeanMap.create(bean);

    	HashMap<String, Class<?>> props = new HashMap<>();
		props.put("id", Integer.class);
		props.put("name", String.class);
		props.put("address", String.class);
    	BeanGenerator beanGen = new BeanGenerator();
    	beanGen.addProperty("id", Integer.class);

    	String string = beanGen.toString();

    	Class<?> createClass = (Class<?>)beanGen.createClass();

    	String string2 = createClass.toString();
    	System.out.println(string2);


    	Enhancer jj = new Enhancer();
	}

	private static void javassist() {
		ClassPool clzPool = ClassPool.getDefault();
    	CtClass cc = clzPool.makeClass("com.webapp.utils.Ok");
        CtMethod method;
		try {

			CtField cf = CtField.make("String ff;", cc);
			cc.addField(cf);

			method = CtNewMethod.make("public void code(){}", cc);
			method.insertBefore("System.out.println(\"I'm a Programmer,Just Coding.....\");");
			cc.addMethod(method);

			cc.debugWriteFile();
		} catch (CannotCompileException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws Exception {
		asm();
	}
	private static void asm() throws Exception {
		String clz = "Hello";

		ClassWriter cw = new ClassWriter(0);
        //1-版本 2-修饰符 3-全限定名 4-泛型 5-父类全限定名 6-实现的接口
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, clz, null, "java/lang/Object", null);

        //1-修饰符 2-字段名 3-字段描述符 4-泛型 5-字段的值
        FieldVisitor fv = cw.visitField(Opcodes.ACC_PUBLIC, "name", "LString;", null, null);
        AnnotationVisitor av = fv.visitAnnotation("LNotNull;", true);
        av.visit("value", "abc");
        av.visitEnd();
        fv.visitEnd();

        //1-修饰符 2-方法名 3-方法描述符 4-泛型 5-异常声明
//        MethodVisitor md = cw.visitMethod(Opcodes.ACC_PUBLIC, "code", "()V", null, null);

        File file = new File(PathUtils.getCurPath(ClassBuilderAll.class) + "/"+clz+".class");
        IOUtils.write(cw.toByteArray(), new FileOutputStream(file));

//        Class<?> clazz = ClassBuilderAll.class.getClassLoader().loadClass(file.getAbsolutePath());
//        String nameString = (String) clazz.getField("name").get(clazz.newInstance());
//        System.out.println("filed value : " + nameString);
//
//        String annoVal = clazz.getField("name").getAnnotation(NotNull.class).toString();
//        System.out.println("annotation value: " + annoVal);

        String exec = CmdUtils.javap(file.getAbsolutePath(), "gbk");
        System.out.println(exec);

	}

}
