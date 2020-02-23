package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import akka.util._
import play.api.http._
import java.util._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */

  //  クッキーの利用
  // def index(name:Option[String]) = Action{request =>
  //   val param:String = name.getOrElse("");
  //   var message = "<p>nameはありません。</p>"
  //   if (param != ""){
  //     message = "<p>nameが送られました。</p>"
  //   }
  //   val cookie = request.cookies.get("name")
  //   message += "<p>cookie: " + cookie.getOrElse(Cookie("name", "no-cookie.")).value + "</p>"
  //   val res = Ok("<tittle>Hello!</tittle><h1>Hello!</h1>" + message)
  //   .as(HTML)
  //   if (param != ""){
  //     res.withCookies(Cookie("name", param)).bakeCookies()
  //   }else{
  //     res
  //   }
  // }

  // セッションの利用
  // def index(name:Option[String], value:Option[String]) = Action{request =>
  //   val s_name:String = name.getOrElse("");
  //   val s_value:String = value.getOrElse("");
  //   val sessions = request.session.data
  //   val message = "<pre>" + sessions + "</pre>"
  //   val res = Ok("<tittle>Hello!</tittle><h1>Hello!</h1>" + message)
  //   .as("text/html")
  //   if (name != ""){
  //     res.withSession(request.session + (s_name -> s_value))
  //   }else{
  //     res
  //   }
  // }

  // viewを使用
  def index(p:Option[Int])= Action{
    Ok(views.html.index("これはコントローラーで用意したメッセージです。", p.getOrElse(0)))
  }
}