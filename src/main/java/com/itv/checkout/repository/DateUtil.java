package com.itv.checkout.repository;

import java.util.Calendar;

import org.springframework.stereotype.Service;

@Service
public class DateUtil {
	
	public java.util.Date getCurrentDate() {
		return Calendar.getInstance().getTime();
	}
}