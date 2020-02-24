package controllers

import java.sql._
import javax.inject._
import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.db._
import PersonForm._

@Singleton
class HomeController @Inject()(db: Database, cc: MessagesControllerComponents) extends MessagesAbstractController(cc){

  def index() = Action{implicit request =>
    var msg = "database record:<br><ul>"
    try{
      db.withConnection{conn =>
      val stmt = conn.createStatement
      val rs = stmt.executeQuery("SELECT * from people")
      while(rs.next){
        msg += "<li>" + rs.getInt("id") + ":" + rs.getString("name") + "</li>"
      }
      msg += "</ul>"
      }
    } catch{
      case e:SQLException =>
      msg = "<li>no record...</li>"
    }
    Ok(views.html.index(msg))
  }

  def add() = Action{implicit request =>
    Ok(views.html.add("フォームを記入して下さい。", form))
  }

  def create() = Action{implicit request =>
    val formdata = form.bindFromRequest
    val data = formdata.get
    try
      db.withConnection{conn =>
        val ps = conn.prepareStatement(
          "insert into people values (default, ?, ?, ?)")
        ps.setString(1, data.name)
        ps.setString(2, data.mail)
        ps.setString(3, data.tel)
        ps.executeUpdate
      }
    catch{
      case e: SQLException =>
        Ok(views.html.add(
          "フォームを入力してください。", form
        ))
    }
    Redirect(routes.HomeController.index)
  }

  def edit(id:Int) = Action{implicit request =>
    var formdata = form.bindFromRequest
    try{
      db.withConnection{conn =>
        val stmt = conn.createStatement
        val rs = stmt.executeQuery("selest * from people where id=" + id)
        rs.next
        val name = rs.getString("name")
        val mail = rs.getString("mail")
        val tel = rs.getString("tel")
        val data = Data(name, mail, tel)
        formdata = form.fill(data)
      }
    }catch{
      case e:SQLException =>
        Redirect(routes.HomeController.index)
    }
    Ok(views.html.edit("フォームを編集して下さい。", formdata, id))
  }

  def update(id:Int) = Action{implicit request =>
    val formdata = form.bindFromRequest
    val data = formdata.get
    try
      db.withConnection{conn =>
        val ps = conn.prepareStatement("update people set name=?, mail=?, tel=? where id=?")
        ps.setString(1, data.name)
        ps.setString(2, data.mail)
        ps.setString(3, data.tel)
        ps.setInt(4, id)
        ps.executeUpdate
      }
    catch{
      case e:SQLException =>
        Ok(views.html.add("フォームを入力してください。", form))
    }
    Redirect(routes.HomeController.index)
  }
}