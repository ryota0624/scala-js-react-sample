package application

import org.scalajs.dom

import scala.concurrent.Future
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.scalajs.js
import scala.scalajs.js.JSON

/**
  * Created by ryota on 2017/02/11.
  */


object Request {
  val jsonheaders = Map("Content-Type" -> "application/json")
  val formDataheaders = Map("Content-Type" -> "application/x-www-form-urlencoded")

  def getJSON[T](url: String): Future[T] = dom.ext.Ajax.get(
    url = url).map(r => JSON.parse(r.responseText).asInstanceOf[T])

  def postJSON[T](url: String, data: dom.ext.Ajax.InputData) = dom.ext.Ajax.post(url = url, data = data, headers = jsonheaders).map({ result =>
    JSON.parse(result.responseText).asInstanceOf[T]
  })

  def postFormData[T](url: String, data: Map[String, Any]) = dom.ext.Ajax.post(url = url, data = map2String(data), headers = formDataheaders)
    .map({ result =>
      JSON.parse(result.responseText).asInstanceOf[T]
    })

  def encode(value: String) = js.URIUtils.encodeURIComponent(value)

  private def map2String(data: Map[String, Any]): String = {
    val valueArray = data.toArray.map { case (key, value) =>
      value match {
        case str: String => Some(s"${key}=${encode(str)}")
        case num: Int => Some(s"${key}=${value}")
        case _ => None
      }
    }

    val filterdValue = for (
      valueOption <- valueArray;
      value <- valueOption
    ) yield {
      value
    }

    filterdValue.reduce(_ + "&" + _)
  }


}

