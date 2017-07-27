package controllers



import models.CD
import javax.inject.Inject

import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc._



class Application @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport {

  def index = Action {
    Ok(views.html.index("Hello World"))
  }

  def hello(name: String) = Action {
    Ok("Hello " + name)
  }

  def createCD = Action { implicit request =>
    val formValidationResult = CD.createCD.bindFromRequest
    formValidationResult.fold({ formWithErrors =>
      BadRequest(views.html.listAll(CD.cds, formWithErrors))
    }, { cd =>
      CD.cds.append(cd)
      Redirect(routes.Application.listCDs())
    })
  }

  def listCDs = Action { implicit request =>
    Ok(views.html.listAll(CD.cds, CD.createCD))
  }

  def getCDbyId(id: Int) = Action {
    implicit request =>
      Ok(views.html.cdById(CD.cds, id)) //need to put what i want on the html page here aswell. Since the HTML page needs
    //CD.cds and id, i need to pass it in Ok(views.html.cdById(parameters that need to be in the html files here))


  }



  def edit(id: Int) = Action {
    implicit request =>
      Ok(views.html.editCD(CD.cds, CD.createCD.fill(CD.cds(id)), id))
    /**The fill thing fills the forms with the right CDs data
      */

  }

  def editCD(id: Int) = Action { implicit request =>
    val formValidationResult = CD.createCD.bindFromRequest
    formValidationResult.fold({ formWithErrors =>
      BadRequest(views.html.listAll(CD.cds, formWithErrors))
    }, { cd =>
      CD.cds.update(id, cd)
      Redirect(routes.Application.listCDs())
    })
  }

  def delete(id: Int) = Action {
    implicit request =>
      Ok(views.html.deleteCD(CD.cds, CD.createCD, id))
  }

  def deleteCD(id: Int) = Action{
    implicit request=>
      CD.cds.remove(id)
      Redirect(routes.Application.listCDs())

  }


}