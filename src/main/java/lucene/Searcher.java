package lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * 根据索引搜索
 *
 * @author lwk
 * @date 2019-07-25 15:32
 */
public class Searcher {
    public static void search(String indexDir, String content)   {
        IndexReader reader = null;

        try {
            // 得到读取索引文件的路径
            Directory dir = FSDirectory.open(Paths.get(indexDir));
            // 通过dir得到的路径下的所有的文件
            reader = DirectoryReader.open(dir);
            // 建立索引查询器
            IndexSearcher is = new IndexSearcher(reader);
            // 实例化分析器
            Analyzer analyzer = new StandardAnalyzer();
            // 中文分词器、效果不好
//            Analyzer analyzer = new SmartChineseAnalyzer();
            // 建立查询解析器
            /**
             * 第一个参数是要查询的字段； 第二个参数是分析器Analyzer、索引名字contents，参考Indexer.java
             */
            QueryParser parser = new QueryParser("contents", analyzer);
            parser.setDefaultOperator(QueryParser.Operator.OR);
            // 根据传进来的p查找
            Query query = parser.parse(content);

            // 计算索引开始时间
            long start = System.currentTimeMillis();
            // 开始查询
            /**
             * 第一个参数是通过传过来的参数来查找得到的query； 第二个参数是要出查询的行数
             */
            TopDocs hits = is.search(query, 10);
            // 计算索引结束时间
            long end = System.currentTimeMillis();
            System.out.println("匹配 " + content + " ，总共花费" + (end - start) + "毫秒" + "查询到" + hits.totalHits + "个记录");
            // 遍历hits.scoreDocs，得到scoreDoc
            /**
             * ScoreDoc:得分文档,即得到文档 scoreDocs:代表的是topDocs这个文档数组
             *
             * @throws Exception
             */
            for (ScoreDoc scoreDoc : hits.scoreDocs) {
                Document doc = is.doc(scoreDoc.doc);
                System.out.println(doc.get("fullPath"));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }finally {
            // TODO 回头改一下 try resource 方式
            // 关闭reader
            if (reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) {
        String indexDir = "D:\\文档\\java 教程\\深入拆解 Java 虚拟机\\htmlIndex";
        //我们要搜索的内容
        String q = "OOPSLA";
        try {
            search(indexDir, q);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
