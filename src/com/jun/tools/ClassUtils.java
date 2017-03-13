package com.jun.tools;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassUtils {
	
	public static ClassLoader getClassLoader() {
		return Thread.currentThread().getContextClassLoader() ;
	}
	
	public static Class<?> loadClass(String className,boolean isInitialized) {
		Class<?> c = null ;
		try {
			c = Class.forName(className,isInitialized,getClassLoader());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("load class failure");
			e.printStackTrace();
		}
		return c ;
	}
	
	public static Set<Class<?>>	getClassSet(String packageName) throws IOException {
		Set<Class<?>> classSet = new HashSet<Class<?>>(); 
		//package name format like "com.jun.controller"
		Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
		//package name format like "com/jun/controller"
		while (urls.hasMoreElements()) {
			URL url = (URL) urls.nextElement();
			if (url != null) {
				String protocol = url.getProtocol() ;
				if (protocol.equals("file")) {
					String packagePath = url.getPath().replaceAll("%20", " ");
					addClass(classSet, packagePath, packageName);
				}else if (protocol.equals("jar")) {
					JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
					if (jarURLConnection != null) {
						JarFile jarFile = jarURLConnection.getJarFile();
						if (jarFile != null) {
							Enumeration<JarEntry> jarEntries = jarFile.entries();
							while (jarEntries.hasMoreElements()) {
								JarEntry jarEntry = (JarEntry) jarEntries.nextElement();
								String jarEntryName = jarEntry.getName();
								if (jarEntryName.endsWith(".class")) {
									String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
									doAddClass(classSet, className);
								}
							}
						}
					}
				}
			}
			
		}
		return classSet;
	}
	
	private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
		//处理.class文件和文件目录
		File[] files = new File(packagePath).listFiles(new FileFilter() {
			
			public boolean accept(File file) {
				// TODO Auto-generated method stub
				return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory() ;
			}
		});
		
		for (File file : files) {
			String fileName = file.getName();
			if (file.isFile()) {
				String className = fileName.substring(0,fileName.lastIndexOf("."));
				if (StringUtils.isNotEmpty(packageName)) {
					className = packageName + "." +className;
				}
				doAddClass(classSet, className);
			}else {
				//file是一个文件夹
				String subPackagePath = fileName;
				if (StringUtils.isNotEmpty(packagePath)) {
					subPackagePath = packagePath + "/" + subPackagePath;
				}
				String subPackageName = fileName;
				if (StringUtils.isNotEmpty(packageName)) {
					subPackageName = packageName + "/" + subPackageName ;
				}
				addClass(classSet, subPackagePath, subPackageName);
			}
		}
	}
	
	private static void doAddClass(Set<Class<?>> classSet,String className) {
		Class<?> c = loadClass(className,false);
		classSet.add(c);
	}
	
}
