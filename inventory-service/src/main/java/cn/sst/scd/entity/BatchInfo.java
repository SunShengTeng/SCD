package cn.sst.scd.entity;

public class BatchInfo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column batch_info.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column batch_info.batch_number
     *
     * @mbg.generated
     */
    private String batchNumber;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column batch_info.id
     *
     * @return the value of batch_info.id
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column batch_info.id
     *
     * @param id the value for batch_info.id
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column batch_info.batch_number
     *
     * @return the value of batch_info.batch_number
     * @mbg.generated
     */
    public String getBatchNumber() {
        return batchNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column batch_info.batch_number
     *
     * @param batchNumber the value for batch_info.batch_number
     * @mbg.generated
     */
    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber == null ? null : batchNumber.trim();
    }
}