package com.webapp.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.List;

public class FilesUtils {

	public static void main(String[] args) throws Exception {
		// Path path = Paths.get(System.getProperty("user.dir"), "file",
		// "file.txt");
		// Path paths = Paths.get("E:\\ok\\ok", "file.txt");
		//
		// String temp = "ssssw";

		Runtime run = Runtime.getRuntime();
		Process p = run.exec("gawk -f E:\\wh \"ok=100\" E:\\hello.txt");
		// Process p = run.exec("./replace.sh one two");

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				p.getInputStream()));
		//
		String s = "h";
		while ((s = reader.readLine()) != null) {
			System.out.println(s);
		}
		reader.close();

	}

	public static void replaceLine() {

	}

	/* --------------------- InsertLines ---------------------- */

	public static void insertLines(Path path, String buffer, int preLines)
			throws Exception {

		FileChannel file = FileChannel.open(notExistCreate(path),
				StandardOpenOption.READ, StandardOpenOption.WRITE);

		String temp = buffer + "\n";
		long fileSize = file.size();
		long totalSize = fileSize + temp.length();

		MappedByteBuffer mbb = file.map(MapMode.READ_WRITE, 0, totalSize); // 总文件

		int position = insertBeforeSize(mbb, preLines);
		int afterSize = (int) (fileSize - position);

		byte[] tempByte = new byte[afterSize];

		MappedByteBuffer insertAfter = file.map(MapMode.PRIVATE, position,
				afterSize); // 插入行后面的内容
		insertAfter.get(tempByte);

		mbb.position(position);
		mbb.put(temp.getBytes());
		mbb.put(tempByte);

		mbb.force();
		file.close();
	}

	/* --------------------- WriteLines ---------------------- */

	// write ByteBuffer to path
	public static int writeLines(Path path, ByteBuffer buffer) {
		FileChannel file = null;
		try {
			file = FileChannel.open(notExistCreate(path),
					StandardOpenOption.CREATE, StandardOpenOption.WRITE,
					StandardOpenOption.TRUNCATE_EXISTING);
			return file.write(buffer);

		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		} finally {
			try {
				if (file != null)
					file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// write CharBuffer to path
	public static int writeLines(Path path, byte[] buffer) {
		return writeLines(path, EncodeUtils.encode(buffer));
	}

	// write CharBuffer to path
	public static int writeLines(Path path, CharBuffer buffer) {
		return writeLines(path, EncodeUtils.encode(buffer));
	}

	// write String to path
	public static int writeLines(Path path, String str) {
		return writeLines(path, EncodeUtils.encode(str));
	}

	public static int writeLines(Path path, List<String> lines, String split) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < lines.size(); i++) {
			buffer.append(lines.get(i) + split);
		}
		buffer.delete(buffer.length() - 1, buffer.length());
		return writeLines(path, buffer.toString());
	}

	public static int writeLines(Path path, List<String> lines) {
		return writeLines(path, lines, "\n");
	}

	/* --------------------- AppendLine ---------------------- */

	public static int appendLine(Path path, ByteBuffer buffer,
			boolean checkLineMark) {
		buffer.rewind();
		FileChannel file = null;
		try {
			file = FileChannel.open(notExistCreate(path),
					StandardOpenOption.CREATE, StandardOpenOption.APPEND);
			if (checkLineMark) {
				if (!existLineMark(path)) {
					file.write(EncodeUtils.encode("\n"));
				}
			} else {
				file.write(EncodeUtils.encode("\n"));
			}
			return file.write(buffer);
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		} finally {
			try {
				if (file != null)
					file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static int appendLine(Path path, ByteBuffer buffer) {
		return appendLine(path, buffer, false);
	}

	public static int appendLine(Path path, byte[] byteArray,
			boolean checkLineMark) {
		return appendLine(path, EncodeUtils.encode(byteArray), checkLineMark);
	}

	public static int appendLine(Path path, byte[] byteArray) {
		return appendLine(path, EncodeUtils.encode(byteArray));
	}

	public static int appendLine(Path path, CharBuffer buffer,
			boolean checkLineMark) {
		return appendLine(path, EncodeUtils.encode(buffer), checkLineMark);
	}

	public static int appendLine(Path path, CharBuffer buffer) {
		return appendLine(path, EncodeUtils.encode(buffer));
	}

	public static int appendLine(Path path, String str, boolean checkLineMark) {
		return appendLine(path, EncodeUtils.encode(str), checkLineMark);
	}

	public static int appendLine(Path path, String str) {
		return appendLine(path, EncodeUtils.encode(str));
	}

	public static int appendLine(Path path, List<String> lines,
			boolean checkLineMark) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < lines.size(); i++) {
			buffer.append(lines.get(i) + "\n");
		}
		buffer.delete(buffer.length() - 1, buffer.length());
		return appendLine(path, buffer.toString(), checkLineMark);
	}

	public static int appendLine(Path path, List<String> lines) {
		return appendLine(path, lines, false);
	}

	/* --------------------- ReadLine ---------------------- */

	public static List<String> readAllLines(Path path) {
		try {
			return Files.readAllLines(path, EncodeUtils.getUTFCharset());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static byte[] readAllBytes(Path path) {
		try {
			return Files.readAllBytes(path);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/* --------------------- Files ---------------------- */

	// createDirAndFile
	public static Path createDirAndFile(String file, String... dir) {
		Path path = null;
		try {
			path = Paths.get(Files.createDirectories(Paths.get("", dir))
					.toString(), file);
			if (Files.notExists(path)) {
				return Files.createFile(path);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return path;
	}

	public static Path createDirAndFile(String file, Path path) {
		return createDirAndFile(file, path.toString());
	}

	public static Path createDirAndFile(Path path) {
		return createDirAndFile(path.getFileName().toString(), path.getParent());
	}

	/* --------------------- Tools ---------------------- */

	public static Path notExistCreate(Path path) {
		return createDirAndFile(path);
	}

	public static boolean existLineMark(Path path) {
		if (Files.notExists(path)) {
			return false;
		}
		try {
			FileChannel file = FileChannel.open(path, StandardOpenOption.READ);
			ByteBuffer byteBuffer = ByteBuffer.allocate(1);
			long fileSize = Files.size(path);
			if (fileSize > 0) {
				if (file.read(byteBuffer, fileSize - 1) > 0) {
					if ((char) byteBuffer.get(0) == '\n') {
						return true;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static int insertBeforeSize(ByteBuffer buffer, int lines) {
		int position = 0;
		if (lines == 0)
			return 0;
		buffer.rewind();
		for (int i = 0; i < buffer.limit(); i++) {
			if ((char) buffer.get() == '\n' && ++position >= lines) {
				return i + 1;
			}
		}
		return buffer.limit();
	}

	public static int whichLineSize(ByteBuffer buffer, int line) {
		int position = 0;
		if (line == 0)
			return 0;

		int count = 0;
		boolean flag = false;
		buffer.rewind();
		for (int i = 0; i < buffer.limit(); i++) {
			if (position + 1 == line) {
				count++;
				flag = true;
			}
			if ((char) buffer.get() == '\n') {
				if (flag) {
					break;
				}
				position++;
			}
		}
		return count;
	}

	public static void cleaner(final Object buffer) throws Exception {
		AccessController.doPrivileged(new PrivilegedAction<Object>() {
			@Override
			public Object run() {
				try {
					Method getCleanerMethod = buffer.getClass().getMethod(
							"cleaner", new Class[0]);
					getCleanerMethod.setAccessible(true);
					// sun.misc.Cleaner cleaner = (sun.misc.Cleaner)
					// getCleanerMethod.invoke(buffer, new Object[0]);
					// cleaner.clean();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		});
	}
}
