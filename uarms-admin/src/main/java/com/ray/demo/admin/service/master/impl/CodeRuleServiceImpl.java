package com.ray.demo.admin.service.master.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ray.framework.service.common.impl.BaseServiceImpl;
import com.ray.demo.admin.dao.master.CodeRuleMapper;
import com.ray.demo.admin.model.master.CodeRule;
import com.ray.demo.admin.model.master.CodeRuleExample;
import com.ray.demo.admin.model.master.CodeRuleVo;
import com.ray.demo.admin.service.master.CodeRuleService;

@Service("CodeRuleService")
public class CodeRuleServiceImpl extends BaseServiceImpl<CodeRule, CodeRuleExample> implements  CodeRuleService {

    @Autowired
    private  CodeRuleMapper  service;
	
    @Autowired
    public CodeRuleServiceImpl(CodeRuleMapper mapper) {
        super(mapper);
        this.service = mapper;
    }

	@Override
	public String selectCode(CodeRuleVo record) {   //获得传递的值
	String code="";
	//当Id为1时;为承租人编号

	long id = record.getId();

//	record.setContractCode("201320180111");
	/*当Id为1时;为承租人编号当Id为1时;为为承租人编号* 需要给我一个id=1,客户类型
	 * 然后我根据id等于1去编码表中查找所需要的数据
	 *然后就是表达式的数据，再进行拆分；
          之后就是一个个的匹配数据
	 */

	if(record.getId()==1){
		//获得自然人法人
		
		CodeRuleExample lsExample = new CodeRuleExample();
		CodeRuleExample.Criteria criteria = lsExample.createCriteria();
		criteria.andIdEqualTo(id);
		List<CodeRule>  codeRule =  service.selectByExample(lsExample);
       int length = codeRule.size();
		
		for(int i=0;i<length;i++){
			CodeRule codeRulea = codeRule.get(i);
			//拆分表达式
	  String expression=codeRulea.getExpression();
	
	  String s1[]=expression.split("\\}");// 第一步 根据“}”来拆分

	  
	  int num = 0;
	  String[] s2=new String[100];
	  //第二次拆分
	  for (int k=0;k<s1.length;k++)
	  {
		  String s3=s1[k];
		   //每个字符串中都有 :，可以再一次取
		   String a[]=s3.split("\\{");
		   
		   for(int j=0;j<a.length;j++)
		   {
		      s2[num++]=a[j];
		   }
	  }
	 
	  Long serial=(long) 0;
//	  serial++;
	for(int y=0;y<num;y++){
		  if(s2[y].equals("流水号")){
				Calendar cal = Calendar.getInstance();//获取一个Claender实例
				if (1 == codeRulea.getResetType()) {
					if (0 == cal.get(Calendar.MONTH)) {
						if (1 == cal.get(Calendar.DATE)) {
							Long serial1 = (long) 0;
							codeRulea.setSerial(serial1);
						}
					}
				}
//			    SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");//格式化日期的形式
//			    cal.set(2017,01,01);//设置日期，此时的日期是2013年11月30号
//			    String date = sf.format(cal.getTime());//此时的date值为20131130
//			    cal.add(Calendar.DAY_OF_YEAR, -1);//减去一天
//			    date = sf.format(cal.getTime());//此时date的日期为20131129
			 serial=codeRulea.getSerial();
			 serial++;
			  int serialLength=codeRulea.getSerialLength();
			  String seriala = String.valueOf(serial);
			  int len = seriala.length();
			  //填充流水号长度
			  int a=serialLength-len;
			  int b = 1;
				while (b <= a) {
					seriala = "0" + seriala;
					b++;
				}
			   code=code+seriala;
			  codeRulea.setSerial(serial);
		  } else if(s2[y].equals("年份")){
			  Calendar cal = Calendar.getInstance();
			  int year = cal.get(Calendar.YEAR);
			 
			code+=year;
			 
			  }
		  else if(s2[y].equals("客户类型")){
			 String type1=record.getType1();
			 
			  if(type1.equals("1")){
				  type1="Z";
			  }else{
				  type1="F";
			  }
			code=code+type1;
			  }
		  else if(s2[y].equals("月份")){
			  Calendar cal = Calendar.getInstance();
			  int month = cal.get(Calendar.MONTH)+1;
			  String  m=null;
			  if(month<10){
				   m="0"+month;
					code=code+m;
			 }
		  else{
				code=code+month;
		  }
	
		  }
		  else {
			code=code+s2[y];
			  }
		  }
	  service.updateByPrimaryKey(codeRulea);
		}
	}
	return code;
	}
	
}
