package controllers;

import java.sql.Timestamp;
import java.util.*;

import com.avaje.ebean.Expr;
import com.fasterxml.jackson.databind.JsonNode;
import models.Post;
import models.PostComment;
import play.mvc.Controller;
import play.mvc.Result;

import util.Common;

import static play.libs.Json.toJson;
import play.libs.Json;

public class PostController extends Controller {
	
	/* add a new post to database */
	public Result addPost() {
		JsonNode jsonNode = request().body().asJson();
		if(jsonNode == null) {
			return Common.badRequestWrapper("Post is empty");
		}
		String title = jsonNode.path("title").asText();
		String content = jsonNode.path("content").asText();
		Long authorId = jsonNode.path("authorId").asLong();
		Timestamp postAt = Timestamp.valueOf(jsonNode.path("postAt").asText());
		new Post(title, content, authorId, postAt).save();
		return ok(toJson("success"));
	}
	
	/* find post by id */
	public Result getPostById(Long postId) {
		Post post = Post.find.byId(postId);
		if(post == null) {
			return Common.badRequestWrapper("no record found");
		}
		return created(Json.toJson(post));
	}
	
	/* get all post */
	public Result getAllPosts() {
		List<Post> posts = Post.find.all();
		if(posts == null || posts.size() == 0) {
			return Common.badRequestWrapper("no record found");
		}
		return created(Json.toJson(posts));
	}
	
	public Result addComment() {
		JsonNode jsonNode = request().body().asJson();
        if(jsonNode == null) {
            return Common.badRequestWrapper("no request body");
        }
		long postId = jsonNode.path("postId").asLong();
		long commentId = jsonNode.path("commentId").asLong();
		Post post = Post.find.byId(postId);
		if(post == null) {
			return Common.badRequestWrapper("post not found");
		}
		new PostComment(postId, commentId).save();
		return ok(Json.toJson("success"));
	}
	
	public Result setAsQuestion() {
		JsonNode jsonNode = request().body().asJson();
        if(jsonNode == null) {
            return Common.badRequestWrapper("no request body");
        }
		long postId = jsonNode.path("postId").asLong();
		Post post = Post.find.byId(postId);
		if(post == null) {
			return Common.badRequestWrapper("Cannot find post");
		}
		post.setQueustion(true);
		post.save();
		return ok(Json.toJson("success"));
	}
	
	public Result setAnswer() {
		JsonNode jsonNode = request().body().asJson();
        if(jsonNode == null) {
            return Common.badRequestWrapper("no request body");
        }
		long postId = jsonNode.path("postId").asLong();
		long commentId = jsonNode.path("commentId").asLong();
		Post post = Post.find.byId(postId);
		if(post == null) {
			return Common.badRequestWrapper("Cannot find post");
		}
		post.setAnswerId(commentId);
		post.save();
		return ok(Json.toJson("success"));
	}

    public Result search(String keyword) {
        List<Post> posts = Post.find.where()
        		.or(Expr.like("title", "%" + keyword + "%"), Expr.like("content", "%" + keyword + "%"))
        		.orderBy("postAt")
        		.findList();
        if(posts == null || posts.size() == 0) {
            return Common.badRequestWrapper("No record found");
        }
        return created(Json.toJson(posts));
    }

    public Result getPostByUserId(Long userId) {
        List<Post> posts = Post.find
            .where()
            .eq("authorId", userId)
            .findList();
        if(posts == null || posts.size() == 0) {
            return Common.badRequestWrapper("No record found");
        }
        return created(Json.toJson(posts));
    }
}
