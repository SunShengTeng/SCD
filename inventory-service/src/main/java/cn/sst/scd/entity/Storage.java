package cn.sst.scd.entity;

/**
 * 库存
 *
 * @param
 * @author shengtengsun
 * @date 2020/10/13 2:05 下午
 * @return
 **/
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
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column storage.id
     *
     * @return the value of storage.id
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column storage.id
     *
     * @param id the value for storage.id
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column storage.item_id
     *
     * @return the value of storage.item_id
     * @mbg.generated
     */
    public Long getItemId() {
        return itemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column storage.item_id
     *
     * @param itemId the value for storage.item_id
     * @mbg.generated
     */
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column storage.count
     *
     * @return the value of storage.count
     * @mbg.generated
     */
    public Long getCount() {
        return count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column storage.count
     *
     * @param count the value for storage.count
     * @mbg.generated
     */
    public void setCount(Long count) {
        this.count = count;
    }
}