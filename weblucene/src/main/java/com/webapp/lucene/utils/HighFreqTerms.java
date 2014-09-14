package com.webapp.lucene.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Fields;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.MultiFields;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.IOContext;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Bits;
import org.apache.lucene.util.BytesRef;

public class HighFreqTerms {


	private static Logger logger = LogManager.getLogger(HighFreqTerms.class);

    public static void main(String[] args) throws Exception {

    	RAMDirectory ram = new RAMDirectory(FSDirectory.open(new File("./index")) ,IOContext.DEFAULT);
    	IndexReader indexReader = DirectoryReader.open(ram);

    	Fields fields = MultiFields.getFields(indexReader);
    	Terms terms = fields.terms("title");
    	if(terms == null) return;

    	TermsEnum termsEnum = terms.iterator(null);
        while (true) {
            BytesRef term = termsEnum.next();
            if (term == null)  break;
            BytesRef copy = new BytesRef();
            copy.copyBytes(term);
            TermStats termStats = new TermStats("title", copy, termsEnum.docFreq());
            System.out.println(termStats.getTermText());
        }
    }

    public static TermStats[] getHighFreqTerms(IndexReader reader, int numTerms, String[] fieldNames) throws Exception {

        List<TermStats> list = new ArrayList<TermStats>();
        Fields fields = MultiFields.getFields(reader);
        if (fields == null) {
            logger.info("Index with no fields - probably empty or corrupted");
            return new TermStats[0];
        }
        for (String field : fieldNames) {
        	Terms terms = fields.terms(field);
        	if(terms == null) return null;

        	TermsEnum termsEnum = terms.iterator(null);
            while (true) {
                BytesRef term = termsEnum.next();
                if (term == null)  break;
                BytesRef copy = new BytesRef();
                copy.copyBytes(term);
                TermStats termStats = new TermStats("title", copy, termsEnum.docFreq());
                System.out.println(termStats.getTermText());
                list.add(new TermStats(field, term, termsEnum.docFreq()));
            }
        }

        TermStats[] result = list.toArray(new TermStats[] {});
        return result;
    }

    public static long getTotalTermFreq(IndexReader reader, String field, BytesRef termtext) throws Exception {
        BytesRef br = termtext;
        long totalTF = 0;
        try {
            Bits liveDocs = MultiFields.getLiveDocs(reader);
            // totalTF = MultiFields.totalTermFreq(reader, field, termtext);
            totalTF = reader.totalTermFreq(new Term(field, br));
            return totalTF;
        } catch (Exception e) {
            return 0;
        }
    }

}
