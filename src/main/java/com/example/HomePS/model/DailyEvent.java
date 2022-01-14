package com.example.HomePS.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.time.ZoneId;

import java.time.LocalTime;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DailyEvent{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @ApiModelProperty(hidden = true)
    private Long dailyEventId;
    @NotBlank(message = "Daily event name is required")
    private String dailyEventName;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime timeStart;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime timeEnd;
    private String dayHappen;
    private double percentDiscount;
    @JsonIgnore
    @Column(nullable=false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean deleted = false;
    @JsonIgnore
    public boolean isDeleted() {
        return deleted;
    }

//    public void convertStringToDayofWeek(String){
//
//    }

//    @Transient
//    public boolean isHappenning(){
//
////        if(LocalTime.now(ZoneId.of("Vietnam/Hanoi")).isAfter(LocalTime.ofInstant(timeStart,ZoneId.of("GMT+7")){
////
////        }
//        Instant.now().atZone(ZoneId.systemDefault()).getDayOfWeek()
//        if(LocalTime.now().isAfter(timeStart) && LocalTime.now().isBefore(timeEnd)){
//            return true;
//        }
//        return false;
//    }

}
