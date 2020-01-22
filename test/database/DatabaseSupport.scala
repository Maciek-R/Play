package database

import java.sql.Connection

import play.api.db.{Database, Databases}

trait DatabaseSupport {
  def withMyDatabase[T](block: Database => T) = {
    Databases.withDatabase(
      driver = "org.postgresql.Driver",
      url = "jdbc:postgresql://localhost:5432/test",
      config = Map(
        "user" -> "postgres",
        "password" -> "qwerty"
      )
    )(block)
  }

  def withConnection[T](block: Connection => T) = {
    withMyDatabase{
      database => block(database.getConnection(false))
    }
  }
}
