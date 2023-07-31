package com.example.neo4jtest.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.*;

//问了都说不一定需要主键
@Data
@RelationshipProperties
public class PMRelation {
    @RelationshipId
    private Long id;

    @ExcelProperty(value = "pmid",index = 0)
    private String pmid;

    @TargetNode
    private Person startNode;

    @ExcelProperty(value = "pid",index = 1)
    private String pid;

    @ExcelProperty(value = "pid",index = 2)
    private String mid;
}
