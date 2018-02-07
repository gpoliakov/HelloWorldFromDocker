package model

import javax.inject.Singleton

import com.datastax.driver.core.{Cluster, Session}
import com.datastax.driver.core.querybuilder.{QueryBuilder => QB}
import com.typesafe.config.ConfigFactory

import scala.collection.JavaConverters._

@Singleton
case class CassandraDAO() {

  private val cluster = {
    Cluster.builder()
      .addContactPoint(ConfigFactory.load().getString("cassandra.node"))
      // .withCredentials("username", "password")
      .build()
  }
  private val keySpace = "test"

  private val session      = cluster.connect()

  private val table = "Counter"
  private val num = "num"
  createKeySpace()
  createTable()

  def createKeySpace() : Unit = {
    val query = s"CREATE KEYSPACE IF NOT EXISTS $keySpace WITH replication = {'class':'SimpleStrategy', 'replication_factor':3};"
    session.execute(query)
  }

  def createTable() : Unit = {
    val query = s"create table if not exists $keySpace.$table ($num int primary key)"
    session.execute(query)
  }

  def dropTable(): Unit = {
    val query = s"drop table if exists $keySpace.$table"
    session.execute(query)
  }

  def selectMax: Int = {
    val query = {
      QB.select(num)
        .from(keySpace, table)
    }
    val res = session.execute(query).asScala.map(row => row.getInt(num))

    if (res.isEmpty) 0 else res.max
  }

  def getAndIncrement(): Int = {
    val query = {
      QB.insertInto(keySpace, table)
        .value(num, selectMax + 1)
    }
    session.execute(query)
    selectMax
  }
}
