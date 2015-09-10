package com.webapp.nio;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.DosFileAttributes;

public class NioPath {

	public static void main(String[] args) throws Exception {

		// List<String> list = new ArrayList<>();
		// list.add("a");
		// list.add("b");
		// list.add("c");
		// list.add("d");
		// System.out.println(list);
		//
		// List<String> list2 = new ArrayList<>();
		// list2.add("a");
		// list2.add("b");
		// System.out.println(list2);
		//
		// list.removeAll(list2);
		//
		// System.out.println(list);
		// System.out.println(list2);

		FileChannel fileChannel = FileChannel.open(
		        Paths.get("E://okhello.txt"), StandardOpenOption.CREATE,
		        StandardOpenOption.WRITE);
		fileChannel.close();

	}

	private static Path text() throws IOException {
		Path path1 = Paths.get("folder1", "sub1", "txt.txt");
		Path path2 = Paths.get("folder2", "sub2");

		Path paths = Paths.get("E://ss");

		DosFileAttributeView dos = Files.getFileAttributeView(paths,
		        DosFileAttributeView.class);
		DosFileAttributes attr = dos.readAttributes();
		System.out.println(attr.isReadOnly());
		// Files.getAttribute(paths, "dd");

		try (DirectoryStream<Path> stream = Files.newDirectoryStream(paths)) {
			for (Path entry : stream) {
				System.out.println(entry);
			}
		}
		return paths;
	}

	public static void watchfile() throws Exception {
		WatchService service = FileSystems.getDefault().newWatchService();
		Path path = Paths.get("E://ss").toAbsolutePath();
		path.register(service, StandardWatchEventKinds.ENTRY_CREATE);
		while (true) {
			WatchKey key = service.take();
			for (WatchEvent<?> event : key.pollEvents()) {
				Path createdPath = (Path) event.context();
				createdPath = path.resolve(createdPath);
				long size = Files.size(createdPath);
				System.out.println(createdPath + " ==> " + size);
			}
			key.reset();
		}
	}

	public static void createFile() throws Exception {
		Path base = Files.createDirectories(Paths.get("E:\\hello", "file"));
		Path filePath = Paths.get(base.toString(), "ok.txt");
		Path file = Files.notExists(filePath) ? Files.createFile(filePath)
		        : filePath;

		// Path target = Paths.get ("D:\\Backup\\MyStuff.txt");
		// Set<PosixFilePermission> perms = PosixFilePermissions.fromString
		// ("rw-rw-rw-");
		// FileAttribute<Set<PosixFilePermission>> attr =
		// PosixFilePermissions.asFileAttribute(perms);
		// Files.createFile (target, attr);
	}

	public static void delFile() throws Exception {
		Path target = Paths.get("D:\\Backup\\MyStuff.txt");
		Files.delete(target);
	}

	public static void copyFile() throws Exception {
		Path source = Paths.get("C:\\My Documents\\Stuff.txt");
		Path target = Paths.get("D:\\Backup\\MyStuff.txt");
		Files.copy(source, target);
	}

	public static void copyFileOther() throws Exception {
		Path source = Paths.get("C:\\My Documents\\Stuff.txt");
		Path target = Paths.get("D:\\Backup\\MyStuff.txt");
		Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
	}

	public static void moveFile() throws Exception {
		Path source = Paths.get("C:\\My Documents\\Stuff.txt");
		Path target = Paths.get("D:\\Backup\\MyStuff.txt");
		Files.move(source, target, StandardCopyOption.REPLACE_EXISTING,
		        StandardCopyOption.COPY_ATTRIBUTES);
	}

	public static void p(Object obj) {
		System.out.println(obj);
	}
}
