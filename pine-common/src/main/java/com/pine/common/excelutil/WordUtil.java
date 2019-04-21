package com.pine.common.excelutil;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: Pine
 * @Date: 2018/7/25 上午10:23
 * @Email:771190883@qq.com
 */
public class WordUtil {
    public static void testWord(String filePath) {
        try {
            FileInputStream in = new FileInputStream(filePath);//载入文档 //如果是office2007  docx格式
            if (filePath.toLowerCase().endsWith("docx")) {
                //word 2007 图片不会被读取， 表格中的数据会被放在字符串的最后
                XWPFDocument xwpf = new XWPFDocument(in);//得到word文档的信息
//             List<XWPFParagraph> listParagraphs = xwpf.getParagraphs();//得到段落信息
                Iterator<XWPFTable> it = xwpf.getTablesIterator();//得到word中的表格

                while (it.hasNext()) {

                    XWPFTable table = it.next();
                    List<XWPFTableRow> rows = table.getRows();
                    //读取每一行数据
                    for (int i = 1; i < rows.size(); i++) {
                        XWPFTableRow row = rows.get(i);
                        //读取每一列数据
                        List<XWPFTableCell> cells = row.getTableCells();
                        for (int j = 0; j < cells.size(); j++) {
                            XWPFTableCell cell = cells.get(j);
                            //输出当前的单元格的数据
                            System.out.println(cell.getText());
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void docWord(String filePath) {
        try {
            FileInputStream in = new FileInputStream(filePath);//载入文档 //如果是office2007  docx格式
            if (filePath.toLowerCase().endsWith("doc")) {
                POIFSFileSystem pfs = new POIFSFileSystem(in);
                HWPFDocument hwpf = new HWPFDocument(pfs);
                Range range = hwpf.getRange();
                TableIterator it = new TableIterator(range);
                while (it.hasNext()) {

                    Table tb = (Table) it.next();


                    for (int i = 0; i < tb.numRows(); i++) {
                        TableRow tr = tb.getRow(i);
                        //迭代列，默认从0开始
                        for (int j = 0; j < tr.numCells(); j++) {
                            TableCell td = tr.getCell(j);//取得单元格

                            //取得单元格的内容
                            for (int k = 0; k < td.numParagraphs(); k++) {
                                Paragraph para = td.getParagraph(k);
                                String s = para.text();
                                //去除后面的特殊符号
                                if (null != s && !"".equals(s)) {
                                    s = s.substring(0, s.length() - 1);
                                }
                                System.out.println(s);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void docWordtest(String filePaht) {
        try {
            FileInputStream in = new FileInputStream(filePaht);//载入文档
            POIFSFileSystem pfs = new POIFSFileSystem(in);
            HWPFDocument hwpf = new HWPFDocument(pfs);
            Range range = hwpf.getRange();//得到文档的读取范围
            TableIterator it = new TableIterator(range);
            //迭代文档中的表格
            while (it.hasNext()) {
                Table tb = (Table) it.next();
                //迭代行，默认从0开始
                for (int i = 0; i < tb.numRows(); i++) {
                    TableRow tr = tb.getRow(i);
                    //迭代列，默认从0开始
                    for (int j = 0; j < tr.numCells(); j++) {
                        TableCell td = tr.getCell(j);//取得单元格
                        //取得单元格的内容
                        for (int k = 0; k < td.numParagraphs(); k++) {
                            Paragraph para = td.getParagraph(k);
                            String s = para.text();
                            System.out.println(s);
                        } //end for
                    }   //end for
                }   //end for
            } //end while
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//end method

    public static void readAndWriterTest4(String filePath) throws IOException {
        File file = new File(filePath);
        String str = "";
        try {
            FileInputStream fis = new FileInputStream(file);
            XWPFDocument xdoc = new XWPFDocument(fis);
            XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
            String doc1 = extractor.getText();
            System.out.println(doc1);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        WordUtil.testWord("/Users/hby/开发文档/简历库/三大网站/前程无忧/51job_杨建新(721318252).docx");
        WordUtil.testWord("/Users/hby/开发文档/简历库/三大网站/猎聘/高翔919128daJ5650ba0d-SeniorDeveloper-Radio-南京爱立信熊猫通信有限公司-男-35岁-本科-12年以上工作经验-猎聘网简历.docx");
        WordUtil.testWord("/Users/hby/开发文档/简历库/三大网站/智联招聘/智联招聘_魏旭东_中文_20180717_1531806738196.docx");
    }
}
