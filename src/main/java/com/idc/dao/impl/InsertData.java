package com.idc.dao.impl;

import java.io.IOException;

import com.idc.domain.HTML;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class InsertData {

    public void getInfo(String key_word, String count) {
        HBaseDaoImpl hBaseDao=new HBaseDaoImpl();
        System.out.println(key_word + " " + count);
        String url = "https://www.baidu.com/s?wd=" + key_word + "&pn=" + count;
        try {
            Document document = Jsoup.connect(url).header("Connection", "Keep-Alive")
                    .header("Accept", "text/html, application/xhtml+xml, */*")
                    .header("Accept-Language", "en-US,en;q=0.8,zh-Hans-CN;q=0.5,zh-Hans;q=0.3")
                    .header("Accept-Encoding", "gzip, deflate")
                    .header("User-Agent", "Mozilla/6.1 (Windows NT 6.3; WOW64; Trident/7.0; rv:11.0) like Gecko").get();
            Elements divs = document.select("div.result.c-container");
            int i = 0;
            if (count == "0")
                i++;
            for (; i < divs.size(); i++) {
                String new_url = divs.get(i).select("h3").select("a").attr("href");
//                String new_url = divs.get(i).select("div.f13").select("a").text();

                String title = divs.get(i).select("h3").select("a").text();

                String date = divs.get(i).select("div.c-abstract").select("span").text();

                // String description = new_doc.select("div.c-abstract").first().text();
                try {
                    Document new_doc = Jsoup.connect(new_url).get();
                    //去除html的标签，获得文本
                    String content = getTextFromHtml(new_doc.toString());

                    HTML html = new HTML(title, " ", date, content, new_url);
                    System.out.println("结果："+ html.getUrl()+ "-------"+html.getContent());
//                    hBaseDao.insertData(html); //插入到hbase中
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 去除html代码中含有的标签
     * @param htmlStr
     * @return
     */
    public static String delHtmlTags(String htmlStr) {
        //定义script的正则表达式，去除js可以防止注入
        String scriptRegex="<script[^>]*?>[\\s\\S]*?<\\/script>";
        //定义style的正则表达式，去除style样式，防止css代码过多时只截取到css样式代码
        String styleRegex="<style[^>]*?>[\\s\\S]*?<\\/style>";
        //定义HTML标签的正则表达式，去除标签，只提取文字内容
        String htmlRegex="<[^>]+>";
        //定义空格,回车,换行符,制表符
        String spaceRegex = "\\s*|\t|\r|\n|&nbsp;|&gt;";

        // 过滤script标签
        htmlStr = htmlStr.replaceAll(scriptRegex, "");
        // 过滤style标签
        htmlStr = htmlStr.replaceAll(styleRegex, "");
        // 过滤html标签
        htmlStr = htmlStr.replaceAll(htmlRegex, "");
        // 过滤空格等
        htmlStr = htmlStr.replaceAll(spaceRegex, "");
        return htmlStr.trim(); // 返回文本字符串
    }

    /**
     * 获取HTML代码里的内容
     * @param htmlStr
     * @return
     */
    public static String getTextFromHtml(String htmlStr){
        //去除html标签
        htmlStr = delHtmlTags(htmlStr);
        //去除空格" "
        htmlStr = htmlStr.replaceAll(" ","");
        return htmlStr;
    }

    public static void main(String[] args) {
        InsertData getWeb = new InsertData();

        String[] words_zn = new String[] { "动态规划", "二叉树", "平衡二叉树", "线段树", "快速幂", "人工智能",
                "机器学习", "数据挖掘", "区块链", "移动安全", "大数据", "华中科技大学" ,"分布式系统","王恺鹏"};

//        String[] words_zn = new String[] {"武汉大学", "双十一", "智能与分布计算实验室"};

        String[] count = new String[] { "0", "10", "20", "30", "40", "50" };
        for (int i = 0; i < words_zn.length; i++)
            for (int j = 0; j < count.length; j++)
                getWeb.getInfo(words_zn[i], count[j]);
    }
}
