package interfaceenginev2.display.component;

import interfaceenginev2.NewDataBaseLayer;
import interfaceenginev2.display.DisplayEngine;
import interfaceenginev2.display.StyleEngine;

import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import comv2.aunwesha.lfutil.GenericUtil;
import comv2.aunwesha.lfutil.LFResource;

public class DBgrid {

	
	public static String createLayout(String interface_id, String child_id, Document doc, String height,Element layoutelement, Element itemmain,
			 String position, String x, String y, String width,String layout, String style,String themeId,String partclass,Element itemhead, Element itembody,StyleEngine styleEngine) {
		boolean isBootstrap=GenericUtil.convertStringToBoolean(LFResource.DISPLAY_ENGINE.retriveResourceValue("bootstrap"));
		String gridstring = "";

		System.out.println(" //////////////////////////////////////////////////DBGRId////////////////////////////////");
		Element headelement = null;
		Element bodyelement = null;
		NodeList headelementn = doc.getElementsByTagName("head");
		for (int i = 0; i < headelementn.getLength(); i++) {
			headelement = (Element) headelementn.item(i);
		}
		NodeList bodyelementn = doc.getElementsByTagName("body");
		for (int i = 0; i < bodyelementn.getLength(); i++) {
			bodyelement = (Element) bodyelementn.item(i);
		}

		String checkInterfaceType = NewDataBaseLayer.GetInterfaceType(interface_id);
		/*
		 * This function is return interface type ( Interface or
		 * InterfaceFragment )
		 */

		// String userparameterstring="";
		String loadurl = NewDataBaseLayer.getLoadURL(interface_id, child_id);
		String editurl = NewDataBaseLayer.getEditURL(interface_id, child_id);
		String Caption = NewDataBaseLayer.getCaption(interface_id, child_id);
		// String Sortname=NewDataBaseLayer.getSortname(interface_id,child_id);
		String SortOrder = NewDataBaseLayer.getSortOrder(interface_id, child_id);
		String navbar = NewDataBaseLayer.getNavBar(interface_id, child_id);
		String getmultiselect = NewDataBaseLayer.getmultiselect(interface_id, child_id);
		String getmultiboxonly = NewDataBaseLayer.getmultiboxonly(interface_id, child_id);
		String resetsearchonclose = NewDataBaseLayer.resetSearchOnClose(interface_id, child_id);
		String multiplesearch = NewDataBaseLayer.multiplesearch(interface_id, child_id);
		String customeditbutton = NewDataBaseLayer.GetCustomEditButton(interface_id, child_id);
		// String griddata=NewDataBaseLayer.GetGridData(interface_id,child_id);
		String griddatatype = NewDataBaseLayer.GetGridDataType(interface_id, child_id);
		String datamanipulation = "";
		if (customeditbutton == null || customeditbutton.equals("")) {
			customeditbutton = DisplayEngine.getApplicationDefaultValue(interface_id, "DBgrid", "CustomEditButton");
		}
		Vector<String> getRowNumandList = NewDataBaseLayer.getRowNumandList(interface_id, child_id);
		String rowNum = "";
		String rowList = "";
		for (int x1 = 0; x1 < getRowNumandList.size(); x1 = x1 + 2) {
			rowNum =  getRowNumandList.elementAt(x1);
			rowList =  getRowNumandList.elementAt(x1 + 1);
		}
		if (GenericUtil.isEmptyString(rowNum)) {
			rowNum = "6";
		}
		if (GenericUtil.isEmptyString(rowList)) {
			rowList = "2,6,12,15";
		}

		if (GenericUtil.isEmptyString(getmultiselect)) {
			getmultiselect = "\n multiselect:false,";
		} else {
			getmultiselect = "\n multiselect:true,";
		}

		if (GenericUtil.isEmptyString(getmultiboxonly)) {
			getmultiboxonly = "\n multiboxonly:false,";
		} else {
			getmultiboxonly = "\n multiboxonly:true,";
		}

		if (GenericUtil.isEmptyString(resetsearchonclose) || resetsearchonclose.equals("true")) {
			resetsearchonclose = " \n onClose:function ResetOnCloseSearch(){resetGrid(\"" + interface_id + "\",\"" + child_id + "\");}";
		} else if (resetsearchonclose.equals("false")) {
			resetsearchonclose = "";
		}
		if (GenericUtil.hasString(multiplesearch) && multiplesearch.equals("true")) {
			multiplesearch = " \n ,multipleSearch:true";
		}

		if (GenericUtil.isEmptyString(navbar)) {
			navbar = "true";
		}
		if (GenericUtil.isEmptyString(loadurl)) {
			loadurl = "./interfaceenginev2.xmlcreator?interface_id=" + interface_id + "&part_id=" + child_id;
		}

		if (GenericUtil.isEmptyString(griddatatype)) {
			griddatatype = "xml";
		}

		if (GenericUtil.hasString(griddatatype) && griddatatype.equals("local")) {
			datamanipulation = "  \n datatype: \"" + griddatatype + "\",";
			// "  \n data: eval("+griddata+"),";
		} else {
			datamanipulation = "  \n url: '" + loadurl + "'," + "  \n datatype: \"" + griddatatype + "\",";
		}

		String bootstrapTheme = "";
		if(isBootstrap){
			bootstrapTheme = "  \n styleUI: 'Bootstrap',";
		}
		//String bootstrapTheme = "";

		String s = " function generateGrid(){" + "  \n jQuery(\"#" + child_id + "\").jqGrid({" + datamanipulation + bootstrapTheme;

		String s1 = "\ncolNames:[";
		Vector<String> colnames = NewDataBaseLayer.getColnames(child_id, interface_id);
		s1 = s1 + " ' " +  colnames.elementAt(0) + " ' ";
		for (int i = 1; i < colnames.size(); i = i + 1) {
			String colname =  colnames.elementAt(i);
			s1 = s1 + ",'" + colname + "'";
		}
		String s4 = "";
		String s3 = " \ncolModel:[";
		Vector<String> colmodel = NewDataBaseLayer.getColModel(child_id, interface_id);
		for (int i = 0; i < colmodel.size(); i = i + 17) {
			String popupselectorstring = "";
			String colname = colmodel.elementAt(i);
			String colindex = colmodel.elementAt(i + 1);
			String col_width = colmodel.elementAt(i + 2);
			String col_editable = colmodel.elementAt(i + 3);
			String col_hidden = colmodel.elementAt(i + 4);
			String key_value = colmodel.elementAt(i + 5);
			String required = colmodel.elementAt(i + 6);
			String email = colmodel.elementAt(i + 11);
			String number = colmodel.elementAt(i + 12);
			String custom = colmodel.elementAt(i + 13);
			String custom_func = colmodel.elementAt(i + 14);
			String default_type = colmodel.elementAt(i + 15);
			String default_value = colmodel.elementAt(i + 16);

			String formoption = "";

			if (GenericUtil.isEmptyString(required) || required.equals("false")) {
				required = "required:false";
			} else {
				required = "required:true";
				formoption = "formoptions:{elmprefix:'<font color=\"red\">*</font>'},";
				// formoption="formoptions:{elmprefix:'*'},";
			}

			if (GenericUtil.isEmptyString(custom) || custom.equals("false")) {
				custom = "";
			} else {
				custom = ",custom:true";
			}

			if (GenericUtil.isEmptyString(custom_func)) {
				custom_func = "";
			} else {
				custom_func = ",custom_func:" + custom_func;
			}

			if (GenericUtil.isEmptyString(email) || email.equals("false")) {
				email = ",email:false";
			} else {
				email = ",email:true";
			}

			if (GenericUtil.isEmptyString(number) || number.equals("false")) {
				number = ",number:false";
			} else {
				number = ",number:true";
			}

			System.out.println(".................DEFAUL VALUE......" + default_type + ".........." + default_value);

			if (GenericUtil.isEmptyString(default_type)) {
				default_value = "";
			}

			else if (default_type.equals("string")) {
				default_value = ",defaultValue:'" + default_value + "'";
			}

			else if (default_type.equals("function")) {
				default_value = ",defaultValue:" + default_value;
			}

			String minval =  colmodel.elementAt(i + 7);
			if (GenericUtil.isEmptyString(minval)) {
				minval = "";
			} else {
				minval = ",minValue:" + minval;
			}
			String maxval =  colmodel.elementAt(i + 8);
			if (GenericUtil.isEmptyString(maxval)) {
				maxval = "";
			} else {
				maxval = ",maxValue:" + maxval;
			}
			String editformat = "";
			String gridcolhidden =  colmodel.elementAt(i + 9);
			if (GenericUtil.isEmptyString(gridcolhidden)) {
				gridcolhidden = "";
			} else {
				gridcolhidden = ",edithidden:" + gridcolhidden;
			}
			String colinfluence =  colmodel.elementAt(i + 10);
			Vector<String> getEditOption = NewDataBaseLayer.getEditOption(child_id, interface_id, colname);
			for (int j = 0; j < getEditOption.size(); j = j + 7) {
				String type = getEditOption.elementAt(j);
				String size = getEditOption.elementAt(j + 1);
				String editrows = getEditOption.elementAt(j + 2);
				String editcols = getEditOption.elementAt(j + 3);
				String editdomaintype = getEditOption.elementAt(j + 4);
				String value = getEditOption.elementAt(j + 5);
				String multiple = getEditOption.elementAt(j + 6);
				if (GenericUtil.isEmptyString(multiple)) {
					multiple = "false";
				}

				else if (multiple.equals("true")) {
					editurl = "./interfaceenginev2.DBGridQueryEditorServletForMulti?interface_id=" + interface_id + "&part_id=" + child_id;
				}

				if (GenericUtil.isEmptyString(type) || type.equals("text")) {
					editformat = "edittype:\"text\",editoptions: {size:" + size + default_value + "}";
				} else if (type.equals("select")) {
					if (!colinfluence.equals("")) {

						popupselectorstring = ",dataEvents:[{ type: 'change',fn: create_drop }]";
						String populatedropdown = "function create_drop()" + "\n {" + "\n  var selectvalue=getValue(\"" + colname + "\");"
								+ "\n PortalEngine.ModifySelectLoadQuery(\"" + interface_id + "\",\"" + child_id + "\",\"" + colinfluence + "\",\""
								+ colname + "\",selectvalue,create_dropdown);" + "\n }" + "\n function create_dropdown(data){" + "\n  $(\"#"
								+ colinfluence + "\").html('<option value=\"\">Choose One</option>'+data);" + "\n  }";

						Element populatedrop = doc.createElement("script");
						populatedrop.setAttribute("type", "text/javascript");
						populatedrop.appendChild(doc.createTextNode(populatedropdown));

						if (checkInterfaceType.equals("InterfaceFragment")) /*
																			 * This
																			 * block
																			 * is
																			 * use
																			 * for
																			 * InterfaceFragment
																			 * Check
																			 */
						{
							bodyelement.appendChild(populatedrop);
						} else {
							headelement.appendChild(populatedrop);
						}
					}
					if (editdomaintype.equals("fixed")) {
						editformat = "edittype:\"select\",editoptions: {value:\"" + value + "\",multiple:" + multiple + popupselectorstring + "}";
					}
					if (editdomaintype.equals("query")) {
						editformat = "edittype:\"select\",editoptions: {value:\"0:Choose One\",multiple:" + multiple + ","
								+ "dataUrl: './interfaceenginev2.SelectDataProviderServlet?child_id=" + child_id + "&interface_id=" + interface_id
								+ "&colname=" + colname + "'" + popupselectorstring + "}";
					}

				}

				if (type.equals("password")) {
					editformat = "edittype:\"password\",editoptions: {size:\"" + size + "\"}";
				}
				if (type.equals("textarea")) {
					editformat = "edittype:\"textarea\",editoptions: {rows:\"" + editrows + "\",cols:\"" + editcols + default_value + "\"}";
				}
				if (type.equals("checkbox")) {
					editformat = "edittype:\"checkbox\",editoptions: {value:\"" + value + default_value + "\"}";
				}
				if (type.equals("date")) {
					editformat = "edittype:\"text\",editoptions: {size:" + size
							+ ",dataInit:function(el){ $(el).datepicker({dateFormat:'yy-mm-dd'}); }" + default_value + "}";
				}

			}
			if (i == (colmodel.size() - 17)) {
				s4 = s4 + "\n{name:'" + colname + "',index:'" + colindex + "'," + formoption + "editrules:{" + required + custom + custom_func
						+ email + number + minval + maxval + gridcolhidden + "},search:true,width:" + col_width + ",key:" + key_value + ", hidden:"
						+ col_hidden + ", editable:" + col_editable + "," + editformat + "}";

			} else {
				s4 = s4 + "\n{name:'" + colname + "',index:'" + colindex + "'," + formoption + "editrules:{" + required + custom + custom_func
						+ email + number + minval + maxval + gridcolhidden + "},search:true,width:" + col_width + ",key:" + key_value + ", hidden:"
						+ col_hidden + ", editable:" + col_editable + "," + editformat + "},";
			}
		}
		if (GenericUtil.isEmptyString(editurl)) {
			editurl = "./interfaceenginev2.DBGridQueryEditorServlet?interface_id=" + interface_id + "&part_id=" + child_id;
		}

		Vector<String> getkeyColumns = NewDataBaseLayer.getKeyColumnsValue(interface_id, child_id);
		String rowdatastring = "";
		String key_column = "";

		for (int k = 0; k < getkeyColumns.size(); k = k + 1) {
			String key_column_name =  getkeyColumns.elementAt(k);
			if (k == (getkeyColumns.size() - 1)) {
				rowdatastring = rowdatastring + key_column_name + ":" + "rowdat." + key_column_name + "";
				key_column = key_column + key_column_name;
			} else {
				rowdatastring = rowdatastring + key_column_name + ":" + "rowdat." + key_column_name + ",";
				key_column = key_column + key_column_name;
			}
		}

		String addnavbarexistcheck = "";
		String addnavbarexist = "";
		addnavbarexistcheck = NewDataBaseLayer.getAddnavbarexistcheck(interface_id, child_id);
		if (GenericUtil.isEmptyString(addnavbarexistcheck)) {
			addnavbarexist = ",add:false";
		} else {
			addnavbarexist = ",add:true";
		}
		//
		/*String addnavbar = "\n {height:300,width:500,reloadAfterSubmit:true,modal: true, closeAfterAdd: true, "
				+ "\n      afterSubmit : function(xhr,postdata)" + "\n         {" + "\n           var x=document.getElementById('" + child_id
				+ "postdatamessage');" + "\n           $.blockUI({ message: xhr.responseText });" + "\n           setTimeout($.unblockUI, 2000);"
				+ "\n           return [true,xhr];" + "\n	        }" + "\n  }";*/
		
		String addnavbar = "\n {height:300,width:500,reloadAfterSubmit:true,modal: true, closeAfterAdd: true,"
				+ "\n   afterSubmit : function(data,postdata,oper)" + "\n       {" + "\n           " +
						"var response = data.responseJSON;" + "\n    " +
				"       if (response && response.hasOwnProperty(\"error\")) {"+ "\n"+
							"if(response.error.length) {\n"+
							"	return [false,response.error ];\n"+
							"}\n"+
						"}\n"+
						"else{"+
						"if(response && response.hasOwnProperty(\"success\")){"+
							"$.blockUI({ message: response.success });"+
							"setTimeout($.unblockUI, 2000);"+
							"return [true,response.success ];\n}"+
						"else{"+
							"$.blockUI({ message: data.responseText });"+
							"setTimeout($.unblockUI, 2000);"+
							"return [true,data.responseText,\"\"];\n"+
						"}"+	
						
				" }" + "\n  }\n  }";

		String modifynavbarexistcheck = "";
		String modifynavbarexist = "";
		modifynavbarexistcheck = NewDataBaseLayer.getModifynavbarexistcheck(interface_id, child_id);
		if (GenericUtil.isEmptyString(modifynavbarexistcheck)) {
			modifynavbarexist = "edit:false";
		} else {
			modifynavbarexist = "edit:true";
		}
		//
		/*String modifynavbar = "\n {height:300,width:500,reloadAfterSubmit:true,modal: true, closeAfterEdit: true,"
				+ "\n   afterSubmit : function(xhr,postdata)" + "\n       {" + "\n           var x=document.getElementById('" + child_id
				+ "postdatamessage');" + "\n           $.blockUI({ message: xhr.responseText });" + "\n           setTimeout($.unblockUI, 2000);"
				+ "\n           return [true,xhr];" + "\n	      }" + "\n  }";*/
		
		String modifynavbar = "\n {height:300,width:500,reloadAfterSubmit:true,modal: true, closeAfterEdit: true,"
				+ "\n   afterSubmit : function(data,postdata,oper)" + "\n       {" + "\n           " +
						"var response = data.responseJSON;" + "\n    " +
				"       if (response && response.hasOwnProperty(\"error\")) {"+ "\n"+
							"if(response.error.length) {\n"+
							"	return [false,response.error ];\n"+
							"}\n"+
						"}\n"+
						"else{"+
						"if(response && response.hasOwnProperty(\"success\")){"+
							"$.blockUI({ message: response.success });"+
							"setTimeout($.unblockUI, 2000);"+
							"return [true,response.success ];\n}"+
						"else{"+
							"$.blockUI({ message: data.responseText });"+
							"setTimeout($.unblockUI, 2000);"+
							"return [true,data.responseText,\"\"];\n"+
						"}"+	
						
				" }" + "\n  }\n  }";
		
		

		String deletenavbarexistcheck = "";
		String deletenavbarexist = "";
		deletenavbarexistcheck = NewDataBaseLayer.getDeletenavbarexistcheck(interface_id, child_id);
		if (GenericUtil.isEmptyString(deletenavbarexistcheck)) {
			deletenavbarexist = ",del:false";
		} else {
			deletenavbarexist = ",del:true";
		}
		/*String deletenavbar = "\n   {reloadAfterSubmit:true," + "\n   onclickSubmit : function(eparams)" + "\n 	 {" + "\n 	    var ret={};"
				+ "\n 	    var sr=jQuery(\"#" + child_id + "\").getGridParam('selrow');" + "\n 	    rowdat=jQuery(\"#" + child_id
				+ "\").getRowData(sr);" + "\n        ret={" + rowdatastring + "};" + "\n        return ret;" + "\n 	},"
				+ "\n   afterSubmit : function(xhr,postdata)" + "\n    {" + "\n           var x=document.getElementById('" + child_id
				+ "postdatamessage');" + "\n           $.blockUI({ message: xhr.responseText });" + "\n           setTimeout($.unblockUI, 2000);"
				+ "\n           return [true,xhr];" + "\n	  }" + "}";*/
		
		String deletenavbar = "\n   {reloadAfterSubmit:true," + "\n   onclickSubmit : function(eparams)" + "\n 	 {" + "\n 	    var ret={};"
				+ "\n 	    var sr=jQuery(\"#" + child_id + "\").getGridParam('selrow');" + "\n 	    rowdat=jQuery(\"#" + child_id
				+ "\").getRowData(sr);" + "\n        ret={" + rowdatastring + "};" + "\n        return ret;" + "\n 	},"
				+ "\n   afterSubmit : function(data,postdata,oper)" + "\n       {" + "\n           " +
						"var response = data.responseJSON;" + "\n    " +
				"       if (response && response.hasOwnProperty(\"error\")) {"+ "\n"+
							"if(response.error.length) {\n"+
							"$.blockUI({ message: response.error });"+
							"setTimeout($.unblockUI, 2000);"+
							"	return [true,response.error ];\n"+
							"}\n"+
						"}\n"+
						"else{"+
						"if(response && response.hasOwnProperty(\"success\")){"+
							"$.blockUI({ message: response.success });"+
							"setTimeout($.unblockUI, 2000);"+
							"return [true,response.success ];\n}"+
						"else{"+
							"$.blockUI({ message: data.responseText });"+
							"setTimeout($.unblockUI, 2000);"+
							"return [true,data.responseText,\"\"];\n"+
						"}"+	
						
				" }" + "\n  }\n  }";
		

		if (GenericUtil.hasString(navbar) && navbar.equals("true")) {
			navbar = "\n jQuery(\"#" + child_id + "\").navGrid('#" + child_id + "pagered'," + "\n {" + modifynavbarexist + addnavbarexist
					+ deletenavbarexist + "}, //options" + modifynavbar + "," + addnavbar + "," + deletenavbar + "," +

					"\n {sopt:['cn','bw','eq','ne','lt','gt','ew']," + resetsearchonclose + multiplesearch + "\n }," + "\n { }" + "\n );";
		}

		Vector<String> getbehaviourfunction = NewDataBaseLayer.getbehaviourforGrid(interface_id, child_id);
		String getbehaviourfunctionname = "";
		if (getbehaviourfunction.size() != 0) {
			for (int bh = 0; bh < getbehaviourfunction.size(); bh = bh + 4) {
				String gridevent =  getbehaviourfunction.elementAt(bh);
				String gridfunctionname =  getbehaviourfunction.elementAt(bh + 1);
				String valuetype =  getbehaviourfunction.elementAt(bh + 2);
				String resource_id =  getbehaviourfunction.elementAt(bh + 3);
				if (valuetype.equals("inline")) {
					getbehaviourfunctionname = getbehaviourfunctionname + gridevent + ":" + gridfunctionname + ",";

				}
				if (valuetype.equals("reference")) {
					Vector<String> js = NewDataBaseLayer.getjs(resource_id, interface_id);
					String jsscript = "";
					for (int k = 0; k < js.size(); k = k + 1) {
						jsscript =  js.elementAt(0);
					}
					getbehaviourfunctionname = getbehaviourfunctionname + gridevent + ":" + jsscript + ",";

				}
			}
		}

		if (GenericUtil.hasString(height)) {
			height = "height:" + height + ",";
		} else {
			height = "";
		}

		String widthString = "";
		if (GenericUtil.hasString(width)) {
			widthString = "width:" + width + ",";
		} else {
			widthString = "";
		}

		
		/**
		 * TODO: Hardcoded shrinkToFit styling. Need to change to make it user
		 * input.
		 */
		String shrinkToFit = "  \n shrinkToFit: false,";
		
		if (customeditbutton == null) {
			customeditbutton = "";
		}

		if (customeditbutton.equals("true")) {
			navbar = "\n $(\"#" + child_id + "gridadd\").click(function(){ jQuery(\"#" + child_id + "\").jqGrid('editGridRow',\"new\"," + addnavbar
					+ "); });" + "\n $(\"#" + child_id + "gridedit\").click(function(){ var gr = jQuery(\"#" + child_id
					+ "\").jqGrid('getGridParam','selrow'); if( gr != null ) jQuery(\"#" + child_id + "\").jqGrid('editGridRow',gr," + modifynavbar
					+ "); else alert(\"Please Select Row\"); }); " + "\n $(\"#" + child_id + "griddelete\").click(function(){ var gr = jQuery(\"#"
					+ child_id + "\").jqGrid('getGridParam','selrow'); if( gr != null ) jQuery(\"#" + child_id + "\").jqGrid('delGridRow',gr,"
					+ deletenavbar + "); else alert(\"Please Select Row to delete!\"); }); " + "\n $(\"#" + child_id
					+ "gridsearch\").click(function(){ jQuery(\"#" + child_id
					+ "\").jqGrid('searchGrid', {sopt:['cn','bw','eq','ne','lt','gt','ew']," + resetsearchonclose + multiplesearch + "\n   } ); });"
					+ "\n $(\"#" + child_id + "gridrefresh\").click(function(){ jQuery(\"#" + child_id + "\").trigger('reloadGrid');	}); ";

		}

		String s2 = "\n rowNum:" + rowNum + "," + "\n rowList:[" + rowList + "]," + "\n pager: jQuery('#" + child_id + "pagered'),"
				+ "\n imgpath: '../coreadmin/themes/basic/images'," + "\n viewrecords: true," + height + widthString + shrinkToFit+ getbehaviourfunctionname
				+ getmultiselect + getmultiboxonly + "\n sortorder:\"" + SortOrder + "\"," + "\n caption:\"" + Caption + "\"," + "\n editurl:\""
				+ editurl + "\"" + "\n }); " + navbar + "\n }";

		String mainstring = s + s1 + "]," + s3 + s4 + "]," + s2;

		// System.out.println(".....................MIANSTRING.............."+mainstring);
		Element gscript9 = doc.createElement("script");
		gscript9.setAttribute("type", "text/javascript");
		gscript9.appendChild(doc.createTextNode(mainstring));

		Element griddiv = doc.createElement("div");
		if (checkInterfaceType.equals("InterfaceFragment")) /*
															 * This block is use
															 * for
															 * InterfaceFragment
															 * Check
															 */
		{
			// System.out.println("....................jjjjjjjjjjjjjjjjjjjjj.............");

			griddiv.setAttribute("id", child_id + "griddiv");
			
			styleEngine.createStyle(layout, style, child_id, interface_id, themeId, partclass, position, x, y, width, height, griddiv, itemhead, itembody, doc);
			
			layoutelement = doc.createElement("table");
			griddiv.appendChild(layoutelement);
			itemmain.appendChild(griddiv);
			layoutelement.setAttribute("id", child_id);
			layoutelement.setAttribute("class", "scroll");
			layoutelement.setAttribute("cellpadding", "0");
			layoutelement.setAttribute("cellspacing", "0");
			layoutelement.setAttribute("width", "100%");
			Element gridlayoutelement = doc.createElement("div");
			griddiv.appendChild(gridlayoutelement);
			gridlayoutelement.setAttribute("class", "scroll");
			gridlayoutelement.setAttribute("id", child_id + "pagered");
			gridlayoutelement.setAttribute("style", "text-align:center;");

			bodyelement.appendChild(gscript9); /*
												 * Fragment grid function is
												 * embed under body tag
												 */
			Element grdFragementFunction = doc.createElement("script");
			grdFragementFunction.setAttribute("type", "text/javascript");
			grdFragementFunction.appendChild(doc.createTextNode("generateGrid();")); /*
																					 * Fragment
																					 * grid
																					 * function
																					 * is
																					 * call
																					 * under
																					 * body
																					 * tag
																					 */
			bodyelement.appendChild(grdFragementFunction);
		} else {
			headelement.appendChild(gscript9);
			gridstring = "generateGrid();";
			griddiv.setAttribute("id", child_id + "griddiv");
			styleEngine.createStyle(layout, style, child_id, interface_id, themeId, partclass, position, x, y, width, height, griddiv, itemhead, itembody, doc);
			layoutelement = doc.createElement("table");
			griddiv.appendChild(layoutelement);
			itemmain.appendChild(griddiv);
			layoutelement.setAttribute("id", child_id);
			layoutelement.setAttribute("class", "scroll");
			layoutelement.setAttribute("cellpadding", "0");
			layoutelement.setAttribute("cellspacing", "0");
			layoutelement.setAttribute("width", "100%");
			Element gridlayoutelement = doc.createElement("div");
			griddiv.appendChild(gridlayoutelement);
			gridlayoutelement.setAttribute("class", "scroll");
			gridlayoutelement.setAttribute("id", child_id + "pagered");
			gridlayoutelement.setAttribute("style", "text-align:center;");
		}

		if (customeditbutton.equals("true")) {
			Element gridadd = doc.createElement("input");
			griddiv.appendChild(gridadd);
			gridadd.setAttribute("type", "button");
			gridadd.setAttribute("id", child_id + "gridadd");
			gridadd.setAttribute("value", "Add");
			gridadd.setAttribute("style", "position:relative;margin-top:10px;float:left");

			Element gridedit = doc.createElement("input");
			griddiv.appendChild(gridedit);
			gridedit.setAttribute("type", "button");
			gridedit.setAttribute("id", child_id + "gridedit");
			gridedit.setAttribute("value", "Edit");
			gridedit.setAttribute("style", "position:relative;margin-top:10px;float:left");

			Element griddelete = doc.createElement("input");
			griddiv.appendChild(griddelete);
			griddelete.setAttribute("type", "button");
			griddelete.setAttribute("id", child_id + "griddelete");
			griddelete.setAttribute("value", "Delete");
			griddelete.setAttribute("style", "position:relative;margin-top:10px;float:left");

			Element gridsearch = doc.createElement("input");
			griddiv.appendChild(gridsearch);
			gridsearch.setAttribute("type", "button");
			gridsearch.setAttribute("id", child_id + "gridsearch");
			gridsearch.setAttribute("value", "Search");
			gridsearch.setAttribute("style", "position:relative;margin-top:10px;float:left");

			Element gridrefresh = doc.createElement("input");
			griddiv.appendChild(gridrefresh);
			gridrefresh.setAttribute("type", "button");
			gridrefresh.setAttribute("id", child_id + "gridrefresh");
			gridrefresh.setAttribute("value", "Refresh");
			gridrefresh.setAttribute("style", "position:relative;margin-top:10px;float:left");
		}
		// //////////////////////////////////////////////// POSTDATA
		// MESSAGE///////////////////////////////////////////////////
		Element postdatamessage = doc.createElement("div");
		postdatamessage.setAttribute("id", child_id + "postdatamessage");
		postdatamessage.setAttribute("style", "color:black;size:8px;width:300px;position:" + position + ";left :70%;top:2%;");
		itemmain.appendChild(postdatamessage);
		return gridstring;
		// //////////////////////////////////////////////// POSTDATA
		// MESSAGE///////////////////////////////////////////////////

	}

}
