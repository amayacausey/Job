package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public void run(String... args) throws Exception {

        Author author = new Author();
        author.setName("Annie Mae");


        Job job= new Job();

        job.setDescription("See Target Website.");
        job.setPhone("555-555-5555");
        job.setPostedDate("07/27/2020");
        job.setTitle("Target Cashier");


        Set <Job> jobs = new HashSet<Job>();
        jobs.add(job);

        Job job2= new Job();
        job2.setTitle("Walmart Associate");
        job2.setPostedDate("07/27/2020");
        job2.setPhone("333-333-3333");
        job2.setDescription("See Walmart Careers");
        jobs.add(job2);

        author.setJobs(jobs);

        authorRepository.save(author);
        job.setAuthor(author);
        job2.setAuthor(author);

        jobRepository.save(job);
        jobRepository.save(job2);


    }
}
