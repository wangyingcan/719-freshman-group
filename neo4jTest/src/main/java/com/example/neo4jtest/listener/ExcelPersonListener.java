package com.example.neo4jtest.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.neo4jtest.model.Person;
import com.example.neo4jtest.service.impl.PersonServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelPersonListener extends AnalysisEventListener<Person> {

    //@Autowired
    private PersonServiceImpl personService;
    //创建list集合封装最终的数据
    public ExcelPersonListener(PersonServiceImpl personService){
        this.personService=personService;
    }

    List<Person> list = new ArrayList<Person>();
    //一行一行去读取excle内容，把每行的内容封装到实体类对象中
    //注：读取是从第二行开始读取，第一行默认为表头，不进行读取。
    @Override
    public void invoke(Person person, AnalysisContext analysisContext) {
        personService.savePerson(person);
    }

    //读取excel表头信息，headMap即为表头信息
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息："+headMap);
    }
    //读取完成后执行
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }
}
