package cn.sst.scd.util;

import cn.sst.scd.dto.Const;
import cn.sst.scd.dto.DistributePlanDTO;
import cn.sst.scd.dto.MaterialInfoDTO;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author shengtengsun
 * @Description Poi Excel工具类
 * @Date 2020/11/23 下午1:28
 * @Version 1.1.0
 **/
public class PoiExcelUtil {

    /**
     * 几天前
     **/
    private static final Integer BEFORE_DAY_NUM = 10;
    /**
     * 几周后
     **/
    private static final Integer AFTER_WEEK = 7;
    /**
     * 固定列最后一列的索引
     **/
    private static final Integer INDEX_OF_FIXED = 12;

    private final static Logger logger = LoggerFactory.getLogger(PoiExcelUtil.class);


    public static void main(String[] args) {
        exportExcel();
    }

    public static void exportExcel() {

        // 导出的excel,全文件名
        final String destFilePath = "/Users/shengtengsun/Desktop/滚动要货POI.xlsx";

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        FileOutputStream fos = null;
        SXSSFWorkbook sxssfWorkbook = null;
        try {
            // -> 从数据库中查询出要进行excel导出的数据
            List<MaterialInfoDTO> list = getData();

            /// -> excel到处逻辑
            long startTime = System.currentTimeMillis();
            // 获取SXSSFWorkbook实例
            sxssfWorkbook = new SXSSFWorkbook();
            Sheet sheet = sxssfWorkbook.createSheet("滚动要货");
            // 冻结最左边的两列、冻结最上面的一行
            // 即：滚动横向滚动条时，左边的第一、二列固定不动;滚动纵向滚动条时，上面的第一行固定不动。
            sheet.createFreezePane(2, 1);
            // 设置并获取到需要的样式
            XSSFCellStyle headerStyle = getAndSetXSSFCellStyleHeader(sxssfWorkbook);
            XSSFCellStyle hasModifyStyle = getAndSetXSSFCellStyleOne(sxssfWorkbook);
            XSSFCellStyle noHasModifyStyle = getAndSetXSSFCellStyleTwo(sxssfWorkbook);
            XSSFCellStyle dynamicColumnStyle = getCellStyleForDynamicColumn(sxssfWorkbook);

            // 2、生成表头
            final HashMap<String, Integer> indexOfDynamicColumn = generateTableTitle(sheet, headerStyle, dynamicColumnStyle);

            // 3、遍历创建行
            for (int rowNum = 1; rowNum <= list.size(); rowNum++) {
                MaterialInfoDTO materialInfoDTO = list.get(rowNum - 1);
                Row row = sheet.createRow(rowNum);
                // 固定列(头部)
                createCellOfFixed(row, materialInfoDTO, rowNum, hasModifyStyle, noHasModifyStyle, sheet, sxssfWorkbook);
                // 动态列
                List<DistributePlanDTO> availablePlan = materialInfoDTO.getTimeArr().stream().filter(distributePlanDTO -> distributePlanDTO.getPlanNum() != null && !distributePlanDTO.getPlanNum().equals(BigDecimal.ZERO)).collect(Collectors.toList());
                for (DistributePlanDTO distributePlanDTO : availablePlan) {
                    /*if (null != distributePlanDTO.getPlanNum() && !BigDecimal.ZERO.equals(distributePlanDTO.getPlanNum())) {

                    }*/
                    Integer indexOfCell = indexOfDynamicColumn.get(distributePlanDTO.getPlanTime());
                    Cell cell = row.createCell(indexOfCell);
                    cell.setCellType(CellType.NUMERIC);
                    cell.setCellValue(distributePlanDTO.getPlanNum().doubleValue());
                }
                // 固定列(尾部)
                Integer lastIndexOfDynamicCell = indexOfDynamicColumn.get("第7周-汇总") + 1;
                int index = lastIndexOfDynamicCell;
                int end = lastIndexOfDynamicCell + 6;
                for (; index < end; index++) {
                    Cell cell = row.createCell(index);
                    int i = index - lastIndexOfDynamicCell;
                    switch (i) {
                        case 0:
                            // 将来
                            cell.setCellType(CellType.NUMERIC);
                            cell.setCellValue(materialInfoDTO.getHistoryNum().doubleValue());
                            break;
                        case 1:
                            // 修改时间
                            if (null != materialInfoDTO.getModifyDate()) {
                                cell.setCellType(CellType.STRING);
                                cell.setCellValue(dateFormat.format(materialInfoDTO.getModifyDate()));
                            }
                            break;
                        case 2:
                            // 修改人
                            cell.setCellType(CellType.STRING);
                            cell.setCellValue(materialInfoDTO.getModifier());
                            break;
                        case 3:
                            // 提交时间
                            if (null != materialInfoDTO.getSubmitTime()) {
                                cell.setCellType(CellType.STRING);
                                cell.setCellValue(dateFormat.format(materialInfoDTO.getSubmitTime()));
                            }
                            break;
                        case 4:
                            // 提交人
                            cell.setCellType(CellType.STRING);
                            cell.setCellValue(materialInfoDTO.getSubmitUser());
                            break;
                        default:
                            // 供应链回复时间
                            cell.setCellType(CellType.STRING);
                            cell.setCellValue(materialInfoDTO.getAnswerTime());
                            break;
                    }
                }
            }
            // 4、合并单元格
            int TheFirstOddNumber = 1;
            while (TheFirstOddNumber <= list.size()) {
                for (int columnIndex = 0; columnIndex < 10; columnIndex++) {
                    CellRangeAddress region = new CellRangeAddress(TheFirstOddNumber, TheFirstOddNumber + 1, columnIndex, columnIndex);
                    sheet.addMergedRegion(region);
                }
                TheFirstOddNumber = TheFirstOddNumber + 2;
            }
            // 在后面设置sheet
            setSheet(sheet);

            fos = new FileOutputStream(destFilePath);
            sxssfWorkbook.write(fos);
            long endTime = System.currentTimeMillis();
            logger.info("数据全部导出至excel总耗时:{} 毫秒!", endTime - startTime, list.size());
        } catch (Exception e) {
            logger.error("发生异常咯！", e);
        } finally {
            try {
                if (sxssfWorkbook != null) {
                    // dispose of temporary files backing this workbook on disk -> 处
                    //     理SXSSFWorkbook导出excel时，产生的临时文件
                    sxssfWorkbook.dispose();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private static XSSFCellStyle getCellStyleForDynamicColumn(SXSSFWorkbook sxssfWorkbook) {
        XSSFCellStyle xssfCellStyle = (XSSFCellStyle) sxssfWorkbook.createCellStyle();
        // 边框
        xssfCellStyle.setBorderBottom(BorderStyle.THIN);
        xssfCellStyle.setBorderRight(BorderStyle.THIN);
        xssfCellStyle.setBorderTop(BorderStyle.THIN);
        xssfCellStyle.setBorderLeft(BorderStyle.THIN);
        xssfCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        xssfCellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        xssfCellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        xssfCellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        // 是否自动换行
        xssfCellStyle.setWrapText(false);
        // 水平居中
        xssfCellStyle.setAlignment(HorizontalAlignment.CENTER);
        // 垂直居中
        xssfCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return xssfCellStyle;
    }

    private static void createCellOfFixed(Row row, MaterialInfoDTO materialInfoDTO, int rowNum, XSSFCellStyle hasModifyStyle, XSSFCellStyle noHasModifyStyle, Sheet sheet, SXSSFWorkbook workbook) {
        for (int cellNum = 0; cellNum < INDEX_OF_FIXED; cellNum++) {
            Cell cell = row.createCell(cellNum);
            // 根据行数,设置该行内的单元格样式
            if (cellNum == 0) {
                if (rowNum % 2 == 1) {
                    cell.setCellValue((rowNum + 1) / 2);
                }
            } else if (cellNum == 1) {
                // 物料号
                // 根据hasModify设置样式,并添加批注
                if (Const.MATERIAL_HAS_MODIFY.equals(materialInfoDTO.getHasModify())) {
                    cell.setCellStyle(hasModifyStyle);
                    //获取批注对象
                    //创建绘图对象
                    Drawing<?> patriarch = sheet.createDrawingPatriarch();
                    //以下代码是根据Cell进行循环调用
                    CreationHelper factory = workbook.getCreationHelper();
                    ClientAnchor anchor = factory.createClientAnchor();
                    anchor.setCol1(cell.getColumnIndex());
                    anchor.setCol2(cell.getColumnIndex() + 1);
                    anchor.setRow1(cell.getRowIndex());
                    anchor.setRow2(cell.getRowIndex() + 1);
                    Comment comment = patriarch.createCellComment(anchor);
                    RichTextString str = factory.createRichTextString("此物料有修改，但是未发布");
                    comment.setString(str);
                    //添加作者,选中B5单元格,看状态栏
                    comment.setAuthor("大华技术");
                    //将批注添加到单元格对象中
                    cell.setCellComment(comment);
                } else {
                    cell.setCellStyle(noHasModifyStyle);
                }
                cell.setCellType(CellType.STRING);
                cell.setCellValue(materialInfoDTO.getPartNum());
            } else if (cellNum == 2) {
                // 产品名称
                cell.setCellType(CellType.STRING);
                cell.setCellValue(materialInfoDTO.getProdDesc());
            } else if (cellNum == 3) {
                // 规格型号
                cell.setCellType(CellType.STRING);
                cell.setCellValue(materialInfoDTO.getExternalModel());
            } else if (cellNum == 4) {
                // 单位
                cell.setCellType(CellType.STRING);
                cell.setCellValue(materialInfoDTO.getProductUnit());
            } else if (cellNum == 5) {
                // 总需求
                cell.setCellType(CellType.NUMERIC);
                cell.setCellValue(materialInfoDTO.getQty().doubleValue());
            } else if (cellNum == 6) {
                // 己备货数量
                cell.setCellType(CellType.NUMERIC);
                cell.setCellValue(materialInfoDTO.getCrmOrderedQuantity().doubleValue());
            } else if (cellNum == 7) {
                // CRM己发货数量
                cell.setCellType(CellType.NUMERIC);
                cell.setCellValue(materialInfoDTO.getEbsShippedQty().doubleValue());
            } else if (cellNum == 8) {
                // SCP可要货量
                cell.setCellType(CellType.NUMERIC);
                cell.setCellValue(materialInfoDTO.getCanRequiredNum().doubleValue());
            } else if (cellNum == 9) {
                // 未承诺量
                cell.setCellType(CellType.NUMERIC);
                cell.setCellValue(materialInfoDTO.getNoPromiseNum().doubleValue());
            } else if (cellNum == 10) {
                // 分类
                cell.setCellType(CellType.STRING);
                if (Const.MATERIAL_DATATYPE_REQUIRE.equals(materialInfoDTO.getDataType())) {
                    cell.setCellValue("要货数量");
                } else {
                    cell.setCellValue("供应链承诺");
                }
            } else {
                // 历史
                cell.setCellType(CellType.NUMERIC);
                cell.setCellValue(materialInfoDTO.getHistoryNum().doubleValue());
            }
        }
    }

    /**
     * 生成表头
     *
     * @param sheet               :
     * @param columnStyleOfFixed:
     * @param dynamicColumnStyle  :
     * @return void
     * @author shengtengsun
     * @date 2020/11/23 下午2:32
     **/
    private static HashMap<String, Integer> generateTableTitle(Sheet sheet, XSSFCellStyle columnStyleOfFixed, XSSFCellStyle dynamicColumnStyle) {
        // 创建第一行,作为header表头
        Row headerRow = sheet.createRow(0);
        headerRow.setHeight((short) 1000);
        // 开始固定部分
        List<String> firstHeaders = Arrays.asList("序号", "物料号", "产品名称", "规格型号", "单位", "总需求", "己备货数量", "CRM己发货数量", "SCP可要货量", "未承诺量", "分类", "历史");
        for (int cellNum = 0; cellNum < INDEX_OF_FIXED; cellNum++) {
            Cell cell = headerRow.createCell(cellNum);
            cell.setCellStyle(columnStyleOfFixed);
            cell.setCellValue(firstHeaders.get(cellNum));
        }
        // 中间动态部分
        List<String> dataStrArr = generateTableTitleData();
        int startIndex = INDEX_OF_FIXED;
        for (String dataStr : dataStrArr) {
            Cell cell = headerRow.createCell(startIndex);
            cell.setCellValue(dataStr);
            cell.setCellStyle(dynamicColumnStyle);
            startIndex++;
        }
        // 尾部
        List<java.lang.String> lastHeaders = Arrays.asList("将来", "修改时间", "修改人", "提交时间", "提交人", "供应链回复时间");
        int index = startIndex;
        int end = startIndex + 6;
        for (; index < end; index++) {
            Cell cell = headerRow.createCell(index);
            cell.setCellValue(lastHeaders.get(index % startIndex));
            cell.setCellStyle(columnStyleOfFixed);
        }
        // 生成动态列名索引May
        HashMap<String, Integer> dynamicIndex = new HashMap<>(101);
        int startOfDynamicColumn = 12;
        for (int i = 0; i < dataStrArr.size(); i++) {
            dynamicIndex.put(dataStrArr.get(i), startOfDynamicColumn);
            startOfDynamicColumn++;
        }
        return dynamicIndex;
    }

    public static List<MaterialInfoDTO> getData() {
        ClassPathResource resource = new ClassPathResource("json/materialInfos.json");
        File file = null;
        try {
            file = resource.getFile();
            String jsonString = FileUtils.readFileToString(file, "UTF-8");
            return JSONArray.parseArray(jsonString, MaterialInfoDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 设置sheet
     */
    private static void setSheet(Sheet sheet) {
        // 设置各列宽度(单位为:字符宽度的1/256)
        sheet.setDefaultColumnWidth(17);

        sheet.setColumnWidth(0, 5 * 256);
        sheet.setColumnWidth(1, 15 * 256);
        sheet.setColumnWidth(2, 50 * 256);
        sheet.setColumnWidth(3, 30 * 256);
        sheet.setColumnWidth(4, 7 * 256);
        sheet.setColumnWidth(5, 8 * 256);
        sheet.setColumnWidth(6, 18 * 256);
        sheet.setColumnWidth(7, 18 * 256);
        sheet.setColumnWidth(8, 18 * 256);
        sheet.setColumnWidth(9, 10 * 256);
        sheet.setColumnWidth(10, 10 * 256);
        sheet.setColumnWidth(11, 8 * 256);
    }

    /**
     * 获取并设置header样式
     */
    private static XSSFCellStyle getAndSetXSSFCellStyleHeader(SXSSFWorkbook sxssfWorkbook) {
        // Header字体
        Font font = sxssfWorkbook.createFont();
        // 字体大小
        font.setFontHeightInPoints((short) 12);
        // 字体加粗
        font.setBold(true);

        XSSFCellStyle xssfCellStyle = (XSSFCellStyle) sxssfWorkbook.createCellStyle();
        // 将字体应用到样式上面
        xssfCellStyle.setFont(font);
        // 背景色
        xssfCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        xssfCellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(217, 217, 217)));

        // 是否自动换行
        xssfCellStyle.setWrapText(false);
        // 水平居中
        xssfCellStyle.setAlignment(HorizontalAlignment.CENTER);
        // 垂直居中
        xssfCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        // 边框
        xssfCellStyle.setBorderBottom(BorderStyle.THIN);
        xssfCellStyle.setBorderRight(BorderStyle.THIN);
        xssfCellStyle.setBorderTop(BorderStyle.THIN);
        xssfCellStyle.setBorderLeft(BorderStyle.THIN);
        xssfCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        xssfCellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        xssfCellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        xssfCellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());

        return xssfCellStyle;
    }

    /**
     * 获取并设置物料修改样式
     */
    private static XSSFCellStyle getAndSetXSSFCellStyleOne(SXSSFWorkbook sxssfWorkbook) {
        XSSFCellStyle xssfCellStyle = (XSSFCellStyle) sxssfWorkbook.createCellStyle();
        XSSFDataFormat format = (XSSFDataFormat) sxssfWorkbook.createDataFormat();
        // 是否自动换行
        xssfCellStyle.setWrapText(false);
        // 水平居中
        xssfCellStyle.setAlignment(HorizontalAlignment.CENTER);
        // 垂直居中
        xssfCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 前景颜色
        xssfCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        xssfCellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(217, 73, 93)));
        // 边框
        xssfCellStyle.setBorderBottom(BorderStyle.THIN);
        xssfCellStyle.setBorderRight(BorderStyle.THIN);
        xssfCellStyle.setBorderTop(BorderStyle.THIN);
        xssfCellStyle.setBorderLeft(BorderStyle.THIN);
        xssfCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        xssfCellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        xssfCellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        xssfCellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        // 防止数字过长,excel导出后,显示为科学计数法,如:防止8615192053888被显示为8.61519E+12
        xssfCellStyle.setDataFormat(format.getFormat("0"));
        return xssfCellStyle;
    }

    /**
     * 获取并设置偶数行样式
     */
    private static XSSFCellStyle getAndSetXSSFCellStyleTwo(SXSSFWorkbook sxssfWorkbook) {
        XSSFCellStyle xssfCellStyle = (XSSFCellStyle) sxssfWorkbook.createCellStyle();
        XSSFDataFormat format = (XSSFDataFormat) sxssfWorkbook.createDataFormat();
        // 是否自动换行
        xssfCellStyle.setWrapText(false);
        // 水平居中
        xssfCellStyle.setAlignment(HorizontalAlignment.CENTER);
        // 边框
        xssfCellStyle.setBorderBottom(BorderStyle.THIN);
        xssfCellStyle.setBorderRight(BorderStyle.THIN);
        xssfCellStyle.setBorderTop(BorderStyle.THIN);
        xssfCellStyle.setBorderLeft(BorderStyle.THIN);
        xssfCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        xssfCellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        xssfCellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        xssfCellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        // 垂直居中
        xssfCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 防止数字过长,excel导出后,显示为科学计数法,如:防止8615192053888被显示为8.61519E+12
        xssfCellStyle.setDataFormat(format.getFormat("0"));
        return xssfCellStyle;
    }

    public static List<String> generateTableTitleData() {
        LocalDate now = LocalDate.now();

        ArrayList<String> tableTitle = new ArrayList<>();
        // 今天前10天 + 本周
        Date start = DateUtil.getBeforeDay(now, BEFORE_DAY_NUM);
        Date end = DateUtil.getEndDayOfWeek(now);
        tableTitle.addAll(DateUtil.getAscDateStrList(start, end));
        // 获取后7周的数据
        int i = 1;
        while (i <= AFTER_WEEK) {
            // 第一周汇总
            List<String> firstWeekList = DateUtil.getAscDateStrList(
                    DateUtil.getFirstDayOfAfterWeek(now, i),
                    DateUtil.getLastDayOfAfterWeek(now, i));
            tableTitle.addAll(firstWeekList);
            tableTitle.add("第" + i + "周-汇总");
            i++;
        }
        return tableTitle;
    }
}
