package cst438.domain;

public class TempAndTime {
public double temp;

public double getTemp() {
	return temp;
}
public void setTemp(double temp) {
	this.temp = temp;
}
public long getTime() {
	return time;
}
public void setTime(long time) {
	this.time = time;
}
public int getTimezone() {
	return timezone;
}
public void setTimezone(int timezone) {
	this.timezone = timezone;
}
public long time;
public int timezone;
public TempAndTime(double temp, long time, int timezone){
this.temp = temp;
this.time = time;
this.timezone = timezone;
}
}
