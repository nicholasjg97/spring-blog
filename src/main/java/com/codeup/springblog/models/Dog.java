package com.codeup.springblog.models;

import javax.persistence.*;

@Entity
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int(11) unsigned not null auto_increment")
    private long id;
    @Column(columnDefinition = "tinyint(3) unsigned not null", unique = true)
    private int age;
    @Column(columnDefinition = "varchar(200) not null")
    private String name;
    @Column(columnDefinition = "char(2) DEFAULT 'XX'")
    private String reside_state;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReside_state() {
        return reside_state;
    }

    public void setReside_state(String reside_state) {
        this.reside_state = reside_state;
    }


}
