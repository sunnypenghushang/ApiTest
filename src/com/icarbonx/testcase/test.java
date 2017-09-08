package com.icarbonx.testcase;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.icarbonx.api.HttpRequest;
import com.icarbonx.api.createFood;
import com.icarbonx.api.foodUtil;
import com.icarbonx.uitls.ExcelSheet;

/*
 * 测试用例集
 */
public class test {
	String analyUrl="http://123.59.140.18/oramirror_cloud/api/analysisData.do";
	String param="app_key=xN12cQL0a6Ui2Aw1az1J&name=";
	//语音输入4种食物组合
    @Test(enabled=false)
    public void analysisData(){
    	String message=null;
    	String[] friut,drink,meat,stap,veg;
    	friut=createFood.getFruitList();
    	drink=createFood.getDrinkList();
    	meat=createFood.getMeatList();
    	stap=createFood.getStapList();
    	veg=createFood.getVegList();
    	
    	for(int i=0;i<2;i++)
    		for(int j=0;j<2;j++)
    			for(int m=0;m<2;m++)
    				for(int n=0;n<2;n++)
    					for(int k=0;k<2;k++)
    					{
    						message="我今天吃了一个"+friut[k]+"100克"+meat[j]+"一杯"+
    					    drink[i]+"200克"+veg[n]+"150克"+stap[m];
    						List<String> list=foodUtil.getFoodSearchResultList(message);
    						System.out.println(message+" 搜索结果：");
    						for(String str:list)
    						{  
    							System.out.println(str);
    						}
    						
    						
    					}

    }
    
    
   /*
    * 获取所有食物的份量
    */
    @Test(dataProvider="weightprovider",enabled=false)
    public void analysisExcel(String weight,int index)
    {   
    	//获取文件中的第一张表格
    	List<String> foodlist=ExcelSheet.getrankvalue("F://food.xls", 0, 0);
    	List<String> weightlist=new ArrayList<>();
    	Iterator<String> iterator=foodlist.iterator();
    	//搜索结果放在List中
    	ExcelSheet.writeSigleDate(weight, "F://food.xls", 0, 0, index);
    	while(iterator.hasNext())
    	{  
    	   String food=iterator.next();
			String response=foodUtil.getFoodSearchBodyStr(weight+food);
			String w=foodUtil.getFoodWeight(response);
			weightlist.add(w);
			//食物份量写入EXCEL表格中,先写表头
	    	ExcelSheet.writeList(weightlist, 0, index, "F://food.xls");
    	}
	
    }
    
    
    @DataProvider(name = "weightprovider")
    public Object[][] dataprovide(){
        return new Object[][]{
                       {"一勺",1},
                       {"一碗",2},
                       {"一个",3},
                       {"一块",4},
                       {"一份",5},
                       {"一点",6},
                       {"一口",7},
                       {"一些",8},
                       {"一杯",9},
                       {"一根",10},
                       {"一片",11},
                       {"一盘",12},
                       {"一碟",13},
                       {"一粒",14},
                       {"一顿",15},
                       {"一道",16}   
                       };
                             
    }                       
    
    /*
     * 获取食物的营养列表
     */
    @Test(enabled=false)
    public void getNutritional()
    {   

    
    	//获取列的所有值
    	List<String> foodlist=ExcelSheet.getrankvalue("F://food.xls", 0, 0);
    	List<String> nutritiona=new ArrayList<>();
    
    	//营养列表
        String jsonurl="http://60.205.107.6/oramirror_cloud/api/nutritionalAnalysis.do?app_key=xN12cQL0a6Ui2Aw1az1J";
    	HttpRequest analysistext=new HttpRequest("http://60.205.107.6/oramirror_cloud/api/analysisData.do?app_key=xN12cQL0a6Ui2Aw1az1J");
	
    	//遍历所有食物
    	for(int i=0;i<foodlist.size();i++)
    	{
    		String foodsigle=foodlist.get(i);
    		String textresult=analysistext.postRequestContent("name=我中午吃了100克"+foodsigle);
    	
    		///json格式封装后获取id
        	String id=foodUtil.getFoodId(textresult,foodsigle);
 
            //获取营养列表
           String str="[{ \"weight\": 100, \"id\":"+id+"}]";
           
           //获得到的请求结果
           String jsonresult=HttpRequest.doPostJson(jsonurl, str).toString();
           nutritiona.add(jsonresult);
           ExcelSheet.writeList(nutritiona, 0, 2, "F://food.xls");
          

    	}


    	
    	
    	
    }
    
    /*
     * 获取食物的搜索结果
     */
    @Test(enabled=false)
    public void getSearchResult()
    {   
    	HttpRequest analysistext=new HttpRequest("http://60.205.107.6/oramirror_cloud/api/analysisData.do?app_key=xN12cQL0a6Ui2Aw1az1J");
       	//获取列的所有值
    	List<String> foodlist=ExcelSheet.getrankvalue("F://food.xls", 0, 0);
    	List<String> searchresult=new ArrayList<>();
    	
    	//遍历所有食物
    	for(int i=0;i<foodlist.size();i++)
    	{
    	
    		String foodsigle=foodlist.get(i);
    		String textresult=analysistext.postRequestContent("name=我中午吃了100克鸡蛋西瓜"+foodsigle);
    	
 
    	    //搜索到的食物名称
        	String searchname=foodUtil.getFoodNameStr(textresult);
            //搜索到的食物名称放入excel表
        	searchresult.add(searchname);
        	ExcelSheet.writeList(searchresult, 0, 1, "F://food.xls");

    	}
    	
    	
    }

    
    
    
    public static void main(String args[])
    {
		String response=foodUtil.getFoodSearchBodyStr("100克鸡蛋");
		String w=foodUtil.getFoodWeight(response);
		System.out.println("份量为"+w);
    	
    }


}
