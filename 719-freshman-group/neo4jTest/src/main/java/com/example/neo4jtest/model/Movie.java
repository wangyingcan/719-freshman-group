package com.example.neo4jtest.model;


import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

@Data
@Node(labels = "Movie")
public class Movie {

    @Id
    @ExcelProperty(value = "mid",index = 0)
    private String mid;

    @ExcelProperty(value = "title",index = 1)
    private String title;

    @Relationship(type = "ACT_IN", direction = Relationship.Direction.INCOMING)     //此处的type对应的是关系类型，用在@Query查询指定关系类型时
    private List<PMRelation> pmRelations = new ArrayList<>();       //此处泛型中的类型对应关系属性类型，关系属性类型是PMRelation
}
