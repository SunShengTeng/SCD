package cn.sst.scd.mapper;

import cn.sst.scd.entity.ItemInfo;
import cn.sst.scd.entity.ItemInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_info
     *
     * @mbg.generated
     */
    long countByExample(ItemInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_info
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_info
     *
     * @mbg.generated
     */
    int insert(ItemInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_info
     *
     * @mbg.generated
     */
    int insertSelective(ItemInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_info
     *
     * @mbg.generated
     */
    List<ItemInfo> selectByExample(ItemInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_info
     *
     * @mbg.generated
     */
    ItemInfo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_info
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ItemInfo record, @Param("example") ItemInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_info
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") ItemInfo record, @Param("example") ItemInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ItemInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ItemInfo record);
}