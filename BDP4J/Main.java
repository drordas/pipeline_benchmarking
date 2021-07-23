/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bdp4j_evaluator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bdp4j.pipe.AbstractPipe;
import org.bdp4j.pipe.ParallelPipes;
import org.bdp4j.pipe.SerialPipes;
import org.bdp4j.types.Instance;

/**
 *
 * @author María Novo
 */
public class Main {

    public static final int NUM_TESTS = 100000;

    /**
     * A logger for logging purposes
     */
    private static final Logger logger = LogManager.getLogger(Main.class);

    /**
     * List of instances that are being processed
     */
    private static List<Instance> instances = new ArrayList<Instance>();

    /*
     * The main method for the running application
     */
    public static void main(String[] args) {
        System.out.println("Program started.");
        System.out.println("-- First case -- ");
        firstCase();
        System.out.println("-- Second case -- ");
        secondCase();
        System.out.println("-- Third case -- ");
        thirdCase();
    }

    public static void firstCase() {
        long start = System.currentTimeMillis();

        Instance instance = new Instance("The data", "The target", "The name", "The source");
        instances = Arrays.asList(instance);

        AbstractPipe p = new EmptyPipe();

        for (int i = 0; i < NUM_TESTS; i++) {
            p.pipeAll(instances);
        }

        long time = System.currentTimeMillis() - start;
        System.out.println("time: " + time);
    }

    public static void secondCase() {
        long start = System.currentTimeMillis();

        Instance instance = new Instance("The data", "The target", "The name", "The source");
        instances = Arrays.asList(instance);

        AbstractPipe p = new SerialPipes(new AbstractPipe[]{new EmptyPipe(), new EmptyPipe()});

        for (int i = 0; i < NUM_TESTS; i++) {
            p.pipeAll(instances);
        }

        long time = System.currentTimeMillis() - start;
        System.out.println("time: " + time);
    }

    public static void thirdCase() {
        long start = System.currentTimeMillis();

        Instance instance = new Instance("The data", "The target", "The name", "The source");
        instances = Arrays.asList(instance);

        AbstractPipe p = new ParallelPipes(new AbstractPipe[]{new EmptyPipe(), new EmptyPipe()});

        for (int i = 0; i < NUM_TESTS; i++) {
            p.pipeAll(instances);
        }

        long time = System.currentTimeMillis() - start;
        System.out.println("time: " + time);
    }
}
