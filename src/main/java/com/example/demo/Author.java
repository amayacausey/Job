package com.example.demo;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;



    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private Set<Job> jobs;


    public Author() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Job> getJobs() {
        return jobs;
    }

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }
}
