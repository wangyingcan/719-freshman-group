package com.example.neo4jtest.model;

import lombok.Data;
import com.alibaba.excel.annotation.ExcelProperty;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@Data
@RelationshipProperties
public class PPRelation {
    @RelationshipId
    private Long id;

    @ExcelProperty(value="pid1",index=1)
    private String pid1;

    @ExcelProperty(value="pid2",index=2)
    private String pid2;

    @ExcelProperty(value="ppid",index=0)
    private String ppid;

    @TargetNode
    private Person startNode;
}
