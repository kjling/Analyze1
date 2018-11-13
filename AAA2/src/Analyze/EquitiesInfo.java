package Analyze;

public class EquitiesInfo {
	
	private String code;
	private String name;
	private String nominal_price;
	private String pc;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getNominal_price() {
		return nominal_price;
	}
	public void setNominal_price(String nominalPrice) {
		nominal_price = nominalPrice;
	}
	
	public String getPc() {
		return pc;
	}
	public void setPc(String pc) {
		this.pc = pc;
	}
	
	public EquitiesInfo(String code,String name,String nominal_price,String pc){
		this.code=code;
		this.name=name;
		this.nominal_price=nominal_price;
		this.pc=pc;
	}
	public EquitiesInfo(){}
}
