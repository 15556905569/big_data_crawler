package com.jxl.jcrawler.util.image;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by amosli on 20/07/2017.
 * //   sample:
 * {
 * //            "Success": true,//成功标识
 * //            "Message": "QVHH",//识别结果或错误信息
 * //            "PlatformType": 0,//识别平台类型
 * //            " PCodeId ": 1700260872,//本次识别id，用于错误反馈
 * //            "ConsumeScore": 12//本次识别所耗分值
 * //    }
 */

public class ImageResponse {

    @JsonProperty("Success")
    private Boolean Success;
    @JsonProperty("Message")
    private String Message;
    @JsonProperty("PlatformType")
    private String PlatformType;
    @JsonProperty("PCodeId")
    private String PCodeId;
    @JsonProperty("ConsumeScore")
    private Integer ConsumeScore;

    public Boolean getSuccess() {
        return Success;
    }

    public void setSuccess(Boolean success) {
        Success = success;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getPlatformType() {
        return PlatformType;
    }

    public void setPlatformType(String platformType) {
        PlatformType = platformType;
    }

    public String getPCodeId() {
        return PCodeId;
    }

    public void setPCodeId(String PCodeId) {
        this.PCodeId = PCodeId;
    }

    public Integer getConsumeScore() {
        return ConsumeScore;
    }

    public void setConsumeScore(Integer consumeScore) {
        ConsumeScore = consumeScore;
    }


    @Override
    public String toString() {
        return "ImageResponse{" +
                "Success=" + Success +
                ", Message='" + Message + '\'' +
                ", PlatformType='" + PlatformType + '\'' +
                ", PCodeId=" + PCodeId +
                ", ConsumeScore=" + ConsumeScore +
                '}';
    }
}
