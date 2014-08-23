package com.webapp.lucene;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinYinUtils {

	public static List<String> getPinYin(String words)
			throws BadHanyuPinyinOutputFormatCombination {
		List<String> pinyins = new ArrayList<String>();

		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		format.setVCharType(HanyuPinyinVCharType.WITH_V);

		int wordNum = words.length();
		for (int i = 0; i < wordNum; i++) {
			String[] pinyin = PinyinHelper.toHanyuPinyinStringArray(
					words.charAt(i), format);
			if (pinyin != null) {
				for (String word : pinyin) {
					if (!pinyins.contains(word)) {
						pinyins.add(word);
					}
				}
			}
		}
		return pinyins;
	}

}
