package util;

import org.junit.Test;

import com.jxl.jcrawler.plugin.kpu.MenuCrawler;
import com.jxl.jcrawler.util.csv.CsvUtil;

public class TestCsvUtil {
	@Test
	public void test() {
		String fileStr = "C:/Users/wangzhenhua/git/big-data-crawler/target/classes/csv/wilayah.csv";
		System.out.println(fileStr);
		CsvUtil csv;
		try {
			csv = new CsvUtil(fileStr);
			csv.readcsv();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMenuCrawler() {
		MenuCrawler.loadWilayah();
	}
}
