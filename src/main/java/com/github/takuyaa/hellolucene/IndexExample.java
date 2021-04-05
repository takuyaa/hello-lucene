package com.github.takuyaa.hellolucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.IOUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class IndexExample {
    public static void main(String[] args) throws IOException {
        // Create a directory for storing Lucene index
        Path indexDirPath = Path.of("index");
        IOUtils.rm(indexDirPath);
        Files.createDirectory(indexDirPath);
        System.out.println("Directory " + indexDirPath.toAbsolutePath() + " created for storing Lucene index.");
        Directory directory = FSDirectory.open(indexDirPath);

        // Set up IndexWriter
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        config.setUseCompoundFile(false);
        IndexWriter indexWriter = new IndexWriter(directory, config);

        // Index "Lucene in Action"
        Document doc1 = new Document();
        doc1.add(new Field("title", "Lucene in Action", TextField.TYPE_STORED));
        indexWriter.addDocument(doc1);

        // Index "Lucene Cookbook"
        Document doc2 = new Document();
        doc2.add(new Field("title", "Lucene Cookbook", TextField.TYPE_STORED));
        indexWriter.addDocument(doc2);

        // Write index files to the directory
        indexWriter.close();
    }
}
