package com.code.dojo.demo.shitcode01.service.pojo;

import java.util.Date;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Builder
public class BindInfoPojo {
    public BindInfoPojo(Date createDate, Date lastUpdateDate, Integer brandCode, String vin, Long userId) {
        super();
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.brandCode = brandCode;
        this.vin = vin;
        this.userId = userId;
    }

    public BindInfoPojo() {
        super();
    }

    private Date createDate;
    private Date lastUpdateDate;
    private Integer brandCode;
    private String vin;
    private Long userId;

}
