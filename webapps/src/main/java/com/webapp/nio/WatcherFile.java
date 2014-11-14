/** @Title: WatcherFile.java
 * @Package com.webapp.nio
 * @Description: TODO 描述
 * @author king chenglong@coweibo.cn
 * @date 2012-9-14 下午5:56:15
 * @version V1.0 */
package com.webapp.nio;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

/** @ClassName: WatcherFile.java
 * @Package com.webapp.nio
 * @Description: TODO 类型描述
 * @author king chenglong@coweibo.cn
 * @date 2012-9-14 下午5:56:15
 * @version V1.0 */
public class WatcherFile {

	public static void main(String[] args) {

		try {
			new WatcherFile().dojob();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** @throws Exception
	 * @Title: dojob
	 * @Description: TODO 方法描述
	 * @param 参数描述
	 * @return void 返回类型描述
	 * @throws */

	private WatchService watcher;
	private Map<WatchKey, Path> keys;

	private WatcherFile() throws Exception {
		watcher = FileSystems.getDefault().newWatchService();
		keys = new HashMap<WatchKey, Path>();
	}

	private void register(Path dir) {
		WatchKey key = null;
		try {
			key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE,
			        ENTRY_MODIFY);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Path prev = keys.get(key);
		if (prev == null) {
			System.out.format("register: %s\n", dir);
		} else {
			if (!dir.equals(prev)) {
				System.out.format("update: %s -> %s\n", prev, dir);
			}
		}
		keys.put(key, dir);
	}

	private void registerAll(Path start) throws IOException {
		Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult preVisitDirectory(Path dir,
			        BasicFileAttributes attrs) {
				register(dir);
				return FileVisitResult.CONTINUE;
			}
		});
	}

	private void dojob() throws Exception {
		System.out.println("starting");
		Path base = Paths.get("E:\\testpath");

		Path path = base.toAbsolutePath();
		path.register(watcher, ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE);

		while (true) {
			WatchKey key = watcher.take();
			for (WatchEvent<?> event : key.pollEvents()) {
				Kind<?> kind = event.kind();

				Path dir = keys.get(key);
				dir = dir == null ? path.resolve((Path) event.context()) : dir;

				if (kind.name().equals(ENTRY_CREATE.name())) {
					registerAll(dir);
				}
				if (kind.name().equals(ENTRY_MODIFY.name())) {
					System.out.println("modify - " + dir);
				}
				if (kind.name().equals(ENTRY_DELETE.name())) {
					System.out.println("del - " + dir);
				}

			}
			boolean valid = key.reset();
			if (!valid) {
				keys.remove(key);
			}
			System.out.println("stoping");
		}

	}

}
