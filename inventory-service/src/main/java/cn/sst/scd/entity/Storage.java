package cn.sst.scd.entity;

import lombok.Data;

/**
 * 库存
 *
 * @param
 * @author shengtengsun
 * @date 2020/10/13 2:05 下午
 * @return
 **/
@Data
public class Storage {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column storage.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column storage.item_id
     *
     * @mbg.generated
     */
    private Long itemId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column storage.count
     *
     * @mbg.generated
     */
    private Long count;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column storage.batch_num
     *
     * @mbg.generated
     */
    private String batchNum;
}