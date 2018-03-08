package com.jxl.jcrawler.hupuMonitor;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.jxl.jcrawler.config.SpringHolder;
import com.jxl.jcrawler.model.hupupost.Count;
import com.jxl.jcrawler.model.hupupost.HupuDateRepository;
import com.jxl.jcrawler.model.hupupost.HupuPost;
import com.jxl.jcrawler.model.hupupostnew.HupuNewData;
import com.jxl.jcrawler.model.hupupostnew.HupuNewDateRepository;

public class HupuDataUpdate {
	private static HupuNewDateRepository newDataRepository = SpringHolder.getBean(HupuNewDateRepository.class);
	private static Logger LOGGER = LoggerFactory.getLogger(HupuDataUpdate.class);
	private static HupuDateRepository dataRepository = SpringHolder.getBean(HupuDateRepository.class);

	public static void updateData() {
		List<HupuPost> list = dataRepository.findAll();
		for (HupuPost hupuPost : list) {
			HupuNewData newData = new HupuNewData();
			newData.setAuthorName(hupuPost.getAuthorName());
			newData.setPostTitle(hupuPost.getPostTitle());
			newData.setPlate(hupuPost.getPlate());
			newData.setStartDate(hupuPost.getStartDate());
			newData.setUrl(hupuPost.getUrl());
			List<Count> counts = hupuPost.getCounts();
			List<String> replyCounts = new ArrayList<>();
			List<String> scanCounts = new ArrayList<>();
			List<String> times = new ArrayList<>();
			for (Count count : counts) {
				replyCounts.add(count.getReplyCount());
				scanCounts.add(count.getScanCount());
				times.add(count.getTime());
			}
			newData.setReplyCounts(replyCounts);
			newData.setScanCounts(scanCounts);
			newData.setMonitorTimes(times);
			newDataRepository.save(newData);
			LOGGER.info("保存："+newData.getPostTitle()+"  成功！！！");
		}
	}

	public static void saveAsExcel() {
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		XSSFSheet sheet = workbook.createSheet("虎扑数据详情表");
		
		//创建第一行
		XSSFRow row = sheet.createRow(0);
		//第一行的第一个单元格
		XSSFCell cell = row.createCell(0);
		cell.setCellValue("postTitle");
		cell = row.createCell(1);
		cell.setCellValue("authorName");
		cell = row.createCell(2);
		cell.setCellValue("startDate");
		cell = row.createCell(3);
		cell.setCellValue("url");
		cell = row.createCell(4);
		cell.setCellValue("monitorTimes");
		cell = row.createCell(5);
		cell.setCellValue("replyCounts");
		cell = row.createCell(6);
		cell.setCellValue("scanCounts");
		cell = row.createCell(7);
		cell.setCellValue("enddate");
		cell = row.createCell(8);
		cell.setCellValue("maxReply");
		cell = row.createCell(9);
		cell.setCellValue("maxScan");
		
		

		XSSFSheet sheet1 = workbook.createSheet("虎扑数据表");
		//创建第一行
		XSSFRow row1 = sheet1.createRow(0);
		XSSFCell cell1 = row1.createCell(0);
		cell1.setCellValue("postTitle");
		cell1 = row1.createCell(1);
		cell1.setCellValue("authorName");
		cell1 = row1.createCell(2);
		cell1.setCellValue("startDate");
		cell1 = row1.createCell(3);
		cell1.setCellValue("url");
		cell1 = row1.createCell(4);
		cell1.setCellValue("enddate");
		cell1 = row1.createCell(5);
		cell1.setCellValue("maxReply");
		cell1 = row1.createCell(6);
		cell1.setCellValue("maxScan");
		cell1 = row1.createCell(7);
		cell1.setCellValue("tenMinScan");
		
		
		String postTitle ="";
		String authorName ="";
		String startDate ="";
		String url ="";
		String enddate ="";
		String maxReply ="";
		String maxScan ="";
		List<HupuPost> list = dataRepository.findAll();
		int a = 0;
		for (int i = 0; i < list.size(); i++) {
			HupuPost hupu = list.get(i);
			postTitle = hupu.getPostTitle();
			authorName = hupu.getAuthorName();
			startDate = hupu.getStartDate();
			url = hupu.getUrl();
			List<Count> counts = hupu.getCounts();
			//判断10分钟内浏览量
			String tenMinScan = "";
			Count count3 = null;
			if (counts.size()>=3) {
				count3 = counts.get(2);
				tenMinScan = count3.getScanCount();
			}else {
				count3 = counts.get(counts.size()-1);
				tenMinScan = count3.getScanCount();
			}
			
			Count count2 = counts.get(counts.size()-1);
			
			String maxRepilyCount = count2.getReplyCount().replaceAll(" ", "");
			enddate = count2.getTime();
			maxReply = maxRepilyCount;
			maxScan = count2.getScanCount();
			if (Integer.parseInt(maxRepilyCount)>=0) {
				row1 = sheet1.createRow(i+1);
				row1.createCell(0).setCellValue(postTitle);
				row1.createCell(1).setCellValue(authorName);
				row1.createCell(2).setCellValue(startDate);
				row1.createCell(3).setCellValue(url);
				row1.createCell(4).setCellValue(enddate);
				row1.createCell(5).setCellValue(maxReply);
				row1.createCell(6).setCellValue(maxScan);
				row1.createCell(7).setCellValue(tenMinScan);
				for (int j = 0; j < counts.size(); j++) {
					Count count = counts.get(j);
					row = sheet.createRow(a+1);
					row.createCell(0).setCellValue(postTitle);
					row.createCell(1).setCellValue(authorName);
					row.createCell(2).setCellValue(startDate);
					row.createCell(3).setCellValue(url);
					row.createCell(4).setCellValue(count.getTime());
					row.createCell(5).setCellValue(count.getReplyCount());
					row.createCell(6).setCellValue(count.getScanCount());
					row.createCell(7).setCellValue(enddate);
					row.createCell(8).setCellValue(maxReply);
					row.createCell(9).setCellValue(maxScan);
					LOGGER.info("写入第"+j+"行成功；");
					postTitle ="";
					authorName ="";
					startDate ="";
					url ="";
					enddate ="";
					maxReply ="";
					maxScan ="";
					a++;
				}
				LOGGER.info("写入："+postTitle+"成功！！！！");
			}
		}
		
		try {
			FileOutputStream fout = new FileOutputStream("/Users/zhenhuawang/Documents/hupuData_0308.xlsx");
			workbook.write(fout);
			fout.close();
			LOGGER.info("文件写入成功！！！！！！！！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
