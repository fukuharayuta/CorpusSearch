import java.io.File;
import java.io.IOException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxSample extends DefaultHandler {
    String tag = "null";
    Attributes docno;
    int start = 0;
    String doc_id;
    String[] searchword = {"湖広熟すれば天下足る", "湖広（湖北・湖南）熟すれば天下足る",
     "アイルランド", "トウモロコシ", "農業革命", "穀物法廃止","穀物法", "三圃制", "アンデス", "占城稲"};
//    String[] searchword = {"グーツヘルシャフト","農場領主制","一条鞭法","価格革命","綿織物","日本銀",
//            "東インド会社","ポトシ","アントウェルペン","アントワープ"};
    String sw = new String();
    String tag_exatraction;

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        SAXParserFactory spfactory = SAXParserFactory.newInstance();
        SAXParser parser = spfactory.newSAXParser();
        parser.parse(new File("res/xml/version3/correct_data(tokyo_2007)_v3.xml"), new SaxSample());
    }

    //start Document
    public void startDocument() {
        System.out.println("ドキュメント開始");
    }

    //start Element
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        tag = qName;
        docno = attributes;
        //System.out.println(docno);
        if (tag.equals("extraction_text")) {
            //System.out.println("要素開始:" + qName_a);
        } else if (tag.equals("text")) {
        } else if (tag.equals("doc")) {

        }

    }

    //Text
    public void characters(char[] ch, int offset, int length) {
        String str = new String(ch, offset, length);
        str.trim();
        String[] sentence = str.split("。", -1);
        int flag = 0;

        for (int j = 0; j < searchword.length; j++) {

            sw = searchword[6];

            if (tag.equals("doc")) {
                try {
                    if (docno.getQName(0).equals("docno")) {
                        doc_id = docno.getValue(0);
                    }
                } catch (NullPointerException e) {
                }

            } else if (tag.equals("extraction_text")) {
                tag_exatraction = tag;
                if (str.equals("")) {
                } else {
                    flag = str.indexOf(searchword[6]);
                    if (flag != -1) {
                        //System.out.println("searchword:" + searchword[j]);
                        System.out.println("searchword:" + sw);
                        System.out.println("docno:" + doc_id);
                        System.out.println("解答：" + str.trim());
                    }
                }
            }
            if (tag.equals("text")) {
                if (str.equals("")) {
                } else {
                    for (int i = 0; i < sentence.length; i++) {
                        flag = sentence[i].indexOf(searchword[6]);
                        if (flag != -1) {
                            //System.out.println("[text]");
                            //System.out.println("searchword:" + sw);
                            //System.out.println("docno:" + doc_id);
                            System.out.println("text:" + sentence[i].trim());
                        }
                    }
                }
            }
        }
    }

    //End Element
    public void endElement(String uri, String localName, String qName) {
        //System.out.println("要素終了:" + qName);
    }

    //End of Document
    public void endDocument() {
        System.out.println("ドキュメント終了");
    }

}
