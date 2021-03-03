package cn.sst.scd.util;

import cn.sst.scd.dto.MaterialDataTypeToStringConverter;
import cn.sst.scd.dto.MaterialInfoDTO;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.merge.LoopMergeStrategy;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

/**
 * @author shengtengsun
 * @Description Excel工具类
 * @Date 2020/11/19 下午3:03
 * @Version 1.1.0
 **/
public class EasyExcelUtil {

    /**
     * 几天前
     **/
    private static final Integer BEFORE_DAY_NUM = 10;
    /**
     * 几周后
     **/
    private static final Integer AFTER_WEEK = 7;

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        String directoryPath = "/Users/shengtengsun/Desktop/";
        String fileName = "滚动要货";
        String extension = ".xlsx";

        ExcelWriterSheetBuilder sheetBuilder = EasyExcel.write((directoryPath + fileName + extension))
                // 这里放入动态头
                .head(head())
                .sheet("滚动要货");
        // 注册写处理器(合并单元格)
        for (int i = 0; i < 9; i++) {
            LoopMergeStrategy mergeStrategy = new LoopMergeStrategy(2, i);
            sheetBuilder.registerWriteHandler(mergeStrategy);
        }
        // 注册dataType类型转换器
        sheetBuilder.registerConverter(new MaterialDataTypeToStringConverter());

        sheetBuilder.doWrite(data());
        System.out.println("EasyExcel导出数据耗时：-> " + (System.currentTimeMillis() - startTime));
    }

    private static List<List<String>> head() {
        List<List<String>> head = new ArrayList<List<String>>();
        head.add(Arrays.asList("物料号"));
        head.add(Arrays.asList("产品名称"));
        head.add(Arrays.asList("规格型号"));
        head.add(Arrays.asList("单位"));
        head.add(Arrays.asList("总需求"));
        head.add(Arrays.asList("己备货数量"));
        head.add(Arrays.asList("CRM己发货数量"));
        head.add(Arrays.asList("SCP可要货量"));
        head.add(Arrays.asList("未承诺量"));
        head.add(Arrays.asList("分类"));

        List<String> tableTitleData = generateTableTitleData();
        for (String title : tableTitleData) {
            head.add(Arrays.asList(title));
        }

        return head;
    }

    private static List<MaterialInfoDTO> data() {
        List list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MaterialInfoDTO.MaterialInfoDTOBuilder builder = MaterialInfoDTO.builder()
                    .materialInfoId("111")
                    .partNum("物料号" + i)
                    .prodDesc("产品名称" + i)
                    .externalModel("规格型号" + i)
                    .dataType((i + 1) % 2);
            list.add(builder.build());
        }
        return list;
    }

    private static List dynamicData() {
        List list = new ArrayList<>();
        HashMap<String, String> propertiesMap = new HashMap<>(16);
        propertiesMap.put("partNum", "java.lang.String");
        propertiesMap.put("2020-11-12", "java.math.BigDecimal");
        DynamicMaterial dynamicMaterial = new DynamicMaterial(propertiesMap);
        dynamicMaterial.setValue("partNum", "物料号");
        dynamicMaterial.setValue("2020-11-12", BigDecimal.valueOf(110.11));
        list.add(dynamicMaterial);
        return list;
    }

    /**
     * 返回Map(不支持)
     *
     * @param :
     * @return java.util.List<java.util.Map>
     * @author shengtengsun
     * @date 2020/11/23 上午10:45
     **/
    private static List<Map> dataMap() {
        List<Map> list = new ArrayList<Map>();
        for (int i = 0; i < 10; i++) {
            HashMap<String, Object> materialMap = new HashMap<>();
            /*materialMap.put("物料的业务主键","1111");*/
            materialMap.put("物料号", "1111");
            materialMap.put("产品名称", "1111");
            materialMap.put("分类", 1);
            list.add(materialMap);
        }
        return list;
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
