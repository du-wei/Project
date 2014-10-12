package com.webapp.junit.dbmonster;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.apache.commons.logging.Log;

import pl.kernelpanic.dbmonster.DBMonster;
import pl.kernelpanic.dbmonster.DBMonsterContext;
import pl.kernelpanic.dbmonster.generator.BasicDataGenerator;
import pl.kernelpanic.dbmonster.generator.Initializable;
import pl.kernelpanic.dbmonster.generator.StringGenerator;

public class BinaryStrGenerator extends BasicDataGenerator implements
		Initializable {

	/** pointer into the fileList to keep track of which file to use next */
	private int currentIndex = -1;

	/** holds the list of files that are used to populate binary columns */
	private ArrayList fileList = new ArrayList();

	/** Random number generator. */
	private Random random = null;

	/** logger for this class */
	private Log log = null;

	/** the maximum length of any column */
	private int maxLength = 0;
	/**
	 * Initializes a class with <code>DBMonsterContext</code>.
	 *
	 * @param ctx
	 *            context
	 *
	 * @throws Exception
	 *             if initialization fails
	 */

	private StringGenerator stringGenerator = new StringGenerator();

	@Override
	public void initialize(DBMonsterContext ctx) throws Exception {
		random = (Random) ctx.getProperty(DBMonster.RANDOM_KEY);
		log = (Log) ctx.getProperty(DBMonster.LOGGER_KEY);

		stringGenerator.initialize(ctx);
	}

	/**
	 * Generates byte[] from the next file in our list of configured files.
	 *
	 * @return value.
	 */
	@Override
	public Object generate() throws Exception {
		if (nulls != 0 && random.nextInt(101) <= nulls) {
			return null;
		}

		if (fileList.size() > 0) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataInputStream is = null;
			String filename = (String) fileList.get(getNextIndex());
			try {
				File f = new File(filename);
				is = new DataInputStream(new FileInputStream(f));
				byte[] buffer = new byte[8192];
				int result = is.read(buffer);
				int bytesWritten = result;
				while (result != -1) {
					baos.write(buffer);
					result = is.read(buffer);
					bytesWritten += result;
				}
				if (log.isDebugEnabled()) {
					StringBuffer sb = new StringBuffer();
					sb.append("generate: bytesWritten=");
					sb.append(bytesWritten);
					sb.append(" for file=");
					sb.append(filename);
					log.debug(sb.toString());
				}
			} catch (IOException e) {
				log.error("Unable to build inputstream for input file="
						+ filename, e);
			} finally {
				if (is != null)
					try {
						is.close();
					} catch (IOException e) {
					}
			}
			byte[] result = baos.toByteArray();
			// shrink the array if it exceeds the maxLength
			if (maxLength > 0 && result.length > maxLength) {
				byte[] newresult = new byte[maxLength];
				for (int i = 0; i < maxLength; i++) {
					newresult[i] = result[i];
				}
				result = newresult;
			}
			return result;
		} else {
			String result = (String) stringGenerator.generate();
			return result.getBytes();
		}

	}

	@Override
	public void reset() {
		currentIndex = 0;
	}

	/**
	 * Sets a filename containing content to be used as a field value in the
	 * database.
	 * 
	 * @param value
	 *            the absolute path to a file that will be read in and stored in
	 *            a table column.
	 */
	public void setFile(String value) {
		fileList.add(value);
	}

	/**
	 * Returns the maximal length of the string.
	 *
	 * @return the maximal value of generated string
	 */
	public int getMaxLength() {
		return maxLength;
	}

	/**
	 * Sets the maximal length of the string.
	 *
	 * @param length
	 *            maximal length
	 */
	public void setMaxLength(int length) {
		maxLength = length;
	}

	/**
	 * Loop around to 0 if we've reached the end of the list.
	 * 
	 * @return
	 */
	private int getNextIndex() {
		currentIndex++;
		if (currentIndex >= fileList.size()) {
			currentIndex = 0;
		}
		return currentIndex;
	}

}
