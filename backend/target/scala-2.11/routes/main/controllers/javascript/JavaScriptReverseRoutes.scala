
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/Anna/Documents/graduate2/soc/SSNetwork/backend/conf/routes
// @DATE:Sun Apr 24 14:28:07 PDT 2016

import play.api.routing.JavaScriptReverseRoute
import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers.javascript {
  import ReverseRouteContext.empty

  // @LINE:9
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:9
    def at: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.at",
      """
        function(file1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file1)})
        }
      """
    )
  
  }

  // @LINE:39
  class ReverseSubscriptionController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:40
    def loadSubscriptionListByCategory: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SubscriptionController.loadSubscriptionListByCategory",
      """
        function(category0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "subscribe/list/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("category", encodeURIComponent(category0))})
        }
      """
    )
  
    // @LINE:39
    def loadSubscriptionList: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SubscriptionController.loadSubscriptionList",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "subscribe/list/all"})
        }
      """
    )
  
    // @LINE:42
    def subscribe: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SubscriptionController.subscribe",
      """
        function(followeeid0,category1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "subscribe/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("followeeid", followeeid0) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("category", encodeURIComponent(category1))})
        }
      """
    )
  
    // @LINE:41
    def loadTimeline: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SubscriptionController.loadTimeline",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "subscribe/timeline"})
        }
      """
    )
  
  }

  // @LINE:6
  class ReverseApplication(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:20
    def getPaperByTitle: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Application.getPaperByTitle",
      """
        function(title0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "title/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("title", encodeURIComponent(title0))})
        }
      """
    )
  
    // @LINE:19
    def getPaperByYear: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Application.getPaperByYear",
      """
        function(year0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "year/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("year", year0)})
        }
      """
    )
  
    // @LINE:11
    def upload: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Application.upload",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "person/uploadPic"})
        }
      """
    )
  
    // @LINE:6
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Application.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
  }

  // @LINE:30
  class ReversePostController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:31
    def getPostById: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.PostController.getPostById",
      """
        function(postId0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "post/getPostById/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("postId", postId0)})
        }
      """
    )
  
    // @LINE:35
    def setAnswer: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.PostController.setAnswer",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "post/setAnswer"})
        }
      """
    )
  
    // @LINE:30
    def addPost: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.PostController.addPost",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "post/addPost"})
        }
      """
    )
  
    // @LINE:32
    def getAllPosts: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.PostController.getAllPosts",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "post/getAllPosts"})
        }
      """
    )
  
    // @LINE:33
    def addComment: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.PostController.addComment",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "post/addComment"})
        }
      """
    )
  
    // @LINE:34
    def setAsQuestion: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.PostController.setAsQuestion",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "post/setAsQuestion"})
        }
      """
    )
  
  }

  // @LINE:24
  class ReverseForumController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:25
    def getComments: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ForumController.getComments",
      """
        function(rootid0,categoryid1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "comment" + _qS([(""" + implicitly[QueryStringBindable[Long]].javascriptUnbind + """)("rootid", rootid0), (""" + implicitly[QueryStringBindable[Long]].javascriptUnbind + """)("categoryid", categoryid1)])})
        }
      """
    )
  
    // @LINE:24
    def addComment: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ForumController.addComment",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "comment"})
        }
      """
    )
  
    // @LINE:26
    def updateComment: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ForumController.updateComment",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "updateComment"})
        }
      """
    )
  
    // @LINE:27
    def uploadFile: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ForumController.uploadFile",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "upload"})
        }
      """
    )
  
  }


}
