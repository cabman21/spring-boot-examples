package com.example.demo.service.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class GpsDTO implements Serializable {

	private static final long serialVersionUID = 5909200109312074068L;

	private String kind = null;

	private String teminalId = null;

	private String can = null;

	private String keyonDatetime = null;

	private String datatime = null;

	private Long imei = null;

	private Long imsi = null;

	private Double longitude = null;

	private Double latitude = null;

	private Integer idleTime = null;

	private Integer speed = null;

	private Integer shock = null;

	private Integer seatBelt = null;

	private Integer seatOn = null;

	private Integer canOn = null;

	private String checkMes = null;

	private String isGps = null;

}
