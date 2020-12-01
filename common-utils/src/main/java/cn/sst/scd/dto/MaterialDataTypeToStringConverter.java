package cn.sst.scd.dto;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

/**
 * @author shengtengsun
 * @Description 物料-滚动要货列表DataType类型转换器
 * @Date 2020/11/19 下午5:18
 * @Version 1.1.0
 **/
public class MaterialDataTypeToStringConverter implements Converter {
    @Override
    public Class supportJavaTypeKey() {
        return Integer.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return null;
    }

    @Override
    public Object convertToJavaData(CellData cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return null;
    }

    @Override
    public CellData convertToExcelData(Object value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        Integer dataType = Integer.valueOf(String.valueOf(value));
        CellData<String> cellData = new CellData<>(CellDataTypeEnum.STRING);
        if (dataType.equals(1)) {
            cellData.setStringValue("要货计划");
            return cellData;
        }
        cellData.setStringValue("供应链承诺");
        return cellData;
    }
}
