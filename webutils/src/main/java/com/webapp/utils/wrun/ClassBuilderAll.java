package com.webapp.utils.wrun;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.webapp.utils.string.Utils;

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

	private static void asm() throws Exception {
		ClassWriter classWriter = new ClassWriter(0);
        // 通过visit方法确定类的头部信息
        classWriter.visit(Opcodes.V1_8,// java版本
                Opcodes.ACC_PUBLIC,// 类修饰符
                "Programmer", // 类的全限定名
                null, "java/lang/Object", null);

        // 定义code方法
        MethodVisitor methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "code", "()V",
                null, null);
        classWriter.visitEnd();
        // 使classWriter类已经完成
        // 将classWriter转换成字节数组写到文件里面去
        byte[] data = classWriter.toByteArray();
        File file = new File("D://xxxx.class");
        FileOutputStream fout = new FileOutputStream(file);
        fout.write(data);
        fout.close();

	}

}
