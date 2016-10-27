package org.neo4j.db;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;

/**
 * Created by Achyut Shrestha on 10/27/2016.
 */

public class GraphManager {

    public static void main(String[] args) {

        Driver driver = GraphDatabase.driver( "bolt://localhost", AuthTokens.basic( "neo4j", "secret" ) );
        Session session = driver.session() ;
        // Running a simple statement can be done like this
        session.run( "CREATE (n:Person {name:'Giornov', firstName:'Giornov', lastName:'Stansen'})-[:isFriend]->(o:Person{name:'Kate', firstName:'Kate',lastName:'Hudson'})" );

        // Or, run multiple statements together in an atomic transaction:
//        try( Transaction tx = session.beginTransaction() )
//    {
//        tx.run( "CREATE (n:'Person' {name:'Alice'})" );
//        tx.run( "CREATE (n {name:'Tina'})" );
//        tx.success();
//    }

        // Retrieve results
//        StatementResult result = session.run( "MATCH (n) RETURN n.name" );
//        List<String> names = new LinkedList<>();
//        while( result.hasNext() )
//        {
//            names.add( result.next().get("n.name").asString() );
//        }

        // Sessions are pooled, to avoid the overhead of creating new connections - this means
        // it is very important to close your session when you are done with it, otherwise you will
        // run out of sessions.
        session.close();

        // And, to clean up resources, always close the driver when your application is done
        driver.close();
    }
}
