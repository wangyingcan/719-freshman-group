package com.example.neo4jtest.model;


import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

@Data       //Lombok注解，作用是简化bean的编写
@Node(labels = "Person")
public class Person {

    @Id
    @ExcelProperty(value = "pid",index = 0)
    private String pid;

    @ExcelProperty(value = "name",index = 1)
    private String name;

    @Relationship(type = "IS_BROTHER_OF", direction = Relationship.Direction.INCOMING)
    private List<PPRelation> ppRelations = new ArrayList<>();
}
