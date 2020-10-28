package cn.sst.scd.entity;

import java.util.Date;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/10/23 5:15 下午
 * @Version 1.1.0
 **/
/*@Data
@Entity
@Table(name = "item_detail")
// 表注释
@org.hibernate.annotations.Table(appliesTo = "item_detail", comment = "商品详情")*/
public class ItemDetail {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)*/
    private Long id;

    /*@Column(name = "item_id", columnDefinition = "VARCHAR(25) NULL COMMENT '商品ID'")*/
    private String itemId;

    /*@Column(name = "describe", columnDefinition = "VARCHAR(25) NULL COMMENT '商品描述'")*/
    private String describe;

    /*@Column(name = "create_time", columnDefinition = "DATETIME NULL COMMENT '创建时间'")*/
    private Date createTime;
}
