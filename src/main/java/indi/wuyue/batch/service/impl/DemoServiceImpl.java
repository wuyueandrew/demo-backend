package indi.wuyue.batch.service.impl;

import indi.wuyue.batch.bean.Person;
import indi.wuyue.batch.service.DemoService;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;

@Service
public class DemoServiceImpl implements DemoService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public void testExport(HttpServletResponse response) {
        try(SXSSFWorkbook workbook = new SXSSFWorkbook(1000);
            ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            SXSSFSheet sheet = workbook.createSheet();

            sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 11));
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 13, 16));


            SXSSFRow groupRow = sheet.createRow(0);
            groupRow.createCell(0).setCellValue("团队");
            groupRow.createCell(1).setCellValue("华东一部");
            groupRow.createCell(12).setCellValue("华东二部");
            groupRow.createCell(13).setCellValue("金融行业");
            groupRow.createCell(17).setCellValue("总计");

            SXSSFRow tradeRow = sheet.createRow(1);
            tradeRow.createCell(0).setCellValue("行业");
            tradeRow.createCell(1).setCellValue("JF");
            tradeRow.createCell(2).setCellValue("新闻资讯");
            tradeRow.createCell(3).setCellValue("教育");
            tradeRow.createCell(4).setCellValue("展会家居");
            tradeRow.createCell(5).setCellValue("彩票");
            tradeRow.createCell(6).setCellValue("竞拍");
            tradeRow.createCell(7).setCellValue("电商平台");
            tradeRow.createCell(8).setCellValue("应用分发");
            tradeRow.createCell(9).setCellValue("游戏");
            tradeRow.createCell(10).setCellValue("会籍卡");
            tradeRow.createCell(11).setCellValue("其他服务");
            tradeRow.createCell(12).setCellValue("货到付款");
            tradeRow.createCell(13).setCellValue("信用卡");
            tradeRow.createCell(14).setCellValue("贷款");
            tradeRow.createCell(15).setCellValue("理财");
            tradeRow.createCell(16).setCellValue("保险");

            SXSSFRow ownerRow = sheet.createRow(2);
            ownerRow.createCell(0).setCellValue("负责人");
            ownerRow.createCell(1).setCellValue("方愉涵");
            ownerRow.createCell(2).setCellValue("胡逸童");
            ownerRow.createCell(3).setCellValue("盼盼");
            ownerRow.createCell(4).setCellValue("郑涵文");
            ownerRow.createCell(5).setCellValue("刘寿伟");
            ownerRow.createCell(6).setCellValue("杨晓");
            ownerRow.createCell(7).setCellValue("杨晓");
            ownerRow.createCell(8).setCellValue("董文龙");
            ownerRow.createCell(9).setCellValue("郑涵文");
            ownerRow.createCell(10).setCellValue("盼盼");
            ownerRow.createCell(11).setCellValue("毛雅晴");
            ownerRow.createCell(12).setCellValue("赵晨");
            ownerRow.createCell(13).setCellValue("金梦");
            ownerRow.createCell(14).setCellValue("魏鑫");
            ownerRow.createCell(15).setCellValue("王灿锋");
            ownerRow.createCell(16).setCellValue("金梦");

            SXSSFRow targetRow = sheet.createRow(3);
            targetRow.createCell(0).setCellValue("目标");
            targetRow.createCell(1).setCellValue(50D);
            targetRow.createCell(2).setCellValue(15D);
            targetRow.createCell(3).setCellValue(12D);
            targetRow.createCell(4).setCellValue(6D);
            targetRow.createCell(5).setCellValue(5D);
            targetRow.createCell(6).setCellValue(1.5D);
            targetRow.createCell(7).setCellValue(2D);
            targetRow.createCell(8).setCellValue(10D);
            targetRow.createCell(9).setCellValue(5D);
            targetRow.createCell(10).setCellValue(3D);
            targetRow.createCell(11).setCellValue(1D);
            targetRow.createCell(12).setCellValue(48D);
            targetRow.createCell(13).setCellValue(20D);
            targetRow.createCell(14).setCellValue(10D);
            targetRow.createCell(15).setCellValue(1D);
            targetRow.createCell(16).setCellValue(15D);
            targetRow.createCell(17).setCellValue(204.5D);

            workbook.write(os);


            byte[] content = os.toByteArray();
            InputStream is = new ByteArrayInputStream(content);
            // 设置response参数，可以打开下载页面
            response.reset();

            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(("demo" + ".xlsx").getBytes(), "iso-8859-1"));
            response.setContentLength(content.length);
            ServletOutputStream outputStream = response.getOutputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            BufferedOutputStream bos = new BufferedOutputStream(outputStream);
            byte[] buff = new byte[8192];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);

            }
            bis.close();
            bos.close();
            outputStream.flush();
            outputStream.close();


        } catch (IOException e) {

        }
    }

    @Override
    public List<Person> testPeopleQuery() {
        return jdbcTemplate.query("SELECT person_id, first_name, last_name FROM people",
                (rs, row) -> new Person(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3))
        );
    }

    @Override
    public void testPeopleExport(HttpServletResponse response) {
        List<Person> people = testPeopleQuery();
        try(SXSSFWorkbook workbook = new SXSSFWorkbook(1000);
            ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            SXSSFSheet sheet = workbook.createSheet();
            SXSSFRow groupRow = sheet.createRow(0);
            groupRow.createCell(0).setCellValue("人员ID");
            groupRow.createCell(1).setCellValue("first_name");
            groupRow.createCell(2).setCellValue("last_name");
            for (int i = 0; i < people.size(); i++) {
                Person p = people.get(i);
                SXSSFRow dataRow = sheet.createRow(i + 1);
                dataRow.createCell(0).setCellValue(i);
                dataRow.createCell(1).setCellValue(p.getFirstName());
                dataRow.createCell(2).setCellValue(p.getLastName());
            }
            workbook.write(os);

            byte[] content = os.toByteArray();
            InputStream is = new ByteArrayInputStream(content);
            // 设置response参数，可以打开下载页面
            response.reset();

            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(("demo" + ".xlsx").getBytes(), "iso-8859-1"));
            response.setContentLength(content.length);
            ServletOutputStream outputStream = response.getOutputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            BufferedOutputStream bos = new BufferedOutputStream(outputStream);
            byte[] buff = new byte[8192];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);

            }
            bis.close();
            bos.close();
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {

        }
    }

}
