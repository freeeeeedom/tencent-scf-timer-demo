(function(e){function t(t){for(var o,l,s=t[0],i=t[1],c=t[2],d=0,f=[];d<s.length;d++)l=s[d],a[l]&&f.push(a[l][0]),a[l]=0;for(o in i)Object.prototype.hasOwnProperty.call(i,o)&&(e[o]=i[o]);u&&u(t);while(f.length)f.shift()();return r.push.apply(r,c||[]),n()}function n(){for(var e,t=0;t<r.length;t++){for(var n=r[t],o=!0,s=1;s<n.length;s++){var i=n[s];0!==a[i]&&(o=!1)}o&&(r.splice(t--,1),e=l(l.s=n[0]))}return e}var o={},a={app:0},r=[];function l(t){if(o[t])return o[t].exports;var n=o[t]={i:t,l:!1,exports:{}};return e[t].call(n.exports,n,n.exports,l),n.l=!0,n.exports}l.m=e,l.c=o,l.d=function(e,t,n){l.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:n})},l.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},l.t=function(e,t){if(1&t&&(e=l(e)),8&t)return e;if(4&t&&"object"===typeof e&&e&&e.__esModule)return e;var n=Object.create(null);if(l.r(n),Object.defineProperty(n,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var o in e)l.d(n,o,function(t){return e[t]}.bind(null,o));return n},l.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return l.d(t,"a",t),t},l.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},l.p="/scf/scfTimer/edit/";var s=window["webpackJsonp"]=window["webpackJsonp"]||[],i=s.push.bind(s);s.push=t,s=s.slice();for(var c=0;c<s.length;c++)t(s[c]);var u=i;r.push([0,"chunk-vendors"]),n()})({0:function(e,t,n){e.exports=n("56d7")},"034f":function(e,t,n){"use strict";var o=n("64a9"),a=n.n(o);a.a},"56d7":function(e,t,n){"use strict";n.r(t);n("cadf"),n("551c"),n("f751"),n("097d");var o=n("2b0e"),a=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{attrs:{id:"app"}},[n("el-menu",{staticClass:"el-menu-demo",attrs:{"default-active":e.activeIndex,mode:"horizontal","background-color":"#545c64","text-color":"#fff","active-text-color":"#ffd04b"},on:{select:e.handleSelect}},e._l(e.menus,function(t,o){return n("el-menu-item",{attrs:{index:o+1+""},on:{click:function(n){return e.toPage(t.path)}}},[e._v("\n            "+e._s(t.label)+"\n        ")])}),1),n("br"),n("router-view")],1)},r=[],l=(n("ac6a"),function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"ruleTemplate"},[n("div",[n("h2",[e._v("SCF定时触发器修改")]),n("el-row",{attrs:{gutter:50}},[n("el-col",{attrs:{md:3}},[n("span",[e._v("触发器")])]),n("el-col",{attrs:{md:4}},[n("el-select",{attrs:{filterable:"",placeholder:"请选择"},on:{change:e.initTable},model:{value:e.nowChooseLabel,callback:function(t){e.nowChooseLabel=t},expression:"nowChooseLabel"}},e._l(e.triggers,function(e){return n("el-option",{key:e.triggerName,attrs:{label:e.triggerName,value:e.triggerName}})}),1)],1)],1),n("el-row",{attrs:{gutter:50}},[n("el-col",{attrs:{md:3}},[n("span",[e._v("所属函数")])]),n("el-col",{attrs:{md:4}},[n("el-input",{attrs:{placeholder:"函数名称",disabled:!0},model:{value:e.nowChoose.functionName,callback:function(t){e.$set(e.nowChoose,"functionName",t)},expression:"nowChoose.functionName"}})],1)],1),n("el-row",{attrs:{gutter:50}},[n("el-col",{attrs:{md:3}},[n("span",[e._v("触发器名称")])]),n("el-col",{attrs:{md:4}},[n("el-input",{attrs:{placeholder:"触发器名称",disabled:!e.isCreate},model:{value:e.nowChoose.triggerName,callback:function(t){e.$set(e.nowChoose,"triggerName",t)},expression:"nowChoose.triggerName"}})],1)],1),n("el-row",{attrs:{gutter:50}},[n("el-col",{attrs:{md:3}},[n("span",[e._v("是否启用")])]),n("el-col",{attrs:{md:3}},[n("el-switch",{attrs:{"active-text":"启用","inactive-text":"停用"},model:{value:e.nowChoose.enable,callback:function(t){e.$set(e.nowChoose,"enable",t)},expression:"nowChoose.enable"}}),n("el-tag",{attrs:{size:"mini"}},[e._v("启用则再下一个生效时间触发")])],1)],1),n("el-row",{attrs:{gutter:50}},[n("el-col",{attrs:{md:3}},[n("span",[e._v("表达式定义")])]),e._l(e.triggerDesc,function(t){return n("el-col",{attrs:{md:2}},[n("el-input",{attrs:{placeholder:t.title,maxlength:"4",disabled:t.isDisabled},model:{value:t.val,callback:function(n){e.$set(t,"val",n)},expression:"item.val"}}),n("el-tag",{attrs:{size:"mini"}},[e._v(e._s(t.remark))])],1)})],2),n("el-button",{directives:[{name:"loading",rawName:"v-loading.fullscreen.lock",value:e.fullscreenLoading,expression:"fullscreenLoading",modifiers:{fullscreen:!0,lock:!0}}],attrs:{type:"success"},on:{click:function(t){return e.saveChange()}}},[e._v("\n            更新定时器\n        ")]),n("el-button",{attrs:{type:"danger"},on:{click:function(t){return e.back2List()}}},[e._v("取消返回")])],1),n("div",[n("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData,align:"center"}},e._l(e.tableRow,function(t){return n("el-table-column",{key:t.prop,attrs:{prop:t.prop,label:t.label,width:t.width,"show-overflow-tooltip":!0},scopedSlots:e._u([{key:"default",fn:function(o){return[t.showBox?n("a",{attrs:{type:"primary"},on:{click:function(n){return e.showMsgBox(o.row[t.prop],t.label,t.showBox)}}},[e._v("\n                        "+e._s(o.row[t.prop])+"\n                    ")]):e._e(),"IN_USE"==t.prop?n("el-switch",{attrs:{"active-text":"启用","inactive-text":"停用"},on:{change:function(t){return e.taskInUseChange(o.row)}},model:{value:o.row["IN_USE"],callback:function(t){e.$set(o.row,"IN_USE",t)},expression:"scope.row['IN_USE']"}}):e._e(),"IS_ASYNC"==t.prop?n("el-switch",{attrs:{"active-text":"是","inactive-text":"否"},on:{change:function(t){return e.taskIsAsyncChange(o.row)}},model:{value:o.row["IS_ASYNC"],callback:function(t){e.$set(o.row,"IS_ASYNC",t)},expression:"scope.row['IS_ASYNC']"}}):e._e(),t.showBox||"IN_USE"==t.prop||"IS_ASYNC"==t.prop?e._e():n("span",[e._v(e._s(o.row[t.prop]))])]}}],null,!0)})}),1)],1)])}),s=[],i=(n("28a5"),"/"+window.location.href.split("/")[3]),c={context:i,functionTriggers:i+"/scf/functionTriggers.mob",updateTrigger:i+"/scf/updateTrigger.mob",createTrigger:i+"/scf/createTrigger.mob",deleteTrigger:i+"/scf/deleteTrigger.mob",getTimerTasks:i+"/scfTimerBind/getTimerTasks.mob",updateInUseTimerTask:i+"/scfTimerBind/updateInUseTimerTask.mob",updateIsAsyncTimerTask:i+"/scfTimerBind/updateIsAsyncTimerTask.mob"},u=c,d=n("bc3a"),f=n.n(d);function p(){return f.a.get(u.functionTriggers+"?nameSpace=crm&functionName=scfTimer")}function h(e){return f.a.post(u.updateTrigger,e)}function g(e){return f.a.post(u.createTrigger,e)}function m(e){return f.a.post(u.deleteTrigger,e)}function w(e){return f.a.post(u.getTimerTasks,e)}function b(e,t){return f.a.get(u.updateInUseTimerTask+"?taskId="+t+"&inUse="+e)}function v(e,t){return f.a.get(u.updateIsAsyncTimerTask+"?taskId="+t+"&isAsync="+e)}var _=1500;function k(e){e.$message({duration:_,showClose:!0,message:"操作成功",type:"success"})}function C(e,t){e.$message({duration:_,showClose:!0,message:t,type:"warning"})}function S(e,t){e.$message({duration:_,showClose:!0,dangerouslyUseHTMLString:!0,message:t,type:"error"})}var y=new o["default"],T={props:[],data:function(){return{nowChooseLabel:"",nowChoose:{triggerName:"",functionName:"",enable:!1,type:"timer",triggerDesc:""},triggers:[],fullscreenLoading:!1,isCreate:!0,triggerDesc:[{key:"second",val:"*",title:"秒",remark:"秒：0-59的整数"},{key:"minute",val:"*",title:"分",remark:"分：0-59的整数"},{key:"hour",val:"*",title:"时",remark:"时：0-23的整数"},{key:"day",val:"*",title:"日",remark:"日：1-31的整数"},{key:"month",val:"*",title:"月",remark:"月：1-12的整数"},{key:"week",val:"*",title:"星期",remark:"星期：0-6的整数"},{key:"year",val:"*",title:"年",remark:"年：1970-2099的整数",isDisabled:!0}],tableRow:[{prop:"TASK_NAME",label:"任务名",width:180},{prop:"TASK_TYPE",label:"任务类型",width:180,showBox:!0},{prop:"TASK_VAL",label:"任务内容",width:180,showBox:!0},{prop:"TASK_FUNCTION",label:"方法和参数",width:180,showBox:!0},{prop:"TASK_PARAM",label:"任务参数",width:100,showBox:!0},{prop:"IS_ASYNC",label:"是否异步",width:160},{prop:"IN_USE",label:"是否启用",width:160}],tableData:[]}},mounted:function(){var e=this;this.fullscreenLoading=!0,p().then(function(t){e.triggers=t.data.result,e.fullscreenLoading=!1})},methods:{showMsgBox:function(e,t,n){n&&this.$alert(e,t,{confirmButtonText:"确定"})},back2List:function(){window.history.back(-1)},taskInUseChange:function(e){var t,n=this;this.fullscreenLoading=!0,t=e["IN_USE"]?1:0,b(t,e["ID"]).then(function(){n.fullscreenLoading=!1,C(n,1==t?e["TASK_NAME"]+"已启用":e["TASK_NAME"]+"已停用")})},taskIsAsyncChange:function(e){var t,n=this;this.fullscreenLoading=!0,t=e["IS_ASYNC"]?1:0,v(t,e["ID"]).then(function(){n.fullscreenLoading=!1,C(n,1==t?e["TASK_NAME"]+"已改为异步":e["TASK_NAME"]+"已改为同步")})},initTable:function(){var e=this;this.isCreate=!1,this.nowChoose=this.triggers.filter(function(t){return t.triggerName==e.nowChooseLabel})[0],this.nowChoose.enable=1==this.nowChoose.enable;for(var t=this.nowChoose.triggerDesc.split(" "),n=0;n<t.length;n++)this.triggerDesc[n].val=t[n];this.fullscreenLoading=!0,w(this.nowChoose).then(function(t){e.tableData=t.data.result,e.tableData.forEach(function(e){1==e["IN_USE"]&&(e["IN_USE"]=!0),0==e["IN_USE"]&&(e["IN_USE"]=!1),1==e["IS_ASYNC"]&&(e["IS_ASYNC"]=!0),0==e["IS_ASYNC"]&&(e["IS_ASYNC"]=!1)}),e.fullscreenLoading=!1})},saveChange:function(){var e=this;this.fullscreenLoading=!0;var t=this.triggerDesc.map(function(e){return e.val}).join(" ");this.nowChoose.triggerDesc=t,this.nowChoose.enable=this.nowChoose.enable?"OPEN":"CLOSE",h(this.nowChoose).then(function(t){console.log(t),"1"==t.data.state?k(e):S(e,t.data.meg),e.nowChoose.enable="OPEN"==e.nowChoose.enable,e.fullscreenLoading=!1})}}},N=T,x=n("2877"),I=Object(x["a"])(N,l,s,!1,null,null,null),L=I.exports,A=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"ruleTemplate"},[n("div",[n("h2",[e._v("SCF定时触发器创建")]),n("el-row",{attrs:{gutter:50}},[n("el-col",{attrs:{md:3}},[n("span",[e._v("所属命名空间")])]),n("el-col",{attrs:{md:4}},[n("el-select",{attrs:{filterable:"",placeholder:"请选择"},model:{value:e.nowChoose.nameSpace,callback:function(t){e.$set(e.nowChoose,"nameSpace",t)},expression:"nowChoose.nameSpace"}},e._l(e.nameSpaces,function(e){return n("el-option",{key:e.nameSpace,attrs:{label:e.nameSpace,value:e.nameSpace}})}),1)],1)],1),n("el-row",{attrs:{gutter:50}},[n("el-col",{attrs:{md:3}},[n("span",[e._v("所属函数")])]),n("el-col",{attrs:{md:4}},[n("el-select",{attrs:{filterable:"",placeholder:"请选择"},model:{value:e.nowChoose.functionName,callback:function(t){e.$set(e.nowChoose,"functionName",t)},expression:"nowChoose.functionName"}},e._l(e.functions,function(e){return n("el-option",{key:e.functionName,attrs:{label:e.functionName,value:e.functionName}})}),1)],1)],1),n("el-row",{attrs:{gutter:50}},[n("el-col",{attrs:{md:3}},[n("span",[e._v("触发器名称")])]),n("el-col",{attrs:{md:4}},[n("el-input",{attrs:{placeholder:"触发器名称"},model:{value:e.nowChoose.triggerName,callback:function(t){e.$set(e.nowChoose,"triggerName",t)},expression:"nowChoose.triggerName"}})],1)],1),n("el-row",{attrs:{gutter:50}},[n("el-col",{attrs:{md:3}},[n("span",[e._v("是否启用")])]),n("el-col",{attrs:{md:3}},[n("el-switch",{attrs:{"active-text":"启用","inactive-text":"停用"},model:{value:e.nowChoose.enable,callback:function(t){e.$set(e.nowChoose,"enable",t)},expression:"nowChoose.enable"}}),n("el-tag",{attrs:{size:"mini"}},[e._v("启用则再下一个生效时间触发")])],1)],1),n("el-row",{attrs:{gutter:50}},[n("el-col",{attrs:{md:3}},[n("span",[e._v("表达式定义")])]),e._l(e.triggerDesc,function(t){return n("el-col",{attrs:{md:2}},[n("el-input",{attrs:{placeholder:t.title,maxlength:"4",disabled:t.isDisabled},model:{value:t.val,callback:function(n){e.$set(t,"val",n)},expression:"item.val"}}),n("el-tag",{attrs:{size:"mini"}},[e._v(e._s(t.remark))])],1)})],2),n("el-button",{directives:[{name:"loading",rawName:"v-loading.fullscreen.lock",value:e.fullscreenLoading,expression:"fullscreenLoading",modifiers:{fullscreen:!0,lock:!0}}],attrs:{type:"success"},on:{click:function(t){return e.saveChange()}}},[e._v("\n            创建定时器\n        ")]),n("el-button",{attrs:{type:"danger"},on:{click:function(t){return e.back2List()}}},[e._v("取消返回")])],1),n("div",[n("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData,align:"center"}},e._l(e.tableRow,function(t){return n("el-table-column",{key:t.prop,attrs:{prop:t.prop,label:t.label,width:t.width,"show-overflow-tooltip":!0},scopedSlots:e._u([{key:"default",fn:function(o){return[t.showBox?n("a",{attrs:{type:"primary"},on:{click:function(n){return e.showMsgBox(o.row[t.prop],t.label,t.showBox)}}},[e._v("\n                        "+e._s(o.row[t.prop])+"\n                    ")]):e._e(),t.showBox?e._e():n("span",[e._v(e._s(o.row[t.prop]))]),e.deleteMode&&"option"==t.prop?n("el-button",{attrs:{type:"danger",icon:"el-icon-delete",circle:"",size:"mini"},on:{click:function(t){return e.delTrigger(o.row)}}}):e._e()]}}],null,!0)})}),1)],1)])},E=[],D={props:[],data:function(){return{nowChooseLabel:"",nowChoose:{triggerName:"",functionName:"scfTimer",enable:!1,type:"timer",triggerDesc:"",nameSpace:"crm"},functions:[{functionName:"scfTimer"}],nameSpaces:[{nameSpace:"crm"}],fullscreenLoading:!1,deleteMode:!1,triggerDesc:[{key:"second",val:"*",title:"秒",remark:"秒：0-59的整数"},{key:"minute",val:"*",title:"分",remark:"分：0-59的整数"},{key:"hour",val:"*",title:"时",remark:"时：0-23的整数"},{key:"day",val:"*",title:"日",remark:"日：1-31的整数"},{key:"month",val:"*",title:"月",remark:"月：1-12的整数"},{key:"week",val:"*",title:"星期",remark:"星期：0-6的整数"},{key:"year",val:"*",title:"年",remark:"年：1970-2099的整数",isDisabled:!0}],tableRow:[{prop:"triggerName",label:"触发器名称",width:180},{prop:"nameSpace",label:"命名空间",width:180},{prop:"functionName",label:"函数名",width:180},{prop:"triggerDesc",label:"触发器规则",width:180,showBox:!0},{prop:"enable",label:"是否启用",width:160},{prop:"option",label:"操作",width:160}],tableData:[]}},mounted:function(){var e=this;"true"===this.$route.query.deleteMode&&(this.deleteMode=!0),this.getTriggers(),y.$on("create",function(){console.log("create"),e.getTriggers()}),y.$on("delete",function(){console.log("del"),e.getTriggers()})},methods:{back2List:function(){this.$router.push("edit")},saveChange:function(){var e=this;this.fullscreenLoading=!0;var t=this.triggerDesc.map(function(e){return e.val}).join(" ");if(this.nowChoose.triggerDesc=t,this.nowChoose.enable=this.nowChoose.enable?"OPEN":"CLOSE","* * * * * * *"==this.nowChoose.triggerDesc)return this.fullscreenLoading=!1,void S(this,"表达式不正确!");g(this.nowChoose).then(function(t){"1"==t.data.state?k(e):S(e,t.data.des),e.nowChoose.enable="OPEN"==e.nowChoose.enable,e.fullscreenLoading=!1,y.$emit("create",!0)})},getTriggers:function(){var e=this;this.fullscreenLoading=!0,p().then(function(t){e.tableData=t.data.result,e.tableData.forEach(function(e){e.enable=1==e.enable?"是":"否"}),e.fullscreenLoading=!1})},delTrigger:function(e){this.$confirm("此操作将删除"+e.triggerName+", 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){m(e).then(function(e){y.$emit("delete",!0)})}).catch(function(){})}}},$=D,B=Object(x["a"])($,A,E,!1,null,null,null),O=B.exports,U={name:"app",components:{scfEdit:L,scfCreate:O},mounted:function(){var e=this;this.menus.forEach(function(t,n){t.path===e.$route.path&&(e.activeIndex=n+1+"")})},data:function(){return{activeIndex:"1",menus:[{path:"/edit",label:"编辑触发器"},{path:"/create",label:"创建触发器"}]}},methods:{handleSelect:function(e,t){console.log(e,t)},toPage:function(e,t,n){this.$router.push(e)}}},M=U,P=(n("034f"),Object(x["a"])(M,a,r,!1,null,null,null)),j=P.exports,Y=n("5c96"),K=n.n(Y),z=(n("0fae"),n("8c4f"));o["default"].use(K.a),o["default"].use(z["a"]),o["default"].config.productionTip=!1;var R=[{path:"/create",component:O},{path:"/edit",component:L},{path:"/",component:L}],F=new z["a"]({routes:R});new o["default"]({render:function(e){return e(j)},router:F}).$mount("#app")},"64a9":function(e,t,n){}});