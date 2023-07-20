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

    @Relationship(type = "ACT IN", direction = Relationship.Direction.INCOMING)
    private List<PMRelation> pmRelations = new ArrayList<>();
}
