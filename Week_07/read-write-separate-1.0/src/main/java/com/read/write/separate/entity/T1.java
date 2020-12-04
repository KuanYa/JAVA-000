package com.read.write.separate.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="t1")
@Data
public class T1 {
	@Id
    private Integer id;

}