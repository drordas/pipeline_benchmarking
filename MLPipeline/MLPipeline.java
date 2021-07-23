/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.simple.project;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.ml.Pipeline;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.Transformer;
import org.apache.spark.ml.param.ParamMap;

/**
 *
 * @author María Novo
 */
public class MLPipeline {

    public static void createEmptyTask() {

        SparkSession spark = SparkSession
                .builder()
                .config("spark.master", "local")
                .getOrCreate();
   
        List<Row> data = Arrays.asList(
                RowFactory.create(0.0, "This is a test")
        );
        StructType schema = new StructType(new StructField[]{
            new StructField("label", DataTypes.DoubleType, false, Metadata.empty()),
            new StructField("sentence", DataTypes.StringType, false, Metadata.empty())
        });
        Dataset<Row> dataset = spark.createDataFrame(data, schema);

        //A null transformer
        NullTransformer nullTransformer = new NullTransformer();

        Pipeline pipeline = new Pipeline();
        pipeline.setStages(new PipelineStage[]{nullTransformer});
        

        for (int i = 0; i < 100000; i++) {
            pipeline.fit(dataset);
        }

    }
    
     public static void createTwoTasks() {

        SparkSession spark = SparkSession
                .builder()
                .config("spark.master", "local")
                .getOrCreate();
   
        List<Row> data = Arrays.asList(
                RowFactory.create(0.0, "This is a test")
        );
        StructType schema = new StructType(new StructField[]{
            new StructField("label", DataTypes.DoubleType, false, Metadata.empty()),
            new StructField("sentence", DataTypes.StringType, false, Metadata.empty())
        });
        Dataset<Row> dataset = spark.createDataFrame(data, schema);

        //A null transformer
        NullTransformer nullTransformer = new NullTransformer();
        NullTransformer nullTransformer2 = new NullTransformer();

        Pipeline pipeline = new Pipeline();
        pipeline.setStages(new PipelineStage[]{nullTransformer, nullTransformer2});
        

        for (int i = 0; i < 100000; i++) {
            pipeline.fit(dataset);
        }

    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        createEmptyTask();
        long end = System.currentTimeMillis();
        System.out.println("Time 1: " + (end - start));
        
        start = System.currentTimeMillis();
        createTwoTasks();
        end = System.currentTimeMillis();
        System.out.println("Time : " + (end - start));
    }

}

class NullTransformer extends Transformer {

    public NullTransformer() {
    }

    public org.apache.spark.ml.Transformer copy(ParamMap extra) {
        return new NullTransformer();
    }

    @Override
    public Dataset<Row> transform(Dataset<?> dataset) {
        return dataset.toDF();
    }

    @Override
    public StructType transformSchema(StructType st) {
        return st;
    }

    @Override
    public String uid() {
        return Identifiable$.MODULE$.randomUID("mycustom");
    }

}

