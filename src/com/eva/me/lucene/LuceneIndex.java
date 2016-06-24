package com.eva.me.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.eva.me.model.QAPairAdv;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Vector;


public class LuceneIndex {

    /**
     * static code block
     * init the property and classpath, the dict file load once only.
     */
    static Properties properties = null;
    static String classpath = null;
    static QaFileOperate qaFileOperate = null;

    public static String fileDirectoryPath = "";
    static String indexDirectoryPath = "";


    static {
        properties = new Properties();
        classpath = LuceneIndex.class.getClassLoader().getResource("").getPath();
        System.out.println("classpath:"+classpath);
        try {
            properties.load(new FileInputStream(classpath + "/proper.properties"));
        } catch (FileNotFoundException e) {
            System.err.println("cannot find proper file...");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("read the properties file is error...");
            e.printStackTrace();
        }
//        fileDirectoryPath = classpath  + properties.getProperty("faqfolder");
//        fileDirectoryPath = "d://test//document";
        fileDirectoryPath = properties.getProperty("faqfolder");
//        indexDirectoryPath = classpath  + properties.getProperty("indexfolder");
//        indexDirectoryPath = "d://test//index";
        indexDirectoryPath = properties.getProperty("indexfolder");
        System.out.println("fileDirectoryPath:"+fileDirectoryPath+"|\nindexDirectoryPath:"+indexDirectoryPath);
        qaFileOperate = SingleQaFileOperate.getSinleQaFileOperate();
    }

    public LuceneIndex(String fileDirectoryPath, String indexDirectoryPath){
        this.fileDirectoryPath = fileDirectoryPath;
        this.indexDirectoryPath = indexDirectoryPath;
    }

    public LuceneIndex(){}

    public static void main(String[] ag) throws IOException {
        LuceneIndex a = new LuceneIndex();
        System.out.println("start rebuild...");
        a.reBuildIndex();
        System.out.println("start search...");
        a.addIndex("C:\\Users\\violi\\Desktop\\毕设\\Search\\Search\\src\\main\\resources\\document\\allFaq\\00aba82892252b502cabc9db11982fa5.txt");
        System.out.println("add index success...");
        a.reBuildIndex();
        a.search("二维码发票可以网上认证吗", 10);
        a.search("远程认证", 10);
    }

    /**
     * @param indexDir(the index dir position)
     * @return indexWriter(init the indexwriter)
     * @throws IOException
     */
    public IndexWriter initIndexWriter(File indexDir)
            throws IOException {
        Directory indexDirectory = FSDirectory.open(indexDir);
        IKAnalyzer ik = new IKAnalyzer(true);
        IndexWriterConfig iwConfig = new IndexWriterConfig(Version.LUCENE_44, ik);
        return new IndexWriter(indexDirectory, iwConfig);
    }

    /**
     * @param iw(the index writer)
     * @param file(the file to be indexed)
     * @throws IOException
     */
    public void index(IndexWriter iw, File file) throws IOException {
        if (file.isFile()) {
            System.out.println("current file is " + file.getName());
            Document doc = new Document();
            String question = qaFileOperate.getFileContentOfTag(file, "question");
            String answer = qaFileOperate.getFileContentOfTag(file, "answer");
            //doc.add(new TextField("ID", file.getName(), Field.Store.YES, Field.Index.ANALYZED));
            doc.add(new Field("ID", file.getName().toString(), TextField.TYPE_STORED));
            doc.add(new Field("question", qaFileOperate.getFileContentOfTag(file, "question"), TextField.TYPE_STORED));
            doc.add(new Field("answer", qaFileOperate.getFileContentOfTag(file, "answer"), TextField.TYPE_STORED));
            //doc.add(new TextField("question",new FileReader(file)));
            iw.addDocument(doc);
            
            qaFileOperate.copyFile(file, fileDirectoryPath);
        } else {
            System.out.println("while indexing, it is not a file...");
        }
    }

    public void addIndex(String filePath) {
        //add index
        IndexWriter iw = null;
        try {
            iw = initIndexWriter(new File(indexDirectoryPath));
        } catch (IOException e) {
            System.err.println("indexwriter init error...");
            e.printStackTrace();
        }
        File file = new File(filePath);
        //delete the same old index
        try {
            iw.deleteDocuments(new Term("ID", file.getName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //start add index
        //System.out.println("start build index...");
        if (file.exists()) {
            try {
                index(iw, file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            System.err.println(filePath + " dont exist!");
        }

        try {
            iw.close();
        } catch (IOException e) {
            System.err.println("close the index writer error...");
            e.printStackTrace();
        }
        //System.out.println("add index finish...");
    }

    /**
     * rebuild all index of files that in faq
     * (Attention:the older index will be deleted)
     */
    public void reBuildIndex() {
        //rebuild index
        //delete former index files
        qaFileOperate.deleteFolder(indexDirectoryPath);

        //File indexDir = new File(indexDirectoryPath);
        IndexWriter iw = null;
        try {
            iw = initIndexWriter(new File(indexDirectoryPath));
        } catch (IOException e) {
            System.err.println("indexwriter init error...");
            e.printStackTrace();
        }

        File file = new File(fileDirectoryPath);
        //System.out.println("start build index...");
        if (file.isFile()) {
            try {
                index(iw, file);
            } catch (IOException e) {
                System.err.println("index the file:" + file.getName() + " error...");
                e.printStackTrace();
            }
        } else {
            String[] fileList = file.list();
            for (int i = 0; i < fileList.length; i++) {
                File oneFile = new File(fileDirectoryPath + "/" + fileList[i]);
                if (oneFile.isFile()) {
                    try {
                        index(iw, oneFile);
                    } catch (IOException e) {
                        System.err.println("index the file:" + file.getName() + " error...");
                        e.printStackTrace();
                    }
                }
            }
        }
        try {
            iw.close();
        } catch (IOException e) {
            System.err.println("close the index writer error...");
            e.printStackTrace();
        }
        //System.out.println("build index finish...");
    }

    /**
     * @param sentence
     * @param topN
     * @return TopDocs(return the topN most similar files' ID)
     */
    public Vector<Document> search(String sentence, int topN)
            throws IOException {

        //Directory
        Directory indexDirectory = FSDirectory.open(new File(indexDirectoryPath));
        //indexreader
        IndexReader indexReader = DirectoryReader.open(indexDirectory);
        //indexsearcher
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        QueryParser queryParser = new QueryParser(Version.LUCENE_44, "question", new IKAnalyzer(true));
        Query query = null;
        try {
            query = queryParser.parse(sentence);
        } catch (ParseException e) {
            System.err.println("query sentence:" + sentence + " is error...");
            e.printStackTrace();
        }

        ScoreDoc[] hits = null;
        try {
            hits = indexSearcher.search(query, null, topN).scoreDocs;
        } catch (IOException e) {
            System.err.println("doc get error...");
            e.printStackTrace();
        }
        System.out.println("hits len:"+hits.length);

        //print doc
        Vector<Document> docList = new Vector<Document>();
        for (int i = 0; i < hits.length; i++) {
            Document doc = indexSearcher.doc(hits[i].doc);
            docList.add(doc);
            System.out.println("\nid:"+doc.get("ID")
            +"\nquestion:"+ doc.get("question") 
            +"\nscore:"+ hits[i].score);
        }

        return docList;
    }
    
    public Vector<QAPairAdv> searchOrigin(String sentence, int topN)
            throws IOException {

        //Directory
        Directory indexDirectory = FSDirectory.open(new File(indexDirectoryPath));
        //indexreader
        IndexReader indexReader = DirectoryReader.open(indexDirectory);
        //indexsearcher
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        QueryParser queryParser = new QueryParser(Version.LUCENE_44, "question", new IKAnalyzer(true));
        Query query = null;
        try {
            query = queryParser.parse(sentence);
        } catch (ParseException e) {
            System.err.println("query sentence:" + sentence + " is error...");
            e.printStackTrace();
        }

        ScoreDoc[] hits = null;
        try {
            hits = indexSearcher.search(query, null, topN).scoreDocs;
        } catch (IOException e) {
            System.err.println("doc get error...");
            e.printStackTrace();
        }
        System.out.println("hits len:"+hits.length);

        //print doc
        Vector<QAPairAdv> qapList = new Vector<QAPairAdv>();
        for (int i = 0; i < hits.length; i++) {
            Document doc = indexSearcher.doc(hits[i].doc);
            float scor = hits[i].score;
            QAPairAdv qapa = new QAPairAdv(doc, scor);
            
            qapList.add(qapa);
            System.out.println("\nid:"+doc.get("ID")
            +"\nquestion:"+ doc.get("question") 
            +"\nscore:"+ hits[i].score);
        }

        return qapList;
    }
}
