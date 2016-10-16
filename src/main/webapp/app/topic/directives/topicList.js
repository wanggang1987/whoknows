
'use strict';

angular.module('wkTopic').directive('topicList', function ($location, $log) {

	return {
		restrict: 'AE',
		templateUrl: 'app/topic/directives/topicList.html',
		replace: true,
		scope: {
			topic: '='
		}, 
		link: function (scope, element, attr) {
			//可以点击 显示全部
			scope.expandTopicContentAble = true;
			
			
			scope.topicLists=[
			             {	id: 1,
			            	 	topicHeader:"C-N欧联有没有可能不用陪体呢?",
			            	 	topicAuthor:{
			            	 		img : "../../images/selfPicture/2.png",
			            	 		baseInfo:"陈雯婷 | 药明康德新药开发有限公司 | 资深化学家"
			            	 	},
			             	topicContent:"做一周了试了三个体系，铜/2价耙/零价耙，磷配体，一四二氧六环/DMF   N2 保护做出来打谱对不上，积峰14个未知化合物。再做不出来没法交代了，要跪在原料的道路。"+
			             					"，铜/2价耙/零价耙，磷配体，一四二氧六环/DMF   N2 保护做出来打谱对不上，积峰14个未知化合物。再做不出来没法交代了，要跪在原料的道路。"+
			             					"，铜/2价耙/零价耙，磷配体，一四二氧六环/DMF   N2 保护做出来打谱对不...",
			             	commentListsExpandAble : true,
			             	commentLists:[{	id: 1,
						    				commentAuthor:{
						            	 		img : "../../images/selfPicture/1.png",
						            	 		baseInfo:"李菲 | 药明康德新药开发有限公司 | 资深化学家"
						            	 		},
						    	        	 	commentContent:"亲测可以",
						    				},{	id: 2,
						    					commentAuthor:{
						    	        	 		img : "../../images/selfPicture/3.jpg",
						    	        	 		baseInfo:"静兰 | 药明康德新药开发有限公司 | 资深化学家"
						    	        	 		},
						    		        	 	commentContent:"经过高温更好",
						    					},{	id: 3,
						    						commentAuthor:{
						    		        	 		img : "../../images/selfPicture/2.png",
						    		        	 		baseInfo:"汪健 | 药明康德新药开发有限公司 | 资深化学家"
						    		        	 		},
						    			        	 	commentContent:"亲测可以",
						    						}]
			             },
			             {	id: 2,
			            	 	topicHeader:"如何提高合成效率?",
			            	 	topicAuthor:{
			            	 		img : "../../images/selfPicture/1.png",
			            	 		baseInfo:"李菲 | 药明康德新药开发有限公司 | 资深化学家"
			            	 	},
			             	topicContent:"做一周了试了三个体系，铜/2价耙/零价耙，磷配体，一四二氧六环/DMF   N2 保护做出来打谱对不上，积峰14个未知化合物。再做不出来没法交代了，要跪在原料的道路。"+
			             					"，铜/2价耙/零价耙，磷配体，一四二氧六环/DMF   N2 保护做出来打谱对不上，积峰14个未知化合物。再做不出来没法交代了，要跪在原料的道路。"+
			             					"，铜/2价耙/零价耙，磷配体，一四二氧六环/DMF   N2 保护做出来打谱对不...",
			             	commentListsExpandAble : true,
			             	commentLists:[{	id: 1,
							    				commentAuthor:{
							            	 		img : "../../images/selfPicture/1.png",
							            	 		baseInfo:"李菲 | 药明康德新药开发有限公司 | 资深化学家"
							            	 		},
							    	        	 	commentContent:"亲测可以",
							    				},{	id: 2,
							    					commentAuthor:{
							    	        	 		img : "../../images/selfPicture/3.jpg",
							    	        	 		baseInfo:"静兰 | 药明康德新药开发有限公司 | 资深化学家"
							    	        	 		},
							    		        	 	commentContent:"经过高温更好",
							    					},{	id: 3,
							    						commentAuthor:{
							    		        	 		img : "../../images/selfPicture/2.png",
							    		        	 		baseInfo:"汪健 | 药明康德新药开发有限公司 | 资深化学家"
							    		        	 		},
							    			        	 	commentContent:"亲测可以",
							    						}]
			             },
			             {	id: 3,
			            	 	topicHeader:"几个化学合成的问题",
			            	 	topicAuthor:{
			            	 		img : "../../images/selfPicture/3.jpg",
			            	 		baseInfo:"汪健 | 药明康德新药开发有限公司 | 资深化学家"
			            	 	},
			             	topicContent:"做一周了试了三个体系，铜/2价耙/零价耙，磷配体，一四二氧六环/DMF   N2 保护做出来打谱对不上，积峰14个未知化合物。再做不出来没法交代了，要跪在原料的道路。"+
			             					"，铜/2价耙/零价耙，磷配体，一四二氧六环/DMF   N2 保护做出来打谱对不上，积峰14个未知化合物。再做不出来没法交代了，要跪在原料的道路。"+
			             					"，铜/2价耙/零价耙，磷配体，一四二氧六环/DMF   N2 保护做出来打谱对不...",
			             	commentListsExpandAble : true,
			                 commentLists:[{	id: 1,
							    				commentAuthor:{
							            	 		img : "../../images/selfPicture/1.png",
							            	 		baseInfo:"李菲 | 药明康德新药开发有限公司 | 资深化学家"
							            	 		},
							    	        	 	commentContent:"亲测可以",
							    				},{	id: 2,
							    					commentAuthor:{
							    	        	 		img : "../../images/selfPicture/3.jpg",
							    	        	 		baseInfo:"静兰 | 药明康德新药开发有限公司 | 资深化学家"
							    	        	 		},
							    		        	 	commentContent:"经过高温更好",
							    					},{	id: 3,
							    						commentAuthor:{
							    		        	 		img : "../../images/selfPicture/2.png",
							    		        	 		baseInfo:"汪健 | 药明康德新药开发有限公司 | 资深化学家"
							    		        	 		},
							    			        	 	commentContent:"亲测可以",
							    						}]			
			             },
			             {	id: 4,
			            	 	topicHeader:"C-N欧联有没有可能不用陪体呢?",
			            	 	topicAuthor:{
			            	 		img : "../../images/selfPicture/2.png",
			            	 		baseInfo:"陈雯婷 | 药明康德新药开发有限公司 | 资深化学家"
			            	 	},
			             	topicContent:"做一周了试了三个体系，铜/2价耙/零价耙，磷配体，一四二氧六环/DMF   N2 保护做出来打谱对不上，积峰14个未知化合物。再做不出来没法交代了，要跪在原料的道路。"+
			             					"，铜/2价耙/零价耙，磷配体，一四二氧六环/DMF   N2 保护做出来打谱对不上，积峰14个未知化合物。再做不出来没法交代了，要跪在原料的道路。"+
			             					"，铜/2价耙/零价耙，磷配体，一四二氧六环/DMF   N2 保护做出来打谱对不...",
			             	commentListsExpandAble : true,
			             	commentLists:[{	id: 1,
						    				commentAuthor:{
						            	 		img : "../../images/selfPicture/1.png",
						            	 		baseInfo:"李菲 | 药明康德新药开发有限公司 | 资深化学家"
						            	 		},
						    	        	 	commentContent:"亲测可以",
						    				},{	id: 2,
						    					commentAuthor:{
						    	        	 		img : "../../images/selfPicture/3.jpg",
						    	        	 		baseInfo:"静兰 | 药明康德新药开发有限公司 | 资深化学家"
						    	        	 		},
						    		        	 	commentContent:"经过高温更好",
						    					},{	id: 3,
						    						commentAuthor:{
						    		        	 		img : "../../images/selfPicture/2.png",
						    		        	 		baseInfo:"汪健 | 药明康德新药开发有限公司 | 资深化学家"
						    		        	 		},
						    			        	 	commentContent:"亲测可以",
						    						}]
			             },
			             {	id: 5,
			            	 	topicHeader:"如何提高合成效率?",
			            	 	topicAuthor:{
			            	 		img : "../../images/selfPicture/1.png",
			            	 		baseInfo:"李菲 | 药明康德新药开发有限公司 | 资深化学家"
			            	 	},
			             	topicContent:"做一周了试了三个体系，铜/2价耙/零价耙，磷配体，一四二氧六环/DMF   N2 保护做出来打谱对不上，积峰14个未知化合物。再做不出来没法交代了，要跪在原料的道路。"+
			             					"，铜/2价耙/零价耙，磷配体，一四二氧六环/DMF   N2 保护做出来打谱对不上，积峰14个未知化合物。再做不出来没法交代了，要跪在原料的道路。"+
			             					"，铜/2价耙/零价耙，磷配体，一四二氧六环/DMF   N2 保护做出来打谱对不...",
			             	commentListsExpandAble : true,
			             	commentLists:[{	id: 1,
							    				commentAuthor:{
							            	 		img : "../../images/selfPicture/1.png",
							            	 		baseInfo:"李菲 | 药明康德新药开发有限公司 | 资深化学家"
							            	 		},
							    	        	 	commentContent:"亲测可以",
							    				},{	id: 2,
							    					commentAuthor:{
							    	        	 		img : "../../images/selfPicture/3.jpg",
							    	        	 		baseInfo:"静兰 | 药明康德新药开发有限公司 | 资深化学家"
							    	        	 		},
							    		        	 	commentContent:"经过高温更好",
							    					},{	id: 3,
							    						commentAuthor:{
							    		        	 		img : "../../images/selfPicture/2.png",
							    		        	 		baseInfo:"汪健 | 药明康德新药开发有限公司 | 资深化学家"
							    		        	 		},
							    			        	 	commentContent:"亲测可以",
							    						}]
			             },
			             {	id: 6,
			            	 	topicHeader:"几个化学合成的问题",
			            	 	topicAuthor:{
			            	 		img : "../../images/selfPicture/3.jpg",
			            	 		baseInfo:"汪健 | 药明康德新药开发有限公司 | 资深化学家"
			            	 	},
			             	topicContent:"做一周了试了三个体系，铜/2价耙/零价耙，磷配体，一四二氧六环/DMF   N2 保护做出来打谱对不上，积峰14个未知化合物。再做不出来没法交代了，要跪在原料的道路。"+
			             					"，铜/2价耙/零价耙，磷配体，一四二氧六环/DMF   N2 保护做出来打谱对不上，积峰14个未知化合物。再做不出来没法交代了，要跪在原料的道路。"+
			             					"，铜/2价耙/零价耙，磷配体，一四二氧六环/DMF   N2 保护做出来打谱对不...",
			             	commentListsExpandAble : true,
			                 commentLists:[{	id: 1,
							    				commentAuthor:{
							            	 		img : "../../images/selfPicture/1.png",
							            	 		baseInfo:"李菲 | 药明康德新药开发有限公司 | 资深化学家"
							            	 		},
							    	        	 	commentContent:"亲测可以",
							    				},{	id: 2,
							    					commentAuthor:{
							    	        	 		img : "../../images/selfPicture/3.jpg",
							    	        	 		baseInfo:"静兰 | 药明康德新药开发有限公司 | 资深化学家"
							    	        	 		},
							    		        	 	commentContent:"经过高温更好",
							    					},{	id: 3,
							    						commentAuthor:{
							    		        	 		img : "../../images/selfPicture/2.png",
							    		        	 		baseInfo:"汪健 | 药明康德新药开发有限公司 | 资深化学家"
							    		        	 		},
							    			        	 	commentContent:"亲测可以",
							    						}]			
			             }
			              
			]
			scope.commentLists = [ 
			                      
			       ]
			scope.expandTopicContent = function(topic){
				scope.expandTopicContentAble = false;
				topic.topicContent = _.map([1, 2, 3], function(num){ return topic.topicContent + topic.topicContent });
			}
			scope.collapseTopicContent = function(topic){
				scope.expandTopicContentAble = true;
				topic.topicContent = topic.topicContent.toString().substr(0,89);
			}
			
			scope.expandCommentLists = function(topic){
				var popverDom = element.find(".topic-comment-lists .popover.topic-comment-list-" + topic.id);
				if(topic.commentListsExpandAble){
					popverDom.css("display","block");
				}else{
					popverDom.css("display","none");
				}
				topic.commentListsExpandAble = !topic.commentListsExpandAble;
				
				
			}
			
			console.log("wkTopic->directives->topicList: topic: " + scope.topic);
		}
	};

});