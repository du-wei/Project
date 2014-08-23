package com.webapp.lucene;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Fields;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.MultiFields;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Bits;
import org.apache.lucene.util.BytesRef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HighFreqTerms {
    private static final Logger LOG = LoggerFactory.getLogger(HighFreqTerms.class);

    public static void main(String[] args) throws Exception {
        String file = "E:\\workspace\\component\\index.file\\search\\index.20140623_170047\\result";
        String[] fields = new String[] {"title"};
        IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(file)));
        TermStats[] terms = getHighFreqTerms(reader, 100, fields);
        for (int i = 0; i < terms.length; i++) {
            System.out.printf("%s:%s %,d \n", terms[i].field, terms[i].termtext.utf8ToString(), terms[i].docFreq);
        }
        reader.close();
    }

    private static final TermStats[] EMPTY_STATS = new TermStats[0];

    public static TermStats[] getHighFreqTerms(IndexReader reader, int numTerms, String[] fieldNames) throws Exception {

        List<TermStats> list = new ArrayList<TermStats>();
        if (fieldNames != null) {
            Fields fields = MultiFields.getFields(reader);
            if (fields == null) {
                LOG.info("Index with no fields - probably empty or corrupted");
                return EMPTY_STATS;
            }
            TermsEnum te = null;
            for (String field : fieldNames) {
                Terms terms = fields.terms(field);
                if (terms != null) {
                    te = terms.iterator(te);

                    while (true) {
                        BytesRef term = te.next();
                        if (term != null) {
                            BytesRef r = new BytesRef();
                            r.copyBytes(term);
                            list.add(new TermStats(field, r, te.docFreq()));
                        } else {
                            break;
                        }
                    }
                }
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
