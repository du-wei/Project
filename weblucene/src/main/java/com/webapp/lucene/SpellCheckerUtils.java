package com.webapp.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.search.spell.Dictionary;
import org.apache.lucene.search.spell.LuceneDictionary;
import org.apache.lucene.search.spell.SpellChecker;
import org.apache.lucene.util.Version;

public class SpellCheckerUtils {
	
	public static SpellChecker getSpellChecker(String field, String indexDir, String spellDir) {

		IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_47, new StandardAnalyzer(Version.LUCENE_47));
        conf.setOpenMode(OpenMode.CREATE);

        DirectoryReader reader = null;
        try {
			reader = DirectoryReader.open(LuceneUtils.getDirectory(indexDir));
			
			Dictionary dic = new LuceneDictionary(reader, field);

			SpellChecker sc = new SpellChecker(LuceneUtils.getDirectory(spellDir));
			sc.indexDictionary(dic, conf, false);
			return sc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }
	
}
