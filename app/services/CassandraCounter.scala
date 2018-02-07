package services


import javax.inject.{Inject, Singleton}

import model.CassandraDAO

@Singleton
class CassandraCounter @Inject()(val cassandraDao: CassandraDAO) extends Counter{
  override def nextCount: Int = cassandraDao.getAndIncrement()
}
