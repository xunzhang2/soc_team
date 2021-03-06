// var suggestCallBack; 
// var suggestions = [];	
Template.suggestion.onRendered(function() {
		var config = {
			siteURL: 'byKeyWord', // Change this to your site
			searchSite: true,
			type: 'web',
			append: false,
			perPage: 8, // A maximum of 8 is allowed by Google
			page: 0 // The start page
		}
		
		var arrow = $('<span>', {
			className: 'arrow'
		}).appendTo('ul.icons');

		$('ul.icons li').click(function() {
			var el = $(this);

			if (el.hasClass('active')) {
				// The icon is already active, exit
				return false;
			}

			el.siblings().removeClass('active');
			el.addClass('active');

			// Move the arrow below this icon
			arrow.stop().animate({
				left: el.position().left,
				marginLeft: (el.width() / 2) - 4
			});

			// Set the search type
			config.type = el.attr('data-searchType');
			$('#more').fadeOut();
		});

		// Adding the site domain as a label for the first radio button:
		$('#siteNameLabel').append(' ' + config.siteURL);

		// Marking the Search tutorialzine.com radio as active:
		$('#searchSite').click();

		// Marking the web search icon as active:
		$('li.web').click();

		// Focusing the input text box:
		$('#s').focus();


		$('#searchForm').submit(function() {
		var input = $("#s").val();
		var radio = $("input[name=check]:checked").val();
		console.log(input);
        if(input!=null){
           // getSuggestions(input);
        }
		console.log(radio);
		if(radio==='keyWord'){
			getPaperByKeyWord(input);
		}
		if(radio==='byYear'){
			getPaperByYear(input);
		}
		if(radio==='byTitle'){
			getPaperByTitle(input);
		}
		if(radio==='web'){
			googleSearch();
		}

		
		return false;
	});

		$('#searchSite,#searchWeb').change(function() {
			// Listening for a click on one of the radio buttons.
			// config.searchSite is either true or false.

			config.searchSite = this.id == 'searchSite';
		});
		function getSuggestions(input){
          suggestions.splice(0,suggestions.length)
          $.getJSON("http://suggestqueries.google.com/complete/search?callback=?",
                {
                  "hl":"en", // Language
                  "jsonp":"suggestCallBack", // jsonp callback function name
                  "q":input, // query term
                   "client":"chrome" // force youtube style response, i.e. jsonp
                }
            );
             
            suggestCallBack = function (data) {
                
                $.each(data[1], function(key, val) {
                    suggestions.push({"value":val});
                });
                
                suggestions.length = 5; // prune suggestions list to only 5 items
                for(var i=1;i<6;i++){
                  console.log(suggestions[i-1].value);
              
              }
          }; 


           $( "#s" ).autocomplete({
           source: suggestions
          });
    }
        //function search
    function getPaperByYear(input){
    	var stringUrl = "http://localhost:9000/year/"+input; 
			var resultsDiv = $('#resultsDiv');
            $.ajax({
                type : "GET",
                contentType : "application/x-www-form-urlencoded; charset=utf-8",
                url : stringUrl,
                //User-Agent: 'Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.5; en-US; rv:1.9.1b3pre) Gecko/20081130 Minefield/3.1b3pre'
                dataType: 'json',
                cache : false,
                success : function(response) {
                console.log("dddd");
               // console.log("Data Loaded: " + JSON.stringify(response));
                $('#more').remove();  
                var results=response;
                console.log(results);
                if (results.length) {

				// If results were returned, add them to a pageContainer div,
				// after which append them to the #resultsDiv:

				var pageContainer = $('<div>', {
					className: 'pageContainer'
				});
				for (var i = 0; i < results.length; i++) {
					// Creating a new result object and firing its toString method:
					console.log(results[i]);
					pageContainer.append(new result(results[i]) + '');
				}
                resultsDiv.empty();
				pageContainer.append('<div class="clear"></div>')
					.hide().appendTo(resultsDiv)
					.fadeIn('slow');

			    } else{

				resultsDiv.empty();
				$('<p>', {
					className: 'notFound',
					html: 'No Results Were Found!'
				}).hide().appendTo(resultsDiv).fadeIn();

			    }
               },
                error : function(xhr, textStatus, errorThrown) {
                	console.log("sdssdsd");
                    console.log("error : " + textStatus);
                }
            });

    }   
        //function search
    function getPaperByTitle(input){
    	var stringUrl = "http://localhost:9000/title/"+input; 
			var resultsDiv = $('#resultsDiv');
            $.ajax({
                type : "GET",
                contentType : "application/x-www-form-urlencoded; charset=utf-8",
                url : stringUrl,
                //User-Agent: 'Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.5; en-US; rv:1.9.1b3pre) Gecko/20081130 Minefield/3.1b3pre'
                dataType: 'json',
                cache : false,
                success : function(response) {
                console.log("dddd");
               // console.log("Data Loaded: " + JSON.stringify(response));
                $('#more').remove();  
                var results=response;
                console.log(results);
                if (results.length) {

				// If results were returned, add them to a pageContainer div,
				// after which append them to the #resultsDiv:

				var pageContainer = $('<div>', {
					className: 'pageContainer'
				});
				for (var i = 0; i < results.length; i++) {
					// Creating a new result object and firing its toString method:
					console.log(results[i]);
					pageContainer.append(new result(results[i]) + '');
				}
                resultsDiv.empty();
				pageContainer.append('<div class="clear"></div>')
					.hide().appendTo(resultsDiv)
					.fadeIn('slow');

			    } else{

				resultsDiv.empty();
				$('<p>', {
					className: 'notFound',
					html: 'No Results Were Found!'
				}).hide().appendTo(resultsDiv).fadeIn();

			    }
               },
                error : function(xhr, textStatus, errorThrown) {
                	console.log("sdssdsd");
                    console.log("error : " + textStatus);
                }
            });

    }
	    //function search
		function googleSearch(settings) {

			// If no parameters are supplied to the function,
			// it takes its defaults from the config object above:

			settings = $.extend({}, config, settings);
			console.log(settings);
			settings.term = settings.term || $('#s').val();

			if (settings.searchSite) {
				// Using the Google site:example.com to limit the search to a
				// specific domain:
				settings.term = 'site:' + settings.siteURL + ' ' + settings.term;
			}

			// URL of Google's AJAX search API
			var apiURL = 'http://ajax.googleapis.com/ajax/services/search/' + settings.type + '?v=1.0&callback=?';
			var resultsDiv = $('#resultsDiv');

			$.getJSON(apiURL, {
				q: settings.term,
				rsz: settings.perPage,
				start: settings.page * settings.perPage
			}, function(r) {

				var results = r.responseData.results;
				console.log(JSON.stringify(results[0]));
				$('#more').remove();

				if (results.length) {

					// If results were returned, add them to a pageContainer div,
					// after which append them to the #resultsDiv:

					var pageContainer = $('<div>', {
						className: 'pageContainer'
					});

					for (var i = 0; i < results.length; i++) {
						// Creating a new result object and firing its toString method:
						pageContainer.append(new result(results[i]) + '');
					}

					if (!settings.append) {
						// This is executed when running a new search, 
						// instead of clicking on the More button:
						resultsDiv.empty();
					}

					pageContainer.append('<div class="clear"></div>')
						.hide().appendTo(resultsDiv)
						.fadeIn('slow');

					var cursor = r.responseData.cursor;

					// Checking if there are more pages with results, 
					// and deciding whether to show the More button:

					if (+cursor.estimatedResultCount > (settings.page + 1) * settings.perPage) {
						$('<div>', {
							id: 'more'
						}).appendTo(resultsDiv).click(function() {
							googleSearch({
								append: true,
								page: settings.page + 1
							});
							$(this).fadeOut();
						});
					}
				} else {

					// No results were found for this search.

					resultsDiv.empty();
					$('<p>', {
						className: 'notFound',
						html: 'No Results Were Found!'
					}).hide().appendTo(resultsDiv).fadeIn();
				}
			});
		}
	    
	    function getPaperByKeyWord(input,settings){
	    	var stringUrl = "http://localhost:9000/keyword/"+input; 
				var resultsDiv = $('#resultsDiv');
	            $.ajax({
	                type : "GET",
	                contentType : "application/x-www-form-urlencoded; charset=utf-8",
	                url : stringUrl,
	                //User-Agent: 'Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.5; en-US; rv:1.9.1b3pre) Gecko/20081130 Minefield/3.1b3pre'
	                dataType: 'json',
	                cache : false,
	                success : function(response) {
	                console.log("dddd");
	               // console.log("Data Loaded: " + JSON.stringify(response));
	                $('#more').remove();  
	                var results=response;
	                console.log(results);
	                if (results.length) {

					// If results were returned, add them to a pageContainer div,
					// after which append them to the #resultsDiv:

					var pageContainer = $('<div>', {
						className: 'pageContainer'
					});
					for (var i = 0; i < results.length; i++) {
						// Creating a new result object and firing its toString method:
						console.log(results[i]);
						pageContainer.append(new result(results[i]) + '');
					}
	                resultsDiv.empty();
					pageContainer.append('<div class="clear"></div>')
						.hide().appendTo(resultsDiv)
						.fadeIn('slow');

				    } else{

					resultsDiv.empty();
					$('<p>', {
						className: 'notFound',
						html: 'No Results Were Found!'
					}).hide().appendTo(resultsDiv).fadeIn();

				    }
	               },
	                error : function(xhr, textStatus, errorThrown) {
	                	console.log("sdssdsd");
	                    console.log("error : " + textStatus);
	                }
	            });
	    }


		function result(r) {

			// This is class definition. Object of this class are created for
			// each result. The markup is generated by the .toString() method.
	        console.log(r.GsearchResultClass);

			var arr = [];
			// GsearchResultClass is passed by the google API
			switch (r.GsearchResultClass) {
				case 'GwebSearch':
					arr = [
					    console.log(r.unescapedUrl),
						'<div class="webResult">',
						'<h2><a href="', r.unescapedUrl, '" target="_blank">', r.title, '</a></h2>',
						'<p>', r.content, '</p>',
						'<a href="', r.unescapedUrl, '" target="_blank">', r.visibleUrl, '</a>',
						'</div>'
						
					];
					break;
				case 'GimageSearch':
					arr = [
						'<div class="imageResult">',
						'<a target="_blank" href="', r.unescapedUrl, '" title="', r.titleNoFormatting, '" class="pic" style="width:', r.tbWidth, 'px;height:', r.tbHeight, 'px;">',
						'<img src="', r.tbUrl, '" width="', r.tbWidth, '" height="', r.tbHeight, '" /></a>',
						'<div class="clear"></div>', '<a href="', r.originalContextUrl, '" target="_blank">', r.visibleUrl, '</a>',
						'</div>'
					];
					break;
				case 'GvideoSearch':
					arr = [
						'<div class="imageResult">',
						'<a target="_blank" href="', r.url, '" title="', r.titleNoFormatting, '" class="pic" style="width:150px;height:auto;">',
						'<img src="', r.tbUrl, '" width="100%" /></a>',
						'<div class="clear"></div>', '<a href="', r.originalContextUrl, '" target="_blank">', r.publisher, '</a>',
						'</div>'
					];
					break;
				case 'GnewsSearch':
					arr = [
						'<div class="webResult">',
						'<h2><a href="', r.unescapedUrl, '" target="_blank">', r.title, '</a></h2>',
						'<p>', r.content, '</p>',
						'<a href="', r.unescapedUrl, '" target="_blank">', r.publisher, '</a>',
						'</div>'
					];
					break;
				case 'getPaperByKeyWord':
			    arr = [
					'<div class="webResult">',
					'<h2><a href="', r.ee, '" target="_blank">', r.title, '</a></h2>',
					'<p>', r.authors, '</p>',
					'<a href="', r.url, '" target="_blank">', r.year, '</a>',
                    '<a href="', r.url, '" target="_blank">', 9.2, '</a>',
					'</div>'
				];
				break;
				case 'getPaperByYear':
				    arr = [
						'<div class="webResult">',
						'<h2><a href="', r.ee, '" target="_blank">', r.title, '</a></h2>',
						'<p>', r.authors, '</p>',
						'<a href="', r.url, '" target="_blank">', r.year, '</a>',
	                    '<a href="', r.url, '" target="_blank">', 9.2, '</a>',
						'</div>'
					];
					break;
				case 'getPaperByTitle':
				    arr = [
						'<div class="webResult">',
						'<h2><a href="', r.ee, '" target="_blank">', r.title, '</a></h2>',
						'<p>', r.authors, '</p>',
						'<a href="', r.url, '" target="_blank">', r.year, '</a>',
	                    '<a href="', r.url, '" target="_blank">', 9.2, '</a>',
						'</div>'
					];
					break;
			}
			// The toString method.
			this.toString = function() {
				return arr.join('');
			}
		}
	});
