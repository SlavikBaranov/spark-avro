/*
 * Copyright 2014 Databricks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.databricks.spark.avro;

import org.apache.spark.sql.api.java.JavaSQLContext;
import org.apache.spark.sql.api.java.JavaSchemaRDD;

/**
 * A collection of static functions for working with Avro in Spark SQL
 */
public class AvroUtils {
    /** Returns a Schema RDD for the given avro path. */
    public static JavaSchemaRDD avroFile(JavaSQLContext sqlContext, String path) {
        return avroFile(sqlContext, path, 0);
    }

    /** Returns a Schema RDD for the given avro path. */
    public static JavaSchemaRDD avroFile(JavaSQLContext sqlContext, String path, int minPartitions) {
        AvroRelation relation = new AvroRelation(path, minPartitions, sqlContext.sqlContext());
        return sqlContext.baseRelationToSchemaRDD(relation);
    }

    /** Saves the specified SchemaRDD as avro file in provided directory. */
    public static void saveAsAvroFile(JavaSchemaRDD javaSchemaRDD, String path) {
        AvroSaver.save(javaSchemaRDD.schemaRDD(), path);
    }
}
