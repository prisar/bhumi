var ktsr="";
 var kt1="";
 var kt2="";
//  debugger;
 var baseUrl = "http://192.168.0.181:8080/";
 $(function(){
	 var fl_1=0, fl_2=0; 
	 var map="";
     var gml_layer="",wkt_layer="";
     var selectControl="";       
     $( document ).ready(function() {        	    
        	  var sheetNo= getParam( 'lstSheetNo' );
        	 if(sheetNo=='-1'){
        		  jAlert("Please Select Sheet No.",'Alert');
        	  }else{        		  
        	//   $('#map').html(""); 
        	  $('#location').html("");
        	  var w=screen.width;
        	  var h=screen.height;	        	  
        	  var tdHight=(parseInt(h)-205)+"px";        	
        	  document.getElementById('map').style.height= tdHight;
              document.getElementById('map').style.width= '99.7%';
             
            //   $(".transparentCover").show();
           	//   $(".loading").show();	
        	  
        	  
        	
        	  OpenLayers.ImgPath = "themes/openlayers_themes-master/dark/";
              
        	  var graticuleCtl = new OpenLayers.Control.Graticule({
                  layerName:"Grids",
                  numPoints: 2,
                  labelled: false
              });
              var options = {                    
                  minResolution: "auto",
                  maxResolution: 156543.0339,
                  minExtent: new OpenLayers.Bounds(-1, -1, 1, 1),                    
                  maxExtent: new OpenLayers.Bounds(this.maxExtent),
                  controls: [
                     // graticuleCtl,
                     // new OpenLayers.Control.LayerSwitcher(),
                      //new OpenLayers.Control.PanZoomBar(),
                      new OpenLayers.Control.Zoom(),
                      new OpenLayers.Control.Navigation({
                          dragPanOptions: {
                              enableKinetic: true
                          }
                      }),
                      new OpenLayers.Control.ScaleLine(),
                      new OpenLayers.Control.MousePosition()                        
                    ],
                  numZoomLevels: 21,
                  displayProjection: new OpenLayers.Projection("EPSG:4015"),
                  units: 'm',
                  projection: new OpenLayers.Projection("EPSG:900913")
              };                
              map = new OpenLayers.Map('map', options);

              var json_mouzaUrl =  baseUrl + "sheetMap_populateLayerData?lstSheetNo="+sheetNo;
              var json_centroidUrl =  baseUrl + "sheetMap_populateCentroidData?lstSheetNo="+sheetNo;
              
              var wktReader = new OpenLayers.Format.WKT();
              
              var protocol=new OpenLayers.Protocol.HTTP({
                  url: json_mouzaUrl,
                  readWithPOST: true,
                  format: new OpenLayers.Format.JSON({
                	  read: function(jsonp) {
                          var jsonformatData=$.parseJSON(jsonp);
      					var features = [];
      					$.each(jsonformatData.features, function(i, item) {
      						console.log(item.plotno);
      						var feature = wktReader.read(item.polygon);
    						feature.attributes = {plot: item.plotno,area:item.plotArea};
    						features.push(feature);
      					});
      					alert(data.newPlotList);
      					return features;
      				}  
      			})
              
              });

              var protocolCentroid=new OpenLayers.Protocol.HTTP({
                  url: json_centroidUrl,
                  readWithPOST: true,
                  format: new OpenLayers.Format.JSON({
                      read: function(jsonp) {
                          var jsonformatData=$.parseJSON(jsonp);
      					var features = [];
      					$.each(jsonformatData.features, function(i, item) {
      						//console.log(item.plotno);
      						var feature = wktReader.read(item.point);
      						feature.attributes = { plot: item.plotno,rend_plot:item.rend_plotno};
      						features.push(feature);
      					});
      					return features;
      				}
      			})
              });
              
              wkt_layer= new OpenLayers.Layer.Vector("Polygon Map", {
                  isBaseLayer  : true,
                  styleMap: new OpenLayers.StyleMap({                      
                      "default": new OpenLayers.Style({
                          pointRadius: 15,
                          fillColor: "#ffcc66",
                          fillOpacity: 0.8,
                          strokeColor: "#cc6633",
                          strokeWidth: 1,
                          strokeOpacity: 0.8,
                          fontColor: 'blue',
                          fontSize: '\${getFontSize}',
                          fontFamily: 'BNB-TTBidisha',
                          fontWeight: 'bold'
                      },
                      {context: {
                         
                          getFontSize: function(feature){
                              var defaultSize = feature.layer.map.getZoom();
                              if(feature.layer.map.getZoom()-14 > 14 ){
                                  var zoomLevel = feature.layer.map.getZoom() - 14;
                                  defaultSize = defaultSize + zoomLevel + 14;
                              }
                              return defaultSize;
                          }
                      }
                  }),
                      "select": new OpenLayers.Style({
                    	  fillColor: "#8aeeef",
                          strokeColor: "#32a8a9"
                          //labelYOffset:13,
                          //label:'\${getselectedLabel}'
                      },
    					{context:{
    							getselectedLabel:function(feature){
    								if(feature.layer.map.getZoom() >= 14) {
    	                                  return feature.attributes.plot;
    	                              } else {
    	                                  return "";
    	                              }
    								}
    						}
      					}

                      )                 
                  }),                    
                  displayProjection: new OpenLayers.Projection("EPSG:4015"),
                  projection: new OpenLayers.Projection("EPSG:900913"),
                  strategies: [
                      new OpenLayers.Strategy.Fixed()
                  ],
                  protocol: protocol
              });                
              
              
              gml_layer = new OpenLayers.Layer.Vector("Centroid Map", {
                  isBaseLayer  : false,
                  styleMap: new OpenLayers.StyleMap({                  
                      "default": new OpenLayers.Style({
                          pointRadius: 0,
                          fillColor: "#ffcc66",
                          fillOpacity: 0.8,
                          strokeColor: "#cc6633",
                          strokeWidth: 1,
                          strokeOpacity: 0.8 ,
                          label : '\${getLabel}',
                          fontColor: 'blue',
                          fontSize: '\${getFontSizeCen}',
                          fontFamily: 'BNB-TTBidisha',
                          fontWeight: 'bold',
                          labelXOffset: "\${getLOffsetX}",
                          labelYOffset: "\${getLOffsetY}"
                      },
                          {context: {
                              getLabel: function(feature) {
//                                alert(feature.layer.map.getZoom());
                                  if(feature.layer.map.getZoom() >= 15) {
                                      return feature.attributes.rend_plot;
                                  } else {
                                      return "";
                                  }
                              },
                              getLOffsetX: function(feature) {                               
                            	  if(feature.layer.map.getZoom() >= 26) {
                                      return "20";
                                  }else if(feature.layer.map.getZoom() >= 23) {
                                      return "16";
                                  } else if(feature.layer.map.getZoom() >= 20) {
                                	  return "14";
                                  }else if(feature.layer.map.getZoom() >= 17) {
                                	  return "10";
                                  }else {
                                      return "3";
                                  }
                              },
                              getLOffsetY: function(feature) {                                
                            	  if(feature.layer.map.getZoom() >= 23) {
                                      return "9";
                                  } else if(feature.layer.map.getZoom() >= 20) {
                                	  return "6";
                                  }else if(feature.layer.map.getZoom() >= 17) {
                                	  return "4";
                                  }else {
                                      return "1";
                                  }
                              },
                              getFontSizeCen: function(feature){
//                               alert(feature.layer.map.getZoom());
                                  var defaultSize = feature.layer.map.getZoom();
                                  if(feature.layer.map.getZoom()> 15 ){
                                      var zoomLevel = (feature.layer.map.getZoom()-15)*6;
                                      defaultSize = defaultSize + zoomLevel;
                                     // alert(defaultSize);
                                  }
                                 // console.log("zoomLevel="+zoomLevel);  
                               // console.log("Size="+defaultSize);
                                 
                                  return defaultSize-5;
                              }
                          }
                      })                 
                  }),                    
                  displayProjection: new OpenLayers.Projection("EPSG:4015"),
                  projection: new OpenLayers.Projection("EPSG:900913"),
                  zoom: 20,
                  strategies: [
                      new OpenLayers.Strategy.Fixed()
                 ],
                 rendererOptions: {zIndexing: true},
                  protocol: protocolCentroid
              });                

              map.addLayer(wkt_layer);  

              map.addLayer(gml_layer);
            
              map.zoomToMaxExtent();
            
              map.zoomToExtent(new OpenLayers.Bounds(71.369140625, 96.82347106933594, 418.8914794921875, 623.1217041015625));
            
                     
              selectControl = new OpenLayers.Control.SelectFeature(
                      [wkt_layer, gml_layer],
                      {
                          clickout: true, toggle: false,
                          multiple: false, hover: false,
                          toggleKey: "ctrlKey", // ctrl key removes from selection
                          multipleKey: "shiftKey" // shift key adds to selection
                      }
                  );

              map.addControl(selectControl);   
              selectControl.activate();   

              if (typeof(selectControl.handlers) != "undefined") { // OL 2.7
                	selectControl.handlers.feature.stopDown = false;
                } else if (typeof(selectControl.handler) != "undefined") { // OL < 2.7
                	selectControl.handler.stopDown = false; 
                	selectControl.handler.stopUp = false; 
                }
              
              wkt_layer.events.on({                  
                  featureselected: function(event) {                 	 
                 	     var  x, y;
 		        	     var feature = event.feature;
 		        	     featureParticularPlot= event.feature;
 		        	     //console.log(feature);
 		        	     x=feature.geometry.getCentroid().x;
 		        	     y=feature.geometry.getCentroid().y;                        
 		        	     var centroid=map.getPixelFromLonLat(new OpenLayers.LonLat(x,y)) ;                                                                         
 		        	     var plotno  = feature.attributes.plot;   
 		        	     selectControl.unselectAll();
 		        	    if(fl_1 > 0) {
//	 	               	      Set the previously selected feature to default style
	 	               	        var defstyle = OpenLayers.Util.applyDefaults(defstyle, OpenLayers.Feature.Vector.style['default']);
	 	               	        defstyle.fill = true;
	 	               	        defstyle.pointRadius=15;
	 	               	        defstyle.fillColor = "#ffcc66";                    
	 	               	        defstyle.fillOpacity = 0.8;
	 	               	        defstyle.strokeColor="#cc6633";
	 	               	        defstyle.strokeWidth=1;
	 	               	        defstyle.strokeOpacity=0.8;
	 	               	        wkt_layer.features[fl_1].style = defstyle;
	 	               	        wkt_layer.features[fl_1].style.label=wkt_layer.features[fl_1].attributes.REND_PLTNO;
	 	               	        wkt_layer.redraw();                    
	 	               	    }						
 	                
 		        	     switch(plotno.toString().length){
 		        	            case 1:
 		        	                plot_no="    "+plotno;
 		        	                break;
 		        	           case 2:
 		            	           plot_no="   "+plotno;
 		        	               break;
 		        	           case 3:
 		        	     	       plot_no="  "+plotno;
 		        	               break;
 		        	           case 4:
         	                       plot_no=" "+plotno;
         	                       break;
         	                   case 5:
         	                       plot_no=plotno;
         	                       break;     
         	                   }

 		        	     
 	     	             var dbfarea = feature.attributes.area * .003874993; // Areas in DBF are in sq.mm, convert it to acres
 	        	         var calarea = feature.geometry.getArea() * .003874993; // Calculated area is in sq.mm, conver it to acres
//         	                        Acre -> Area in sq.mm * 0.003874993, Sq.Feet -> Area in sq.mm * 45559.66, Katha - > Area in sq.mm 60                   
//         	                        alert(centroid.x+30 + ' ' + centroid.y+20); 

 	        	        $('#hdnPlotNo').val(plotno);
 						//console.log(featureParticularPlot);
 	        	       
 						var tf5PlotArea=0;
 						 var title ="";						                                                  
                  		}
         	          });
              
                							
              var response = protocol.read({          	   
          	    callback: function(resp){   						
                    	var responseStatus=resp.priv.status;
  						console.log(resp.priv.status);
  				// 		if(responseStatus>=200){              
  				// 			 $(".transparentCover").hide();
  				// 			 $(".loading").hide();			          
				//  }						
           	    }
             
       	     }); 
             
          }
          }); 
          
    $("#btnSearch").click(function(event) {
             // alert('hi');
                var search_plot=$("#txtPlotNo").val(); 
                if(search_plot==''){
                	jAlert("Please Enter The Plot No...",'Alert');
                }else{
                $('#location').empty();                
        	   if(fl_1 >= 0) {
        	        var defstyle = OpenLayers.Util.applyDefaults(defstyle, OpenLayers.Feature.Vector.style['default']);
        	        defstyle.fill = true;
        	        defstyle.pointRadius=15;
        	        defstyle.fillColor = "#ffcc66";                    
        	        defstyle.fillOpacity = 0.8;
        	        defstyle.strokeColor="#cc6633";
        	        defstyle.strokeWidth=1;
        	        defstyle.strokeOpacity=0.8;
        	        wkt_layer.features[fl_1].style = defstyle;
        	        wkt_layer.features[fl_1].style.label=gml_layer.features[fl_1].attributes.REND_PLTNO;
        	        wkt_layer.redraw();                    
        	    }
        	    selectControl.unselectAll(); // Un selected all previously selected features        	    
        	    for(var fl = 0; fl < wkt_layer.features.length; fl++) {
        	        if (parseInt(wkt_layer.features[fl].attributes.plot) == parseInt(search_plot)) {        	        	
        	        	wkt_layer.features[fl].style = new OpenLayers.Style();
        	        	wkt_layer.features[fl].style.pointRadius=15;
        	        	wkt_layer.features[fl].style.fillColor = "#8aeeef";
        	        	wkt_layer.features[fl].style.fillOpacity =0.8;
        	        	wkt_layer.features[fl].style.strokeColor="#cc6633";
        	        	wkt_layer.features[fl].style.strokeWidth=1;
        	        	wkt_layer.features[fl].style.strokeOpacity=0.8;
//        	          selectControl.select(gml_layer.features[fl]);
        	            wkt_layer.features[fl].style.label=gml_layer.features[fl].attributes.REND_PLTNO;
        	            var xx = wkt_layer.features[fl].geometry.getCentroid().x;
        	            var yy = wkt_layer.features[fl].geometry.getCentroid().y;
        	            map.setCenter(new OpenLayers.LonLat(xx, yy), 17); // Zoom Factor = 14
        	            wkt_layer.redraw();
        	            fl_1=fl;
        	            return;       
        	        }                   
        	    }                	   
        	    jAlert("Plot Not Found..",'Alert');
                }    	
        	}); 

    $("#btnResetZoom").click(function(event) { 
    	var midpoint=(wkt_layer.features.length)/2;
	     midpoint=parseInt(midpoint);	      
	     var xx = wkt_layer.features[midpoint].geometry.getCentroid().x;
         var yy = wkt_layer.features[midpoint].geometry.getCentroid().y;         
        map.setCenter(new OpenLayers.LonLat(xx,yy), 15); // Zoom Factor = 14
        wkt_layer.redraw(); 	    
  	}); 
    
   });
 

 
 function getParam( name )
 {
  name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
  var regexS = "[\\?&]"+name+"=([^&#]*)";
  var regex = new RegExp( regexS );
  var results = regex.exec( window.location.href );
  if( results == null )
   return "";
 else
  return results[1];
 }
 
 function plotInfoReportShow(plot){
		var mouzaCode=$('#lstMouzaCode').val();	
	 	var sheetNo=$('#lstSheetNo').val() || 1;	
	  	var mapType=$('#lstMapType').val();		   
  	     var w=screen.width;
  	     var h=screen.height;
  	     sw=(parseInt(w)/2)-70;
  	     sh=(parseInt(h)/2)-70;        	    
  	     dialogOpts1 = {
  	            //   modal: true,
  	               position: [sw, sh],
  	               resizable: false,
  	               height:160,
  	               caption:false,    
  	               closeOnEscape: false,
  	               open: function() {
  	                   $('#imageShow').dialog('option', 'title', " Please Wait........");      
  	                   $(this).siblings('.ui-dialog-titlebar').remove();          
  	                   
  	               },
  	              
  	               width: 160
  	           };
  	       $('#imageShow').dialog(dialogOpts1);
		 $.ajax({
      	    url:"PdfForPlotInfo?lstMouzaCode="+mouzaCode+"&lstSheetNo="+sheetNo+"&lstMapType="+mapType+"&plotNo="+plot,
      	    type:"POST",
      	    dataType:"json",
      	    success:function(data){        	    	
      	    	$( '#imageShow' ).dialog( "close" );  
      	    	if(data.result=='success'){
      	    	  $.getJSON("callViewReportAction.action",function(data){ 		
      	   			if(data.reportMsg=="true"){      	   				
      	   				 window.open(contextPath+"/allHtmlReportViewNavigation.action",'_blank');
      	   			     window.focus();
      	   			}     	   						
      	   		  });
      	    	}else{
      	    		jAlert("Cannot Generate Plot Information Report For This Plot No.!!!!",'Error');
      	    	}
      	      }
			});	
	}
 
 
 function khatianInfoShow(plot){
	   var w=screen.width;
	     var h=screen.height;
	     sw=(parseInt(w)/2)+200;
	     sh=(parseInt(h)/2)-300;        	    
	     dialogOpts = {
	             //  modal: true,
	               position: [sw, sh],
	               resizable: false,
	               height:235,
	               //caption:false,        	                                 
	               open: function() {
	                   $('#popupwindow').dialog('option', 'title', " Plot Details.....");      
	                   //$(this).siblings('.ui-dialog-titlebar').remove();          
	                   
	               },
	               buttons: { 
	            	   
	            	  "Khatian Report ": function() {                  			
	            		  khatianReportPrint();
	            	   },
	            	   "Exit ": function() {                 			
	            		   $( '#popupwindow').dialog( "close" );
	            	   }
                 },
	              
	               width: 440
	           };
	
	     $.publish('reloadPortfolioGrid');	  
	     $('#popupwindow').dialog(dialogOpts);	    	
 }
 
 
 function khatianReportPrint(){	 
	   if(kt1=="" && kt2==""){
		   jAlert("Please Select Any Row.......",'Alert');
	   }else{
	    var mouzaCode=$('#lstMouzaCode').val();	
	 	var sheetNo=$('#lstSheetNo').val();	
	  	var mapType=$('#lstMapType').val();		   
	     var w=screen.width;
	     var h=screen.height;
	     sw=(parseInt(w)/2)-70;
	     sh=(parseInt(h)/2)-70;        	    
	     dialogOpts1 = {
	               //modal: true,
	               position: [sw, sh],
	               resizable: false,
	               height:160,
	               caption:false, 
	               closeOnEscape: false,
	               open: function() {
	                   $('#imageShow').dialog('option', 'title', " Please Wait........");      
	                   $(this).siblings('.ui-dialog-titlebar').remove();                  
	               },
	              
	               width: 160
	           };
	       $('#imageShow').dialog(dialogOpts1);
		 $.ajax({
   	    url:"PdfForKhatian?lstMouzaCode="+mouzaCode+"&lstSheetNo="+sheetNo+"&lstMapType="+mapType+"&kt1="+kt1+"&kt2="+kt2,
   	    type:"POST",
   	    dataType:"json",
   	    success:function(data){  
   	    	$( '#imageShow' ).dialog( "close" );  
   	    	if(data.result=='success'){
   	    		$.getJSON("callViewReportAction.action",function(data){ 		
      	   			if(data.reportMsg=="true"){      	   				
      	   				 window.open(contextPath+"/allHtmlReportViewNavigation.action",'_blank');
      	   			     window.focus();
      	   			}     	   						
      	   		  });
   	    	}else{
   	    		jAlert("Cannot Generate Khatian Report For This Khatian No.!!!!",'Error');
   	    	}
   	      }
			}); 
		 kt1="";
		 kt2="";
	   }
	 
 }

 
