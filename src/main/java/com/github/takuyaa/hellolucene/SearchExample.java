package com.github.takuyaa.hellolucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Path;

public class SearchExample {
    public static void main(String[] args) throws IOException {
        // Open a directory which stores index
        Directory directory = FSDirectory.open(Path.of("index"));

        // Create an IndexSearcher
        IndexReader indexReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        // Create Query object that searches for "lucene" on "title" field
        Query query = new TermQuery(new Term("title", "lucene"));
        TopDocs results = indexSearcher.search(query, 10);
        ScoreDoc[] hits = results.scoreDocs;

        // Iterate through the results
        for (ScoreDoc hit : hits) {
            Document hitDoc = indexSearcher.doc(hit.doc);
            System.out.println("Hit: " + hitDoc.get("title"));
        }

        // Post-processing
        indexReader.close();
        directory.close();
    }
}
